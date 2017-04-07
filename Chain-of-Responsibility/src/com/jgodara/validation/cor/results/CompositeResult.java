package com.jgodara.validation.cor.results;

import java.util.HashMap;

import com.jgodara.validation.cor.Result;
import com.jgodara.validation.cor.Validator;

public class CompositeResult extends HashMap<Validator, Result> implements
		Result {
	
	private Integer appliedCount;
	
	public CompositeResult(Integer appliedCount) {
		this.appliedCount = appliedCount;
	}

	@Override
	public boolean isOk() {
		boolean isOk = true;
		for (Result r : values()) {
			isOk = r.isOk();
			if (!isOk)
				break;
		}
		return isOk;
	}

	@Override
	public String getMessage() {
		return toString();
	}
	
	public Integer failCount() {
		return size();
	}
	
	public Integer passCount() {
		return appliedCount - size();
	}

}
