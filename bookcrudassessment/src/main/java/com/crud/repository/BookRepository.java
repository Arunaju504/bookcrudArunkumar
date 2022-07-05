package com.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.book.Book;

public class BookRepository implements CrudRepository<Book>{

	private Connection con;
	private List<Book> bookList ;
	
	public BookRepository(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Book add(Book t) {
		int added =0;
		String sql ="insert into book values(?,?,?,?)";
		try {
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(1, t.getBookNumber());
			st.setString(2, t.getBookName());
			st.setString(3, t.getBookAuthor());
			st.setDouble(4, t.getBookPrice());
			added =st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return added ==1 ? t : null;
	}

	@Override
	public List<Book> findAll()  {
		String sql ="select * from book";
		List<Book> bookList =new ArrayList<>();;
		try {
			PreparedStatement st =con.prepareStatement(sql);
			ResultSet rs =st.executeQuery();
			
			while(rs.next()){
				
				int bookNumber =rs.getInt(1);
				String bookName =rs.getString(2);
				String bookAuthor =rs.getString(3);
				double bookPrice =rs.getDouble(4);
				Book book = new Book(bookNumber, bookName, bookAuthor, bookPrice);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public Book findById(int id) {
		String sql ="select * from book where id =?";
		Book bookById =null;
		try {
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(1, id);
				ResultSet rs =st.executeQuery();
				
				while(rs.next()){
					
					int bookNumber =rs.getInt(1);
					String bookName =rs.getString(2);
					String bookAuthor =rs.getString(3);
					double bookPrice =rs.getDouble(4);
					bookById = new Book(bookNumber, bookName, bookAuthor, bookPrice);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bookById;
	}

	@Override
	public Book delete(int id) {
		int deleted =0;
		String sql ="delete from book where id=?";
		try {
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(1, id);
			deleted =st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted ==1 ? findById(id) : null;
	}

	@Override
	public Book update(Book t) {
		int updated =0;
		String sql ="update book set name=?, author=? ,price=? where id=? ";
		try {
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(4, t.getBookNumber());
			st.setString(1, t.getBookName());
			st.setString(2, t.getBookAuthor());
			st.setDouble(3, t.getBookPrice());
			updated =st.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return updated ==1 ? t : null;
	}

}
