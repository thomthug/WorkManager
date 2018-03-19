package com.negafuma.gui.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.negafuma.gui.model.Picture;
import com.negafuma.gui.services.PictureService;

@Stateless
public class PictureServiceImpl implements PictureService {

    @PersistenceContext(name="WorkManagerJTA")
    private EntityManager em;

    String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(Picture picture) {
		try {
			em.persist(picture);
			getMessage(true,null);
			getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
			e.printStackTrace();
		}
	}

    @Override
	public Picture find(Integer pictureId) {
    	Picture picture = em.find(Picture.class, pictureId);
        return picture;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Picture> findAll() {
    	List<Picture> pictureList = em.createQuery("SELECT p FROM pictures p").getResultList();
		return pictureList;
    }

	@Override
	public void merge(Picture picture) {
		em.merge(picture);
	}

	@Override
	public void remove(Picture picture) {
		em.remove(picture);
	}

	/**** message treatment ****/

	/**
	 * @param 1: message
	 */
	public String getMessage(Boolean bool,String msg){
//		String message = "Action Validé!!";
		if(!bool) {
			message=msg;
		}else {
			message = "Action Validé!!";
		}
		return message;
	}

	/**
	 * @param 1: severity , 2: message
	 */
	public Object[] getMessageInfo(Boolean bool,String msg) {
		if(!bool) {
			messageInfo[0]=FacesMessage.SEVERITY_ERROR;
			messageInfo[1]=msg;
		}else {
			messageInfo[0]=FacesMessage.SEVERITY_INFO;
			messageInfo[1] = "Action Validé!!";
		}
		return messageInfo;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Object[] getMessageInfo() {
		return messageInfo;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageInfo(String[] messageInfo) {
		this.messageInfo = messageInfo;
	}
}
