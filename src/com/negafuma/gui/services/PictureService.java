package com.negafuma.gui.services;

import java.util.List;

import com.negafuma.gui.model.Picture;

public interface PictureService {

	public void persist(Picture picture);
	public List<Picture> findAll();
	public Picture find(Integer id);
	public void merge(Picture picture);
	public void remove(Picture picture);

	/** message treatment **/
	public String getMessage();
	public Object[] getMessageInfo();
}
