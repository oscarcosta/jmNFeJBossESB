package com.jm.nfe.esb.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

public class NfeRequestAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger.getLogger(NfeRequestAction.class.getName());
	
	protected ConfigTree _config;

	public NfeRequestAction(ConfigTree config) {
		_config = config;
	}

	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			String xml = (String) messageMap.get("nfeMessage");
			
			HashMap requestMap = new HashMap();
			requestMap.put("nfeRecepcaoLote2.nfeRecepcaoLote2nfeCabecMsg", xml);
			message.getBody().add(requestMap);
			System.out.println("Request map: " + requestMap.toString());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro ao gerar o request: ", e);
			throw e;
		}
		return message;
	}
}
