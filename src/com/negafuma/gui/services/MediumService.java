package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.Medium;

public interface MediumService {

	public void persist(Medium medium);
	public List<Medium> findAll();
	public Medium find(Integer id);
	public void merge(Medium medium);
	public void remove(Medium medium);
	public List<Medium> findAllWithSubworks();

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
