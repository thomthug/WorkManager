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

import com.negafuma.gui.model.Nationality;
import com.negafuma.gui.services.NationalityService;

@ViewScoped
@ManagedBean
public class NationalityController {

	private Nationality nationality = new Nationality();
	private List<Nationality> nationalityList1 = new ArrayList<Nationality>();
	private UploadedFile file;
	private UploadedFile fileUp;
	private Nationality selectedNationality = new Nationality();
    private List<Nationality> selectedNationalities;
    private Part filePart;
    private String fileContent;
    private byte[] fileContents;

	@EJB
	private NationalityService nationalityService;

	@PostConstruct
    public void init() {
		nationalityList1 = nationalityService.findAll();
		fileUp = null;
		fileContents = null;
    }

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public void saveNationality(Nationality nationality) {
		if(file != null) {
			nationality.setNationalityLogo(this.file.getContents());
			file = null;
		}
		nationalityService.persist(nationality);
//		addMessage(nationalityService.getMessage());
//		addMessageInfo(nationalityService.getMessageInfo()[1].toString(),(Severity)nationalityService.getMessageInfo()[0]);
		nationalityList1.add(nationality);
	}

	public void updateNationality(Nationality selectedNationality) {
		if(fileUp != null) {
//			selectedNationality.setNationalityLogo(fileUp.getContents());
			fileUp = null;
		}

		if(fileContents != null) {
			selectedNationality.setNationalityLogo(fileContents);
			fileContents = null;
		}
		nationalityService.merge(selectedNationality);
//		addMessage(nationalityService.getMessage());
//		addMessageInfo(nationalityService.getMessageInfo()[1].toString(),(Severity)nationalityService.getMessageInfo()[0]);
	}

	public void upload() {

   	 if(file != null) {
//	    	Picture image = new Picture();
   		 	getSelectedNationality();
           FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
           FacesContext.getCurrentInstance().addMessage(null, message);
//   		addMessageInfo(mediumService.getMessageInfo()[1].toString(),(Severity)mediumService.getMessageInfo()[0]);
   	 	}
     }

	public void handleFileUpload(FileUploadEvent event) {

    	UploadedFile uploadedFile = event.getFile();
    	setFileUp(uploadedFile);
    	this.fileUp = uploadedFile;
    	this.fileContents = event.getFile().getContents();


//    	this.selectedNationality.setNationalityLogo(uploadedFile.getContents());
//    	nationalityService.merge(this.selectedNationality);
//    	nationalityService.persist(this.nationality);

//        FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        addMessage(mediumService.getMessage());
//		addMessageInfo(mediumService.getMessageInfo()[1].toString(),(Severity)mediumService.getMessageInfo()[0]);
    }

	public UploadedFile getFileUp() {
		return fileUp;
	}

	public void setFileUp(UploadedFile fileUp) {
		this.fileUp = fileUp;
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

	public void resetField() {
		nationality.setNationalityTitle(null);
	}

	public List<Nationality> getNationalityList1() {
		return nationalityList1;
	}



//	public void setNationalityList1(List<Nationality> nationalityList1) {
//		this.nationalityList1 = nationalityList1;
//	}

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

    //####################################################################################################//



	public Nationality getSelectedNationality() {
        return selectedNationality;
    }

    public void setSelectedNationality(Nationality selectedNationality) {
        this.selectedNationality = selectedNationality;
    }

    public List<Nationality> getSelectedNationalities() {
        return selectedNationalities;
    }

    public void setSelectedNationalities(List<Nationality> selectedNationalities) {
        this.selectedNationalities = selectedNationalities;
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
