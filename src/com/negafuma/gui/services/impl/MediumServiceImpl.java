package com.negafuma.gui.services.impl;

import java.util.List;

//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.negafuma.gui.model.Medium;
import com.negafuma.gui.services.MediumService;

@Stateless
@Transactional
public class MediumServiceImpl implements MediumService{
	@PersistenceContext(name="WorkManagerJTA")
	private EntityManager em;

	String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(Medium medium) {
		try {
		em.persist(medium);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medium> findAll() {
		List<Medium> testList = em.createQuery("SELECT m FROM mediums m").getResultList();
		return testList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medium> findAllWithSubworks() {

		List<Medium> mediumList = em.createQuery(
		"SELECT m FROM mediums m "
		+ "LEFT JOIN m.subworks s "
//		+ "WITH (i.is_main_image IS true "
//		+ "OR i.is_main_image IS null)"
		).getResultList();
		return mediumList;
	}

	@Override
	public Medium find(Integer id) {
		Medium medium = em.find(Medium.class, id);
		return medium;
	}

	@Override
	public void merge(Medium medium) {
		em.merge(medium);
	}

	@Override
	public void remove(Medium medium) {
		em.remove(medium);
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
