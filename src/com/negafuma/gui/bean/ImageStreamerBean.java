package com.negafuma.gui.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.negafuma.gui.model.Medium;
import com.negafuma.gui.model.Nationality;
import com.negafuma.gui.model.Picture;
import com.negafuma.gui.services.MediumService;
import com.negafuma.gui.services.NationalityService;
import com.negafuma.gui.services.PictureService;


@ApplicationScoped
@ManagedBean
public class ImageStreamerBean {

    @EJB
    private PictureService imageService;

    @EJB
    private NationalityService nationalityService;

    @EJB
    private MediumService mediumService;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	String imageId = context.getExternalContext().getRequestParameterMap().get("pictureId");
        	Picture image = imageService.find(Integer.valueOf(imageId));
        	if(imageId == null || imageId.equals("")) {
    			return new DefaultStreamedContent(new ByteArrayInputStream("resources/images/emblem.jpg".getBytes(StandardCharsets.UTF_8)));
//        	  StandardCharsets.UTF_16.encode(str).array()
        	}else {
        		return new DefaultStreamedContent(new ByteArrayInputStream(image.getPictureContent()));
        	}
        }
    }

    public StreamedContent getNationalityLogo() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	String nationalityId = context.getExternalContext().getRequestParameterMap().get("nationalityId");
        	Nationality image = nationalityService.find(Integer.valueOf(nationalityId));
        	if(nationalityId == null || nationalityId.equals("")) {
    			return new DefaultStreamedContent(new ByteArrayInputStream("resources/images/emblem.jpg".getBytes(StandardCharsets.UTF_8)));
        	}else {
        		return new DefaultStreamedContent(new ByteArrayInputStream(image.getNationalityLogo()));
        	}
        }
    }

    public StreamedContent getMediumLogo() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	String mediumId = context.getExternalContext().getRequestParameterMap().get("mediumId");
        	Medium image = mediumService.find(Integer.valueOf(mediumId));
        	if(mediumId == null || mediumId.equals("")) {
    			return new DefaultStreamedContent(new ByteArrayInputStream("resources/images/emblem.jpg".getBytes(StandardCharsets.UTF_8)));
        	}else {
        		return new DefaultStreamedContent(new ByteArrayInputStream(image.getMediumLogo()));
        	}
        }
    }
}