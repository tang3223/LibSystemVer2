package com.gcit.libsystem.web;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.libsystem.entity.*;
import com.gcit.libsystem.service.BorrowerService;;


@WebServlet({"/brselectbranch","/checkid","/confirmcheckbook","/confirmreturnbook","/checkAbility"})
public class BorrowerServlet extends HttpServlet {

	private static final long serialVersionUID = 1494340643033434525L;

	public BorrowerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "/index.jsp";
		boolean ajaxUseFlag = false;
		switch (reqUrl) {
		case "/checkid":
			ajaxUseFlag = Boolean.TRUE;
			String data = checkID(request);
			response.getWriter().write(data);
			break;
		default:
			break;
		}
		if (!ajaxUseFlag) {
			RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "/index.jsp";
		Integer branchID = 1;
		Integer cardID = 1;
		switch (reqUrl) {		
		case "/brselectbranch":		
			if (request.getParameter("borrowerType") != null){
				forwardPath = "/returnbook.jsp";
			}
			else {
				forwardPath = "/checkbook.jsp";
			}
			cardID = Integer.parseInt(request.getParameter("cardNum"));
			branchID = Integer.parseInt(request.getParameterValues("branchId")[0].toString());
			break;
		case "/confirmcheckbook":
			checkOutBook(request);
			forwardPath = "/checkbook.jsp";
			cardID = Integer.parseInt(request.getParameter("borrowerId"));
			branchID = Integer.parseInt(request.getParameter("branchId"));
			break;
		case "/confirmreturnbook":
			returnBook(request);
			forwardPath = "/returnbook.jsp";
			cardID = Integer.parseInt(request.getParameter("borrowerId"));
			break;
		default:
			break;
		}
		request.setAttribute("borrowerId", cardID);
		request.setAttribute("branchId", branchID);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);		
	}

	private String checkID(HttpServletRequest request) {
		Integer cardID = Integer.parseInt(request.getParameter("cardID"));
		BorrowerService service = new BorrowerService();
		String passed = "<button type='submit' class='btn btn-primary'>Select</button>";
		String failed = "<p>Invalid Card Number!</p>";
		try {
			if (!service.checkCardID(cardID)){
				return failed;
			}
			else {
				return passed;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return failed;
		}
	}

	private void checkOutBook(HttpServletRequest request) {
		Integer bookID = Integer.parseInt(request.getParameter("bookid"));
		Integer branchID = Integer.parseInt(request.getParameter("branchid"));
		Integer borrowerID = Integer.parseInt(request.getParameter("borrowerid"));
		BorrowerService service = new BorrowerService();
		
		try {
			Book book = service.readBook(bookID);
			Branch branch = service.readBranch(branchID);
			Borrower borrower = service.readBorrower(borrowerID);
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBook(book);
			bookLoan.setBorrower(borrower);
			bookLoan.setBranch(branch);
			service.checkOutBook(bookID,branchID,borrowerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void returnBook(HttpServletRequest request) {
		Integer bookID = Integer.parseInt(request.getParameter("bookId"));
		Integer branchID = Integer.parseInt(request.getParameter("branchId"));
		Integer borrowerID = Integer.parseInt(request.getParameter("borrowerId"));
		String  dayOut = request.getParameter("dayOut");
		BorrowerService service = new BorrowerService();
		
		try {
			Book book = service.readBook(bookID);
			Branch branch = service.readBranch(branchID);
			Borrower borrower = service.readBorrower(borrowerID);
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBook(book);
			bookLoan.setBorrower(borrower);
			bookLoan.setBranch(branch);
			service.returnBook(bookID,branchID,borrowerID,dayOut);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
}















