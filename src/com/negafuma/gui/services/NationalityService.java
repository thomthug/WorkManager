package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.Nationality;

public interface NationalityService {

	public void persist(Nationality nationality);
	public List<Nationality> findAll();
	public Nationality find(Integer id);
	public void merge(Nationality nationality);
	public void remove(Nationality nationality);
	public List<Nationality> findAllWithSubworks();

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
