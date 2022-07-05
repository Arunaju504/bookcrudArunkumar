package com.servlet.comtroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.factory.ConnectionFactory;
import com.model.book.Book;
import com.service.book.BookService;

/**
 * Servlet implementation class BookServet
 */
@WebServlet("/")
public class BookServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private BookService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	try {
		con = ConnectionFactory.getConnection();
		service = new BookService(con);
		System.out.println("BD Connection"+con);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		switch (url) {
		case "/":
			showPage(request,response);
			break;
		case "/edit":
			editPage(request,response);
			break;
		case "/update":
			updatePage(request,response);
			break;
			
			case "/delete":
				deletePage(request,response);
				break;

		default:
			break;
		}
	}

	private void deletePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strnumber =request.getParameter("id");
		int bookNumber =Integer.parseInt(strnumber);
		Book bookDel =service.delete(bookNumber);
		List<Book> bookList = service.findAll();
		request.setAttribute("books", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/show.jsp");
		dispatcher.forward(request, response);
		
	}

	private void updatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strnumber =request.getParameter("bookNumber");
		int bookNumber =Integer.parseInt(strnumber);
		String bookName =request.getParameter("bookName");
		String bookAuthor =request.getParameter("bookAuthor");
		String strPrice =request.getParameter("bookPrice");
		double bookPrice =Double.parseDouble(strPrice);
		Book entity =new Book(bookNumber,bookName,bookAuthor,bookPrice);
		Book books =service.update(entity);
		request.setAttribute("book", books);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
		
	}

	private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strnumber =request.getParameter("id");
		int id =Integer.parseInt(strnumber);
		Book bookedit =service.findById(id);
		
		request.setAttribute("booksToEdit", bookedit);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add.jsp");
		dispatcher.forward(request, response);
	}

	private void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = service.findAll();
		request.setAttribute("books", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/show.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strnumber =request.getParameter("bookNumber");
		int bookNumber =Integer.parseInt(strnumber);
		String bookName =request.getParameter("bookName");
		String bookAuthor =request.getParameter("bookAuthor");
		String strPrice =request.getParameter("bookPrice");
		double bookPrice =Double.parseDouble(strPrice);
		Book entity =new Book(bookNumber,bookName,bookAuthor,bookPrice);
		Book books =service.add(entity);
		request.setAttribute("book", books);request.setAttribute("message", "One Book Added books");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add.jsp");
		dispatcher.forward(request, response);
		
	}

	

}
