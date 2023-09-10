package controllerApi.dao;

import controllerApi.exception.ApiException;
import model.Endereco;

public interface ServicoApiCepDao {
	
	public Endereco buscarEndereco(String cep) throws ApiException;

}
