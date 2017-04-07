package com.jgodara.validation.cor.validators;

import com.jgodara.validation.cor.Result;
import com.jgodara.validation.cor.Validator;
import com.jgodara.validation.cor.results.SimpleResult;

public class AlphaNumericValidtor implements Validator<String> {

	private final Result FAILED;

	public AlphaNumericValidtor() {
		FAILED = new SimpleResult("The password should be alphanumeric.", false);
	}

	@Override
	public Result validate(String value) {
		return isAlphaNumeric(value) ? Result.OK : FAILED;
	}

	private boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		return s.matches(pattern);
	}
	
	@Override
	public String toString() {
		return "AlphaNumericValidator";
	}

}
