package com.negafuma.gui.services.impl;

import java.util.List;

//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.negafuma.gui.model.Category;
import com.negafuma.gui.services.CategoryService;

@Stateless
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@PersistenceContext(name="WorkManagerJTA")
	private EntityManager em;

	String message = "Action Validé!!";
	Object[] messageInfo = new Object[2];

	@Override
	public void persist(Category category) {
		try {
		em.persist(category);
		getMessage(true,null);
		getMessageInfo(true,null);
		}catch (Exception e) {
			getMessage(false,"Action Non Validé, à investiguer!");
			getMessageInfo(false,"Action Non Validé, à investiguer!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		List<Category> categoryList = em.createQuery("SELECT c FROM categories c").getResultList();
		return categoryList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAllWithSubworks() {
		List<Category> categoryList = em.createQuery(
		"SELECT c FROM categories c "
		+ "LEFT JOIN c.subworks s "
//		+ "WITH (i.is_main_image IS true "
//		+ "OR i.is_main_image IS null)"
		).getResultList();
		return categoryList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAllWithSubcategories() {
		List<Category> categoryList = em.createQuery(
		"SELECT c FROM categories c "
		+ "LEFT JOIN c.subcategories sc "
//				+ "WITH (i.is_main_image IS true "
//				+ "OR i.is_main_image IS null)"
		).getResultList();
		return categoryList;
	}

	@Override
	public Category find(Integer id) {
		Category category = em.find(Category.class, id);
		return category;
	}

	@Override
	public void merge(Category category) {
		em.merge(category);
	}

	@Override
	public void remove(Category category) {
		em.remove(category);
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
