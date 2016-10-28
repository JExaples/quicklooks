package com.jgodara.stackoverflow.market.behaviour.impl;

import org.apache.log4j.Logger;

import com.jgodara.stackoverflow.market.behaviour.Modifier;
import com.jgodara.stackoverflow.market.model.Stock;

/**
 * @author Jay
 * 
 *         Modifier implementation for Value.
 */
public class ValueModifier implements Modifier {

	private static final Logger logger = Logger.getLogger(ValueModifier.class);
	
	public void modify(Stock target, float modifier) {
		logger.info("Modifying value of stock " + target);
		target.setValue(target.getValue() + modifier);	
	}

}
