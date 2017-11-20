package com.jm.nfe.esb.actions;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TEnviNFe;

public class GeradorNFeAction extends AbstractActionLifecycle {

	private static final Logger LOGGER = Logger
			.getLogger(GeradorNFeAction.class.getName());

	protected ConfigTree _config;

	public GeradorNFeAction(ConfigTree config) {
		_config = config;
	}

	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			TNFe nfe = (TNFe) messageMap.get("nfe");

			TEnviNFe tEnviNFe = new TEnviNFe();
			tEnviNFe.setIdLote("01"); // TODO numero lote
			tEnviNFe.getNFe().add(nfe);

			// TODO Gerar o XML da NFe
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(new JAXBElement<TEnviNFe>(new QName("enviNFe"), TEnviNFe.class, tEnviNFe), writer);

			HashMap requestMap = new HashMap(); // Substitui mapa anterior
			requestMap.put("nfeMessage", writer.toString());
			message.getBody().add(requestMap);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro no gerador da nfe: ", e);
			throw e;
		}
		return message;
	}

}
