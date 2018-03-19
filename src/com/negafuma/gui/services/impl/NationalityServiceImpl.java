package com.negafuma.gui.services.impl;

import java.util.List;

//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.negafuma.gui.model.Nationality;
import com.negafuma.gui.services.NationalityService;

@Stateless
@Transactional
public class NationalityServiceImpl implements NationalityService{
	@PersistenceContext(name="WorkManagerJTA")
	private EntityManager em;

	String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(Nationality nationality) {
		try {
		em.persist(nationality);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nationality> findAll() {
		List<Nationality> nationalityList = em.createQuery("SELECT n FROM nationalities n").getResultList();
		return nationalityList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nationality> findAllWithSubworks() {

		List<Nationality> nationalityList = em.createQuery(
		"SELECT n FROM nationalities n "
		+ "LEFT JOIN n.subworks s "
//		+ "WITH (i.is_main_image IS true "
//		+ "OR i.is_main_image IS null)"
		).getResultList();
		return nationalityList;
	}

	@Override
	public Nationality find(Integer id) {
		Nationality nationality = em.find(Nationality.class, id);
		return nationality;
	}

	@Override
	public void merge(Nationality nationality) {
		em.merge(nationality);
	}

	@Override
	public void remove(Nationality nationality) {
		em.remove(nationality);
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
