package com.negafuma.gui.services.impl;

import java.util.List;

//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.negafuma.gui.model.Work;
import com.negafuma.gui.services.WorkService;

@Stateless
@Transactional
public class WorkServiceImpl implements WorkService{
	@PersistenceContext(name="WorkManagerJTA")
	private EntityManager em;

	String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(Work work) {
		try {
		em.persist(work);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAll() {
		List<Work> testList = em.createQuery("SELECT w FROM works w").getResultList();
		return testList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAllWithSubWorks() {
		List<Work> workList = em.createQuery(
		"SELECT w FROM works w "
		+ "LEFT JOIN w.subworks s "
//		+ "WITH (i.is_main_image IS true "
//		+ "OR i.is_main_image IS null)"
		).getResultList();
		return workList;
	}

	@Override
	public Work find(Integer id) {
		Work work = em.find(Work.class, id);
		return work;
	}

	@Override
	public void merge(Work work) {
		try {
		em.merge(work);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@Override
	public void remove(Work work) {
		try {
		em.remove(work);
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
