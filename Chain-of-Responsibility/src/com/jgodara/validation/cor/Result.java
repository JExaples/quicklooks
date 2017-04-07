package com.jgodara.validation.cor;

public interface Result {

	public static final Result OK = new Result() {

		@Override
		public boolean isOk() {
			return true;
		}

		@Override
		public String getMessage() {
			return "OK";
		}

	};

	public boolean isOk();

	public String getMessage();

}
