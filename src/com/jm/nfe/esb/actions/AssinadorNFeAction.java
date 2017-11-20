package com.jm.nfe.esb.actions;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;
import org.w3._2000._09.xmldsig.SignatureType;

import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;

public class AssinadorNFeAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger.getLogger(AssinadorNFeAction.class.getName());
	
	protected ConfigTree _config;
	
	public AssinadorNFeAction(ConfigTree config) {
		_config = config;
	}
	
	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			TNFe nfe = (TNFe) messageMap.get("nfe");
			InfNFe infNFe = nfe.getInfNFe();
			
			// TODO Assinar a NFe
			nfe.setSignature(new SignatureType());
			
			messageMap.put("nfe", nfe);
			message.getBody().add(messageMap);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro no assinador da nfe: ", e);
			throw e;
		}
		return message;
	}
	
}