package com.jgodara.validation.cor;

public interface Validator<T> {
	
	public Result validate(T value);

}
