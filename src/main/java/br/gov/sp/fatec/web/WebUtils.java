/**
 * 
 */
package br.gov.sp.fatec.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Emanuel
 *
 */
public class WebUtils {

	public static void incluirMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
						mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static void incluirMensagemAviso(String mensagem) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem,
								mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static void incluirMensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
								mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static String recuperarCausa(Throwable t) {
		Throwable causa = t;
		while (causa.getCause() != null) {
			causa = causa.getCause();
		}
		return causa.getMessage();
	}

}
