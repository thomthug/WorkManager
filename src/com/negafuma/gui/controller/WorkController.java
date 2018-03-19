package com.negafuma.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.negafuma.gui.model.Work;
import com.negafuma.gui.services.WorkService;

@ManagedBean
public class WorkController {

	private Work work = new Work();
	private Work work1 = new Work();
	private List<Work> workList1 = new ArrayList<Work>();

	@EJB
	private WorkService workService;

	@PostConstruct
    public void init() {
		workList1 = workService.findAll();
    }

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Work getWork1() {
		return work1;
	}

	public void setWork1(Work work1) {
		this.work1 = work1;
	}

	public void saveWork(Work work) {
		workService.persist(work);
		addMessage(workService.getMessage());
		addMessageInfo(workService.getMessageInfo()[1].toString(),(Severity)workService.getMessageInfo()[0]);
		workList1.add(work);
	}

	public void updateWork(Work work1) {
		workService.merge(work1);
		addMessage(workService.getMessage());
		addMessageInfo(workService.getMessageInfo()[1].toString(),(Severity)workService.getMessageInfo()[0]);
	}
	public void deleteWork(Work work1) {
		workService.remove(work1);
		addMessage(workService.getMessage());
		addMessageInfo(workService.getMessageInfo()[1].toString(),(Severity)workService.getMessageInfo()[0]);
		workList1.remove(work1);
	}

//	public Work loaWork(Work work) {
//		this.work = work;
//		return work;
//	}

	public List<Work> getWorkList1() {
		return workList1;
	}

//	public void setWorkList1(List<Work> workList1) {
//		this.workList1 = workList1;
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
