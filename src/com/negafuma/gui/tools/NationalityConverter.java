package com.negafuma.gui.tools;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.logging.Logger;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter("com.negafuma.gui.tools.NationalityConverter")
public class NationalityConverter implements Converter{

	private static final Logger log = Logger.getLogger(NationalityConverter.class.getName());

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		PickList  p = (PickList) arg1;
		DualListModel dl = (DualListModel) p.getValue();
		return dl.getSource().get(Integer.valueOf(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		PickList  p = (PickList) arg1;
		DualListModel dl = (DualListModel) p.getValue();
		return  String.valueOf(dl.getSource().indexOf(arg2));
	}


//	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
//        PickList  p = (PickList) component;
//        DualListModel dl = (DualListModel) p.getValue();
//        return dl.getSource().get(Integer.valueOf(submittedValue));
//    }
//
//    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
//        PickList  p = (PickList) component;
//        DualListModel dl = (DualListModel) p.getValue();
//        return  String.valueOf(dl.getSource().indexOf(value));
//    }

}
