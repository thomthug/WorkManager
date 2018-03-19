package com.negafuma.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.negafuma.gui.model.Category;
import com.negafuma.gui.services.CategoryService;

@ManagedBean
public class CategoryController {

	private Category category = new Category();
	private List<Category> categoryList1 = new ArrayList<Category>();

	@EJB
	private CategoryService categoryService;

	@PostConstruct
    public void init() {
		categoryList1 = categoryService.findAll();
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void saveCategory(Category category) {
		categoryService.persist(category);
//		addMessage(mediumService.getMessage());
		addMessageInfo(categoryService.getMessageInfo()[1].toString(),(Severity)categoryService.getMessageInfo()[0]);
		categoryList1.add(category);
	}

	public List<Category> getCategoryList1() {
		return categoryList1;
	}

//	public void setMediumList1(List<Medium> mediumList1) {
//		this.mediumList1 = mediumList1;
//	}

	/** Ajax function **/

//	public void ConfirmAction(ActionEvent actionEvent) {
//        addMessage("Action Validé!!");
//    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessageInfo(String summary,Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
