package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.Category;

public interface CategoryService {

	public void persist(Category test);
	public List<Category> findAll();
	public Category find(Integer id);
	public void merge(Category test);
	public void remove(Category test);
	public List<Category> findAllWithSubworks();
	public List<Category> findAllWithSubcategories();

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
