package com.jm.nfe.esb.actions;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

public class ValidadorNFeAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger.getLogger(ValidadorNFeAction.class.getName());
	
	protected ConfigTree _config;
	
	public ValidadorNFeAction(ConfigTree config) {
		_config = config;
	}
	
	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			String xml = (String) messageMap.get("nfeMessage");
			
			// TODO Validar o XML da NFe
		    /*SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    Schema schema = factory.newSchema(new StreamSource(ValidadorNFeAction.class.getResourceAsStream("//schemas//PL_006q//enviNFe_v2.00.xsd")));
		    Validator validator = schema.newValidator();
		    validator.validate(new StreamSource(new ByteArrayInputStream(xml.getBytes())));*/
		    
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro no validador da nfe: ", e);
			throw e;
		}
		return message;
	}
	
}
