package controllerApi.exception;

public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public ApiException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
