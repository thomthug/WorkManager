package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.Work;

public interface WorkService {

	public void persist(Work work);
	public List<Work> findAll();
	public Work find(Integer id);
	public void merge(Work work);
	public void remove(Work work);
	public List<Work> findAllWithSubWorks();

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
