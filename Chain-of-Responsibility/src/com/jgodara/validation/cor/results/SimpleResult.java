package com.jgodara.validation.cor.results;

import com.jgodara.validation.cor.Result;

public class SimpleResult implements Result {

	private String message;
	private boolean isOk;

	public SimpleResult() {
		this(null, true);
	}

	public SimpleResult(boolean isOk) {
		this(null, isOk);
	}

	public SimpleResult(String message, boolean isOk) {
		if (message != null && message.length() > 0)
			this.message = message;
		else
			message = "Validation " + (isOk ? "Successful" : "Failed");

		this.isOk = isOk;
	}

	@Override
	public boolean isOk() {
		return isOk;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "SimpleResult[" + isOk + ", " + getMessage() + "]";
	}

}
