package com.jm.nfe.esb.actions;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

public class ConsultaResponseAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger.getLogger(ConsultaResponseAction.class.getName());
	
	protected ConfigTree _config;
	
	public ConsultaResponseAction(ConfigTree config) {
		_config = config;
	}
	
	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			
			// TODO Processa Consulta da NFe
		
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro na consulta da nfe: ", e);
			throw e;
		}
		return message;
	}

}
