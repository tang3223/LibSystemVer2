package com.gcit.libsystem.web;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.libsystem.entity.*;
import com.gcit.libsystem.service.AdminService;


@WebServlet({"/addauthor", "/editauthor", "/pageauthors",
	"/searchauthor", "/searchauthorpage", "/deleteauthor", 
	"/editbook", "/deletebook", "/addbook",
	"/editgenre","/deletegenre","/addgenre",
	"/editpublisher","/deletepublisher","/addpublisher",
	"/editbranch","/deletebranch","/addbranch",
	"/editborrower","/deleteborrower","/addborrower",
	"/editbookloan"})
public class AdminServlet extends HttpServlet {
   
	private static final long serialVersionUID = -8553273515079577135L;
	private Integer currPageNum = 1;
	
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "/index.jsp";
		boolean ajaxUseFlag = false;
		switch (reqUrl) {
		
		case "/pageauthors":
			pageAuthors(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/searchauthor": 
			ajaxUseFlag = Boolean.TRUE;
			currPageNum = 1;
			String data = searchAuthors(request);
			response.getWriter().write(data);
			break;
		case "/searchauthorpage": 
			ajaxUseFlag = Boolean.TRUE;
			currPageNum = Integer.parseInt(request.getParameter("pageNum"));
			String dataPage = searchAuthors(request);
			response.getWriter().write(dataPage);
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
		switch (reqUrl) {		
		case "/addauthor":
			addAuthor(request);
			forwardPath = "/viewauthor.jsp";			
			break;
		case "/editauthor":
			updateAuthor(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/deleteauthor":
			deleteAuthor(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/addbook":
			addBook(request);
			forwardPath = "/viewbook.jsp";
			break;
		case "/editbook":
			updateBook(request);
			forwardPath = "/viewbook.jsp";
			break;
		case "/deletebook":
			deleteBook(request);
			forwardPath = "/viewbook.jsp";
			break;
		case "/addgenre":
			addGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/editgenre":
			updateGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/deletegenre":
			deleteGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/addpublisher":
			addPublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/editpublisher":
			updatePublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/deletepublisher":
			deletePublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/editbranch":
			updateBranch(request);
			if(request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("librarian")){
				forwardPath = "/managebranch.jsp";
				request.setAttribute("branchId", request.getParameter("branchID"));
			}
			else {
				forwardPath = "/viewbranch.jsp";
			}
			break;
		case "/deletebranch":
			deleteBranch(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/addbranch":
			addBranch(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/editborrower":
			updateBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/deleteborrower":
			deleteBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/addborrower":
			addBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/editbookloan":
			updateBookLoan(request);
			forwardPath = "/viewloan.jsp";
			break;
		default:
			break;
		}	
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);		
	}

	private void addAuthor(HttpServletRequest request) {
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		AdminService service = new AdminService();
		try {
			service.addAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateAuthor(HttpServletRequest request) {
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		author.setAuthorID(Integer.parseInt(request.getParameter("authorID")));
		AdminService service = new AdminService();
		try {
			service.updateAuthor(author);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteAuthor(HttpServletRequest request){
		Author author = new Author();
		author.setAuthorID(Integer.parseInt(request.getParameter("authorID")));
		AdminService service = new AdminService();
		
		try {
			service.deleteAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void pageAuthors(HttpServletRequest request) {
		Integer pageNum = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		try {
			request.setAttribute("authors", service.readAllAuthors(pageNum));
			request.setAttribute("index", pageNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String searchAuthors(HttpServletRequest request) {
		String authorName = request.getParameter("searchName");
		AdminService service = new AdminService();
		StringBuffer pushBack = new StringBuffer();
		Integer numOfPages = 0;
		try {
			List<Author> unprocessedAuthors = service.readAuthor(null, authorName);
			if (unprocessedAuthors.size() % 10 > 0) {
				numOfPages = unprocessedAuthors.size() / 10 + 1;
			} else {
				numOfPages = unprocessedAuthors.size() / 10;
			}

			List<Author> processedAuthors = service.readAuthor(currPageNum, authorName);
			pushBack.append("<div class='panel-heading'><button type='submit' class='btn btn-primary' data-toggle='modal' data-target='#myModal'>Add Author</button><div class='pull-right'><nav aria-label='Page navigation'><ul class='pagination' style='margin: 0px !important; vertical-align: middle;'><li><a href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");
			for (int i = 1; i <= numOfPages; i++){
				pushBack.append("<li><a onclick='searchAuthorPage(" + i + ")'>" + i + "</a></li>");
			}
			pushBack.append("<li><a href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li></ul></nav></div></div><table class='table table-striped' id=authorTable><thead><tr><th><h4><span class='label label-primary'>#</span></h4></th><th><h4><span class='label label-primary'>Author Name</span></h4></th><th><h4><span class='label label-primary'>Edit</span></h4></th><th><h4><span class='label label-primary'>Delete</span></h4></th></tr></thead><tbody>");		
			for (Author author : processedAuthors) {
				Integer index = processedAuthors.indexOf(author)+1+(currPageNum-1)*10;
				pushBack.append("<tr><td>" + index  + "</td><td>" + author.getAuthorName() + "</td>");
				pushBack.append("<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#editAuthorModel'href='editauthor.jsp?authorId="
								+ author.getAuthorID() + "'>Update</button></td>");
				pushBack.append("<td><button type='button' class='btn btn-danger' href='deleteAuthor?authorId="
						+ author.getAuthorID() + "'>Delete</button></td></tr>");
			}
			pushBack.append("</tbody></table>");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pushBack.toString();
	}
	
	private void addBook(HttpServletRequest request) {
		Book book = new Book();
		AdminService service = new AdminService();
		book.setTitle(request.getParameter("bookName"));
		String[] authorIDs = request.getParameterValues("authorIDs");
		String[] genreIDs  = request.getParameterValues("genreIDs");
		List<Author> authors = new ArrayList<>();
		List<Genre>  genres  = new ArrayList<>();
		try {
			for (int i = 0; i < authorIDs.length; i++ ) {
				Author author = service.readAuthor(Integer.parseInt(authorIDs[i]));
				authors.add(author);
			}
			book.setAuthors(authors);
			Publisher publisher = service.readPublisher(Integer.parseInt(request.getParameter("publisherID")));
			book.setPublisher(publisher);
			for (int i = 0; i < genreIDs.length; i++) {
				Genre genre = service.readGenre(Integer.parseInt(genreIDs[i]));
				genres.add(genre);
			}
			book.setGenres(genres);
			service.addBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBook(HttpServletRequest request) {
		Book book = new Book();
		AdminService service = new AdminService();
		book.setTitle(request.getParameter("bookName"));
		book.setBookId(Integer.parseInt(request.getParameter("bookID")));
		String[] authorIDs = request.getParameterValues("authorIDs");
		String[] genreIDs  = request.getParameterValues("genreIDs");
		List<Author> authors = new ArrayList<>();
		List<Genre>  genres  = new ArrayList<>();
		try {
			for (int i = 0; i < authorIDs.length; i++ ) {
				Author author = service.readAuthor(Integer.parseInt(authorIDs[i]));
				authors.add(author);
			}
			book.setAuthors(authors);
			Publisher publisher = service.readPublisher(Integer.parseInt(request.getParameter("publisherID")));
			book.setPublisher(publisher);
			for (int i = 0; i < genreIDs.length; i++) {
				Genre genre = service.readGenre(Integer.parseInt(genreIDs[i]));
				genres.add(genre);
			}
			book.setGenres(genres);
			service.updateBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteBook(HttpServletRequest request){
		Book book = new Book();
		book.setBookId(Integer.parseInt(request.getParameter("bookID")));
		AdminService service = new AdminService();
		try {
			service.deleteBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateGenre(HttpServletRequest request) {
		Genre genre = new Genre();
		genre.setGenreName(request.getParameter("genreName"));
		genre.setGenreId(Integer.parseInt(request.getParameter("genreID")));
		AdminService service = new AdminService();
		try {
			service.updateGenre(genre);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteGenre(HttpServletRequest request) {
		Genre genre = new Genre();
		genre.setGenreId(Integer.parseInt(request.getParameter("genreID")));
		AdminService service = new AdminService();
		try {
			service.deleteGenre(genre);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addGenre(HttpServletRequest request) {
		Genre genre = new Genre();
		genre.setGenreName(request.getParameter("genreName"));
		AdminService service = new AdminService();
		try {
			service.addGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePublisher(HttpServletRequest request) {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(request.getParameter("publisherName"));
		publisher.setPublisherAddress(request.getParameter("publisherAddress"));
		publisher.setPublisherPhone(request.getParameter("publisherPhone"));
		publisher.setPublisherId(Integer.parseInt(request.getParameter("publisherID")));
		AdminService service = new AdminService();
		try {
			service.updatePublisher(publisher);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deletePublisher(HttpServletRequest request) {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(request.getParameter("publisherID")));
		AdminService service = new AdminService();
		try {
			service.deletePublisher(publisher);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addPublisher(HttpServletRequest request) {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(request.getParameter("publisherName"));
		publisher.setPublisherAddress(request.getParameter("publisherAddress"));
		publisher.setPublisherPhone(request.getParameter("publisherPhone"));
		AdminService service = new AdminService();
		try {
			service.addPublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBranch(HttpServletRequest request) {
		Branch branch = new Branch();
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchAddress(request.getParameter("branchAddress"));
		branch.setBranchID(Integer.parseInt(request.getParameter("branchID")));
		AdminService service = new AdminService();
		try {
			service.updateBranch(branch);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteBranch(HttpServletRequest request) {
		Branch branch = new Branch();
		branch.setBranchID(Integer.parseInt(request.getParameter("branchID")));
		AdminService service = new AdminService();
		try {
			service.deleteBranch(branch);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addBranch(HttpServletRequest request) {
		Branch branch = new Branch();
		branch.setBranchName(request.getParameter("branchName"));
		branch.setBranchAddress(request.getParameter("branchAddress"));
		AdminService service = new AdminService();
		try {
			service.addBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBorrower(HttpServletRequest request) {
		Borrower borrower = new Borrower();
		borrower.setBorrowerName(request.getParameter("borrowerName"));
		borrower.setBorrowerAddress(request.getParameter("borrowerAddress"));
		borrower.setBorrowerPhone(request.getParameter("borrowerPhone"));
		borrower.setBorrowerID(Integer.parseInt(request.getParameter("borrowerID")));
		AdminService service = new AdminService();
		try {
			service.updateBorrower(borrower);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteBorrower(HttpServletRequest request) {
		Borrower borrower = new Borrower();
		borrower.setBorrowerID(Integer.parseInt(request.getParameter("borrowerID")));
		AdminService service = new AdminService();
		try {
			service.deleteBorrower(borrower);
			request.setAttribute("message", "Edit Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addBorrower(HttpServletRequest request) {
		Borrower borrower = new Borrower();
		borrower.setBorrowerName(request.getParameter("borrowerName"));
		borrower.setBorrowerAddress(request.getParameter("borrowerAddress"));
		borrower.setBorrowerPhone(request.getParameter("borrowerPhone"));
		AdminService service = new AdminService();
		try {
			service.addBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBookLoan(HttpServletRequest request) {
		BookLoan bookLoan = new BookLoan();
		AdminService service = new AdminService();
		
		try {
			Book book         = service.readBook(Integer.parseInt(request.getParameter("bookID")));
			Branch branch     = service.readBranch(Integer.parseInt(request.getParameter("branchID")));
			Borrower borrower = service.readBorrower(Integer.parseInt(request.getParameter("borrowerID")));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf.parse(request.getParameter("dueDate"));
			java.sql.Date dueDate = new java.sql.Date(date.getTime());
			bookLoan.setBook(book);
			bookLoan.setBranch(branch);
			bookLoan.setBorrower(borrower);
			bookLoan.setDueDate(dueDate);
			service.updateDueDate(bookLoan);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}















