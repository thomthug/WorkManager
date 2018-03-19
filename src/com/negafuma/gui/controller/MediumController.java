package com.negafuma.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;

import com.negafuma.gui.model.Medium;
import com.negafuma.gui.services.MediumService;

@ViewScoped
@ManagedBean
public class MediumController {

	private Medium medium = new Medium();
	private List<Medium> mediumList1 = new ArrayList<Medium>();
	private UploadedFile file;
	private UploadedFile fileUp;
	private Medium selectedMedium = new Medium();
    private List<Medium> selectedMediums;
    private Part filePart;
    private String fileContent;
    private byte[] fileContents;

	@EJB
	private MediumService mediumService;

	@PostConstruct
    public void init() {
		mediumList1 = mediumService.findAll();
		fileUp = null;
		fileContents = null;
    }

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public void saveMedium(Medium medium) {
		if(file != null) {
			medium.setMediumLogo(this.file.getContents());
			file = null;
		}
		mediumService.persist(medium);
//		addMessage(mediumService.getMessage());
//		addMessageInfo(mediumService.getMessageInfo()[1].toString(),(Severity)mediumService.getMessageInfo()[0]);
		mediumList1.add(medium);
	}

	public void updateMedium(Medium selectedMedium) {
		if(fileUp != null) {
//			selectedNationality.setNationalityLogo(fileUp.getContents());
			fileUp = null;
		}

		if(fileContents != null) {
			selectedMedium.setMediumLogo(fileContents);
			fileContents = null;
		}
		mediumService.merge(selectedMedium);
//		addMessageInfo(nationalityService.getMessageInfo()[1].toString(),(Severity)nationalityService.getMessageInfo()[0]);
	}

	public void handleFileUpload(FileUploadEvent event) {

    	UploadedFile uploadedFile = event.getFile();
    	setFileUp(uploadedFile);
    	this.fileUp = uploadedFile;
    	this.fileContents = event.getFile().getContents();

    }

	public List<Medium> getMediumList1() {
		return mediumList1;
	}

	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Medium Selected", ((Medium) event.getObject()).getMediumTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Medium Unselected", ((Medium) event.getObject()).getMediumTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//	public void setMediumList1(List<Medium> mediumList1) {
//		this.mediumList1 = mediumList1;
//	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFile getFileUp() {
		return fileUp;
	}

	public void setFileUp(UploadedFile fileUp) {
		this.fileUp = fileUp;
	}

	public Medium getSelectedMedium() {
		return selectedMedium;
	}

	public void setSelectedMedium(Medium selectedMedium) {
		this.selectedMedium = selectedMedium;
	}

	public List<Medium> getSelectedMediums() {
		return selectedMediums;
	}

	public void setSelectedMediums(List<Medium> selectedMediums) {
		this.selectedMediums = selectedMediums;
	}

	public Part getFilePart() {
		return filePart;
	}

	public void setFilePart(Part filePart) {
		this.filePart = filePart;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public byte[] getFileContents() {
		return fileContents;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}

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
