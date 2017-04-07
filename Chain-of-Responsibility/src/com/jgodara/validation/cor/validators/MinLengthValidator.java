package com.jgodara.validation.cor.validators;

import com.jgodara.validation.cor.Result;
import com.jgodara.validation.cor.Validator;
import com.jgodara.validation.cor.results.SimpleResult;

public class MinLengthValidator implements Validator<String> {

	private final Result FAILED;
	private Integer minLength;

	public MinLengthValidator() {
		this(8);
	}

	public MinLengthValidator(Integer minLength) {
		this.minLength = minLength;
		FAILED = new SimpleResult("Password must be at least " + minLength
				+ " characters long.", false);
	}

	@Override
	public Result validate(String value) {
		return value.length() >= minLength ? Result.OK : FAILED;
	}
	
	@Override
	public String toString() {
		return "MinLengthValidator";
	}

}
