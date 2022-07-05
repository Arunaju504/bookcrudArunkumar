package com.crud.repository;

import java.util.List;

public interface CrudRepository<T> {

	public T add(T t);
	public List<T> findAll();
	public T findById(int id);
	public T delete(int id);
	public T update(T t);
	
	
}
