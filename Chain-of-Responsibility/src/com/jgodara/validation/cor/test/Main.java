package com.jgodara.validation.cor.test;

import com.jgodara.validation.cor.Result;
import com.jgodara.validation.cor.Validator;
import com.jgodara.validation.cor.rules.CompositePasswordRule;
import com.jgodara.validation.cor.validators.AlphaNumericValidtor;
import com.jgodara.validation.cor.validators.MinLengthValidator;

public class Main {

	public static void main(String[] args) throws Exception {
		Validator<String> pwRule = new CompositePasswordRule(
				new MinLengthValidator(), new AlphaNumericValidtor());
		Result result = pwRule.validate("Test45");
		if (!result.isOk())
			throw new Exception(result.getMessage());

		System.out.println("All Checks Passed:\n" + result);
	}

}
