package com.negafuma.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.negafuma.gui.model.Picture;
import com.negafuma.gui.services.PictureService;


@ApplicationScoped
@ManagedBean
public class PictureController {

    @EJB
    private PictureService pictureService;

    List<Picture> pictureList = new ArrayList<Picture>();

    @PostConstruct
    public void init() {
    	pictureList = pictureService.findAll();
    }

    public List<Picture> getPictureList() {
		return pictureList;
	}

    /*** upload ***/

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {

    	 if(file != null) {
	    	Picture image = new Picture();

	    	image.setPictureTitle(file.getFileName().substring(0, file.getFileName().length()-4));
	    	image.setPictureContent(file.getContents());
	    	image.setPictureMimeType(file.getFileName().substring(file.getFileName().length()-3, file.getFileName().length()));
	    	image.setPictureIsMain(false);

	    	pictureService.persist(image);

            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
//            addMessage(mediumService.getMessage());
//    		addMessageInfo(mediumService.getMessageInfo()[1].toString(),(Severity)mediumService.getMessageInfo()[0]);

            pictureList.add(image);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

    	UploadedFile uploadedFile = event.getFile();

    	Picture image = new Picture();

    	image.setPictureTitle(uploadedFile.getFileName().substring(0, uploadedFile.getFileName().length()-4));
    	image.setPictureContent(uploadedFile.getContents());
    	image.setPictureMimeType(uploadedFile.getFileName().substring(uploadedFile.getFileName().length()-3, uploadedFile.getFileName().length()));
    	image.setPictureIsMain(false);

    	pictureService.persist(image);

        FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
//        addMessage(mediumService.getMessage());
//		addMessageInfo(mediumService.getMessageInfo()[1].toString(),(Severity)mediumService.getMessageInfo()[0]);

        pictureList.add(image);
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessageInfo(String summary,Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}