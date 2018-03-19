package com.negafuma.gui.services.impl;

import java.util.List;

//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.negafuma.gui.model.SubWork;
import com.negafuma.gui.services.SubWorkService;

@Stateless
@Transactional
public class SubWorkServiceImpl implements SubWorkService{
	@PersistenceContext(name="WorkManagerJTA")
	private EntityManager em;

	String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(SubWork subWork) {
		try {
		em.persist(subWork);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubWork> findAll() {
		List<SubWork> subWorkList = em.createQuery("SELECT s FROM subworks s").getResultList();
		return subWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubWork> findAllWithPictures() {
		List<SubWork> subWorkList = em.createQuery(
		"SELECT s FROM subworks s "
		+ "LEFT JOIN s.pictures p "
//		+ "WITH (i.is_main_image IS true "
//		+ "OR i.is_main_image IS null)"
		).getResultList();
		return subWorkList;
	}

	@Override
	public SubWork find(Integer id) {
		SubWork subWork = em.find(SubWork.class, id);
		return subWork;
	}

	@Override
	public void merge(SubWork subWork) {
		try {
		em.merge(subWork);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@Override
	public void remove(SubWork subWork) {
		try {
		em.remove(subWork);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	/** message treatment **/

	/**
	 * *
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
	 * *
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

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Object[] getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String[] messageInfo) {
		this.messageInfo = messageInfo;
	}

}
