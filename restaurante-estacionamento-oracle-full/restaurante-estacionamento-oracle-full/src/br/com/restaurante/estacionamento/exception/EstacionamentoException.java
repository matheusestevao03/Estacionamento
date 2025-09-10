package br.com.restaurante.estacionamento.exception;

public class EstacionamentoException extends Exception {
    public EstacionamentoException(String message) {
        super(message);
    }

    public EstacionamentoException(String message, Throwable cause) {
        super(message, cause);
    }
}
