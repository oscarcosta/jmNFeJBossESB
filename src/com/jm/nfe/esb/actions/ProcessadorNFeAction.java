package com.jm.nfe.esb.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide;

public class ProcessadorNFeAction extends AbstractActionLifecycle {
	
	private static final Logger LOGGER = Logger.getLogger(ProcessadorNFeAction.class.getName());

	protected ConfigTree _config;

	public ProcessadorNFeAction(ConfigTree config) {
		_config = config;
	}

	public Message process(Message message) throws Exception {
		try {
			Map messageMap = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
			TNFe nfe = (TNFe) messageMap.get("nfe");
			InfNFe infNFe = nfe.getInfNFe();
					
			// TODO Processamento da NFe
			infNFe.setIde(complementaIDE(infNFe));
			infNFe.setId(geraChaveNFe(infNFe.getIde(), infNFe.getEmit()));
			nfe.setInfNFe(infNFe);
			
			HashMap requestMap = new HashMap(); // Substitui mapa anterior
			requestMap.put("nfe", nfe);
			message.getBody().add(requestMap);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro no processamento da nfe: ", e);
			throw e;
		}
		return message;
	}

	private Ide complementaIDE(InfNFe infNFe) {
		Ide ide = infNFe.getIde();
		ide.setCUF("31"); // TODO Anexo IX – Tabelas de UF, Município e País
		ide.setDEmi("0312"); // TODO Data formato "MMAA"
		ide.setCMunFG("3106200"); // TODO Anexo IX – Tabelas de UF, Município e
									// País
		// TODO complementar IDE ...
		return ide;
	}

	private String geraChaveNFe(Ide ide, Emit emit) {
		StringBuilder chaveAcesso = new StringBuilder();
		chaveAcesso.append(ide.getCUF());
		chaveAcesso.append(ide.getDEmi());
		chaveAcesso.append(emit.getCNPJ());
		chaveAcesso.append(ide.getMod());
		chaveAcesso.append(ide.getSerie());
		chaveAcesso.append(ide.getNNF());
		chaveAcesso.append(ide.getTpEmis());
		chaveAcesso.append("01234567"); // TODO Código Numérico que compõe a
										// Chave de Acesso
		chaveAcesso.append("0"); // TODO Dígito Verificador da Chave de Acesso
									// (módulo 11)
		return chaveAcesso.toString();
	}
	
}
