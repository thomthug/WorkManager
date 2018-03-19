package com.negafuma.gui.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.negafuma.gui.model.Nationality;

public class NationalitySelectionBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3591088292580542970L;
	private Nationality selectedNationality;
    private List<Nationality> selectedNationalites;

	public Nationality getSelectedNationality() {
        return selectedNationality;
    }

    public void setSelectedNationality(Nationality selectedNationality) {
        this.selectedNationality = selectedNationality;
    }

    public List<Nationality> getSelectedNationalites() {
        return selectedNationalites;
    }

    public void setSelectedNationalites(List<Nationality> selectedNationalites) {
        this.selectedNationalites = selectedNationalites;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Nationality Selected", ((Nationality) event.getObject()).getNationalityTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Nationality Unselected", ((Nationality) event.getObject()).getNationalityTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
