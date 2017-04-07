package com.jgodara.validation.cor.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.jgodara.validation.cor.Result;
import com.jgodara.validation.cor.Validator;
import com.jgodara.validation.cor.results.CompositeResult;

public class CompositePasswordRule extends ArrayList<Validator>
		implements Validator<String> {
	
	public CompositePasswordRule(Collection<Validator<?>> rules) {
		addAll(rules);
	}
	
	public CompositePasswordRule(Validator<?> ... rules) {
		addAll(Arrays.asList(rules));
	}

	@Override
	public CompositeResult validate(String value) {
		CompositeResult result = new CompositeResult(size());
		for (Validator rule : this) {
			Result tempResult = rule.validate(value);
			if (!tempResult.isOk())
				result.put(rule, tempResult);
		}
		return result;
	}

}