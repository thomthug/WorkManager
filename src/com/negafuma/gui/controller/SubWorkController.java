package com.negafuma.gui.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import com.negafuma.gui.model.Medium;
import com.negafuma.gui.model.Nationality;
import com.negafuma.gui.model.SubWork;
import com.negafuma.gui.services.NationalityService;
import com.negafuma.gui.services.SubWorkService;

@ViewScoped
@ManagedBean
public class SubWorkController {

	private SubWork subWork = new SubWork();
	private List<SubWork> subWorkList1 = new ArrayList<SubWork>();

	private List<Nationality> droppedNationalities = new ArrayList<Nationality>();
	private List<Nationality> nationalities;
	private DualListModel<Nationality> nationalityList0;
	private DualListModel<Nationality> nationalityList1;

	private SubWork selectedSubWork = new SubWork();
    private List<SubWork> selectedSubWorks;

    private Medium curMedium = new Medium();

	@EJB
	private SubWorkService subWorkService;

	@EJB
	private NationalityService nationalityService;

	@PostConstruct
    public void init() {
		subWorkList1 = subWorkService.findAll();
		nationalities = nationalityService.findAll();
//		droppedNationalities = new ArrayList<Nationality>();

		//Themes
        List<Nationality> nationalitySource = nationalities;
        List<Nationality> nationalityTarget = new ArrayList<Nationality>();

        nationalityList0 = new DualListModel<Nationality>(nationalitySource, nationalityTarget);
//        Set

    }

	public SubWork getSubWork() {
		return subWork;
	}

	public void setSsubWork(SubWork subWork) {
		this.subWork = subWork;
	}

	public void saveSubWork(SubWork subWork) {

		if(subWork.getWork().getWorkId() > 0) {
//			subWork.setMedium(this.curMedium);
			Set<Nationality> nationalitySetList = new HashSet<Nationality>();

			for(Nationality nat : nationalityList0.getTarget()) {
				nationalitySetList.add(nat);
			}

			subWork.setNationalityCollection(nationalitySetList);
			subWorkService.persist(subWork);
			addMessageInfo(subWorkService.getMessageInfo()[1].toString(),(Severity)subWorkService.getMessageInfo()[0]);
			subWorkList1.add(subWork);
//			curMedium=null;
		}else{
			addErrorMessage("Veuillez renseigner une Oeuvre pour votre sous-oeuvre!");
		}
	}

	public void updateSubWork(SubWork selectedSubWork) {
		subWorkService.merge(selectedSubWork);
//		addMessage(subWorkService.getMessage());
		addMessageInfo(subWorkService.getMessageInfo()[1].toString(),(Severity)subWorkService.getMessageInfo()[0]);
	}

	public void deleteSubWork(SubWork deletedSubWork) {
		subWorkService.remove(deletedSubWork);
//		addMessage(subWorkService.getMessage());
		addMessageInfo(subWorkService.getMessageInfo()[1].toString(),(Severity)subWorkService.getMessageInfo()[0]);
		subWorkList1.remove(deletedSubWork);
	}

	public List<SubWork> getSubWorkList1() {
		return subWorkList1;
	}

	public void onNationalityDrop(DragDropEvent ddEvent) {
        Nationality nationality = ((Nationality) ddEvent.getData());

        droppedNationalities.add(nationality);
        nationalities.remove(nationality);
    }




//	public void setSubWorkList1(List<SubWork> subWorkList1) {
//		this.subWorkList1 = subWorkList1;
//	}

	public SubWork getSelectedSubWork() {
		List<Nationality> nationalitySrc = nationalities;
		nationalitySrc.removeAll(selectedSubWork.getNationalityCollection());

		List<Nationality> nationalitySource = nationalities;
        List<Nationality> nationalityTarget = new ArrayList<Nationality>();
        nationalityTarget.addAll(selectedSubWork.getNationalityCollection());

        nationalityList1 = new DualListModel<Nationality>(nationalitySource, nationalityTarget);



		return selectedSubWork;
	}

	public void setSelectedSubWork(SubWork selectedSubWork) {
		this.selectedSubWork = selectedSubWork;
	}

	public List<SubWork> getSelectedSubWorks() {
		return selectedSubWorks;
	}

	public void setSelectedSubWorks(List<SubWork> selectedSubWorks) {
		this.selectedSubWorks = selectedSubWorks;
	}

	public void setSubWork(SubWork subWork) {
		this.subWork = subWork;
	}

	public List<Nationality> getDroppedNationalities() {
		return droppedNationalities;
	}

	public void setDroppedNationalities(List<Nationality> droppedNationalities) {
		this.droppedNationalities = droppedNationalities;
	}

	public List<Nationality> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<Nationality> nationalities) {
		this.nationalities = nationalities;
	}



	public DualListModel<Nationality> getNationalityList1() {
		return nationalityList1;
	}

	public void setNationalityList1(DualListModel<Nationality> nationalityList1) {
		this.nationalityList1 = nationalityList1;
	}

	public DualListModel<Nationality> getNationalityList0() {
		return nationalityList0;
	}

	public void setNationalityList0(DualListModel<Nationality> nationalityList0) {
		this.nationalityList0 = nationalityList0;
	}

	public void setSubWorkList1(List<SubWork> subWorkList1) {
		this.subWorkList1 = subWorkList1;
	}

	public Medium getCurMedium() {
		return curMedium;
	}

	public void setCurMedium(Medium curMedium) {
		this.curMedium = curMedium;
	}

	/** Ajax function **/

//	public void ConfirmAction(ActionEvent actionEvent) {
//        addMessage("Action Validé!!");
//    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addErrorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessageInfo(String summary,Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Nationality Selected", ((SubWork) event.getObject()).getSubWorkTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Nationality Unselected", ((SubWork) event.getObject()).getSubWorkTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    ///////////////////////////////// pick list /////////////////////////////////

    public void onTransfer(TransferEvent event) {
//        StringBuilder builder = new StringBuilder();
//        for(Object item : event.getItems()) {
//        	System.out.println("on Transfer !!!");
//            builder.append(((Theme) item).getName()).append("<br />");
//        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

}
