package controllerApi.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

	public static String converterJsonEmString(BufferedReader bufferedReader) throws IOException {
		String resposta, jsonEmString = "";
		while((resposta = bufferedReader.readLine()) != null) {
			jsonEmString += resposta;
		}
		return jsonEmString;
	}
}
