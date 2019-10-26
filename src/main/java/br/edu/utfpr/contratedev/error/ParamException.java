package br.edu.utfpr.contratedev.error;

public class ParamException extends RuntimeException {

    public ParamException() {
        super("Algum parâmetro é inválido.");
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}