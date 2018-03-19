package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.SubWork;

public interface SubWorkService {

	public void persist(SubWork subWork);
	public List<SubWork> findAll();
	public SubWork find(Integer id);
	public void merge(SubWork subWork);
	public void remove(SubWork subWork);
	public List<SubWork> findAllWithPictures();

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
