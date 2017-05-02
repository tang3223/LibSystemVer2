package com.gcit.libsystem.web;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.libsystem.entity.*;
import com.gcit.libsystem.service.LibrarianService;;


@WebServlet({"/selectbranch","/addbranchbook","/editbranchbook",
	"/deletebranchbook"})
public class LibrarianServlet extends HttpServlet {

	private static final long serialVersionUID = 2284422418894890491L;
	
    public LibrarianServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "/index.jsp";
		switch (reqUrl) {

		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "/index.jsp";
		Integer branchID = 1;
		switch (reqUrl) {		
		case "/selectbranch":
			forwardPath = "/managebranch.jsp";
			branchID = Integer.parseInt(request.getParameterValues("branchId")[0].toString());
			break;
		case "/addbranchbook":
			addCopies(request);
			forwardPath = "/managebranch.jsp";
			branchID = Integer.parseInt(request.getParameterValues("branchId")[0].toString());
			break;
		case "/editbranchbook":
			updateCopies(request);
			forwardPath = "/managebranch.jsp";
			branchID = Integer.parseInt(request.getParameterValues("branchId")[0].toString());
			break;
		case "/deletebranchbook":
			deleteCopies(request);
			forwardPath = "/managebranch.jsp";
			branchID = Integer.parseInt(request.getParameterValues("branchId")[0].toString());
			break;
		default:
			break;
		}
		request.setAttribute("branchId", branchID);
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);		
	}

	private void addCopies(HttpServletRequest request){
		Branch branch = new Branch();
		LibrarianService service = new LibrarianService();
		Integer bookID = Integer.parseInt(request.getParameter("bookId"));
		Integer bookCopies = Integer.parseInt(request.getParameter("bookCopies"));
		branch.setnoOfCopies(bookID, bookCopies);
		branch.setBranchID(Integer.parseInt(request.getParameter("branchId")));
		try {
			service.addNoOfCopies(bookID, branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateCopies(HttpServletRequest request){
		Branch branch = new Branch();
		LibrarianService service = new LibrarianService();
		Integer bookID = Integer.parseInt(request.getParameter("bookId"));
		Integer bookCopies = Integer.parseInt(request.getParameter("bookCopies"));
		branch.setnoOfCopies(bookID, bookCopies);
		branch.setBranchID(Integer.parseInt(request.getParameter("branchId")));
		try {
			service.addNoOfCopies(bookID, branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteCopies(HttpServletRequest request) {
		Branch branch = new Branch();
		Integer bookID = Integer.parseInt(request.getParameter("bookId"));
		branch.setBranchID(Integer.parseInt(request.getParameter("branchId")));
		LibrarianService service = new LibrarianService();
		try {
			service.deleteNoOfCopies(bookID, branch);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}















