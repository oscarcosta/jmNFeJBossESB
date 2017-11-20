package com.jm.nfe.esb.actions;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecepcaoResponseAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger.getLogger(RecepcaoResponseAction.class.getName());
	
	protected ConfigTree _config;
	
	public RecepcaoResponseAction(ConfigTree config) {
		_config = config;
	}
	
	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			String retorno = (String) messageMap.get("nfeRecepcaoLote2Result");
			
			// TODO Processa recepção da NFe

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro na recepção da nfe: ", e);
			throw e;
		}
		return message;
	}
	
}
