package com.jgodara.stackoverflow.market.behaviour.impl;

import org.apache.log4j.Logger;

import com.jgodara.stackoverflow.market.behaviour.Modifier;
import com.jgodara.stackoverflow.market.model.Stock;

/**
 * @author Jay
 * 
 *         Modifier implementation for Public Presence.
 */
public class PublicPresenceModifier implements Modifier {

	private static final Logger logger = Logger.getLogger(PublicPresenceModifier.class);

	public void modify(Stock target, float modifier) {
		logger.info("Modifying public presense of stock " + target);
		target.setPublicPresence(target.getPublicPresence() + modifier);	
	}

}
