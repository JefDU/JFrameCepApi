package controller.exception;

public class BuscarEnderecoException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public BuscarEnderecoException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
