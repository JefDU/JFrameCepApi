package controller;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import controller.exception.BuscarEnderecoException;
import controllerApi.dao.ServicoApiCepDao;
import controllerApi.dao.impl.ServicoApiCepDaoImpl;
import controllerApi.exception.ApiException;

public class BuscarEnderecoController {
	
	private ServicoApiCepDao dao;
	
	public BuscarEnderecoController() {
		dao = new ServicoApiCepDaoImpl();
	}

    public MaskFormatter mascaraCep(JFormattedTextField cepMask) throws BuscarEnderecoException {
    	String formato = "#####-###";
    	MaskFormatter maskCep;
		try {
			maskCep = new MaskFormatter(formato);
			maskCep.install(cepMask);
		} catch (ParseException e) {
			throw new BuscarEnderecoException("Erro ao formatar CEP: " + e.getMessage());
		}
    	return maskCep;
    }
    
    private StringBuilder enderecoCepFormatado(String cep) throws ApiException {
    	StringBuilder formatoCep = new StringBuilder(dao.buscarEndereco(cep).getCep());	
    	formatoCep.insert(5, "-");
    	
    	return formatoCep;		
    }
    
    private StringBuilder enderecoRuaFormatado(String cep) throws ApiException {
        StringBuilder formatoRua = new StringBuilder(dao.buscarEndereco(cep).getStreet());
        formatoRua.insert(3, ":");
        
        return formatoRua;
    }
    
    
    public String buscarEndereco(String cep) throws BuscarEnderecoException {
    	StringBuilder sb = new StringBuilder();
    	try {
			sb.append("CEP: " + enderecoCepFormatado(cep) + "\n");
			sb.append(enderecoRuaFormatado(cep) + "\n");
			sb.append("Cidade: " + dao.buscarEndereco(cep).getCity() + "\n");
			sb.append("Estado: " + dao.buscarEndereco(cep).getState() + "\n");
			
		} catch (ApiException e) {
			throw new BuscarEnderecoException("Error: " + e.getMessage());
		}
    	
    	return sb.toString();
    }
}
