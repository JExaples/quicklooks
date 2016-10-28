package com.jgodara.stackoverflow.market.behaviour.impl;

import org.apache.log4j.Logger;

/**
 * @author Jay
 * 
 *         Modifier implementation for Public Opinion.
 */
import com.jgodara.stackoverflow.market.behaviour.Modifier;
import com.jgodara.stackoverflow.market.model.Stock;

/**
 * @author Jay
 * 
 *         Modifier implementation for Public Opinion.
 */
public class PublicOpinionModifier implements Modifier {

	private static final Logger logger = Logger.getLogger(PublicOpinionModifier.class);

	public void modify(Stock target, float modifier) {
		logger.info("Modifying public opinion of stock " + target);
		target.setPublicOpinion(target.getPublicOpinion() + modifier);	
	}

}
