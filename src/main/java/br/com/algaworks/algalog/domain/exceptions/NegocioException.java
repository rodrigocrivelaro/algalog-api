package br.com.algaworks.algalog.domain.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String msg) {
        super(msg);
    }
}
