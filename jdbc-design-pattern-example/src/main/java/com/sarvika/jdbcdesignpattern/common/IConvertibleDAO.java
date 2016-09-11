package com.sarvika.jdbcdesignpattern.common;

import java.util.Map;

public interface IConvertibleDAO<T> {

	public T mapToObject(Map<String, Object> data);
	
	public Map<String, Object> objectToMap(T object);
	
}
