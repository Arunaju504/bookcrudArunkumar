package com.service.book;

import java.sql.Connection;
import java.util.List;

import com.crud.repository.BookRepository;
import com.crud.repository.CrudRepository;
import com.model.book.Book;

public class BookService {
	
	private Connection con;
	private CrudRepository<Book> repo;
	public BookService(Connection con) {
		super();
		this.con = con;
		this.repo = new BookRepository(con);
	}
	
	public Book add(Book book) {
		return this.repo.add(book);	
	}
	
	public List<Book> findAll(){
		return this.repo.findAll();
	}
	
	public Book findById(int id) {
		return this.repo.findById(id);
	}
	public Book delete(int id) {
		return this.repo.delete(id);
		
	}
	public Book update(Book t) {
		return this.repo.update(t);
	}
	
}
