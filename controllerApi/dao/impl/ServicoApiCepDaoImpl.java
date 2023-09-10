package controllerApi.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import controllerApi.dao.ServicoApiCepDao;
import controllerApi.exception.ApiException;
import model.Endereco;

public class ServicoApiCepDaoImpl implements ServicoApiCepDao {

	private String webService = "https://brasilapi.com.br/api/cep/v2/";
	private int codigoSucesso = 200;
	
	public Endereco buscarEndereco(String cep) throws ApiException {
		String urlParaChamada = webService + cep;
		
		try {
			URL url = new URL(urlParaChamada);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			
			if (conexao.getResponseCode() != codigoSucesso) 
				throw new RuntimeException("HTTP error code: " + conexao.getResponseCode());
				
				BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
				String jsonEmString = Util.converterJsonEmString(resposta);
				
				Gson gson = new Gson();
				Endereco cepEndereco = gson.fromJson(jsonEmString, Endereco.class);
			
			return cepEndereco;
			
		} catch(Exception e) {
			
			throw new ApiException("Erro: " + e.getMessage());
			
		}
	}
}
