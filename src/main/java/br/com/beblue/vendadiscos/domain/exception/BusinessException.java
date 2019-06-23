package br.com.beblue.vendadiscos.domain.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -5406458453680436050L;

	public BusinessException(final String msg) {
		super(msg);
	}
}
