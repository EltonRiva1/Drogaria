package br.com.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	public static void adicionarMensagemSucesso(String string) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, string, string);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMessage);
	}

	public static void adicionarMensagemErro(String string) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, string, string);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMessage);
	}
}
