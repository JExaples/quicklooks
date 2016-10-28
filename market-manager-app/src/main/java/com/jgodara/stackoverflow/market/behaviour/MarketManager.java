package com.jgodara.stackoverflow.market.behaviour;

import org.apache.log4j.Logger;

import com.jgodara.stackoverflow.market.model.Stock;

/**
 * @author Jay
 *
 *         {@link MarketManager} represents events which would affect the stocks
 *         in different ways.
 */
public class MarketManager {

	private static final Logger logger = Logger.getLogger(MarketManager.class);

	// The name of the event.
	private String name = "unkown";
	
	// Modifier value of the event.
	private float modifier = 0f;
	
	// The listener to listen for the modifications.
	private Modifier modificationListener;
	
	// The target stock object towards which the action is directed.
	private Stock target;

	/**
	 * Executes the market event.
	 */
	public void executeAction() {
		// Check for required values.
		if (target == null) {
			logger.error("A target must be set before executing action.");
			return;
		}
		
		if (modificationListener == null) {
			logger.error("A modification listener must be set before executing the action.");
			return;
		}

		logger.info("Executing action \"" + name + "\" with modifier "
				+ modifier + "...");

		// And then invoke the modification listener.
		modificationListener.modify(target, modifier);
	}

	/////////////////////////////////////////////////////
	///////////		Getter/Setters		/////////////////
	/////////////////////////////////////////////////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getModifier() {
		return modifier;
	}

	public void setModifier(float modifier) {
		this.modifier = modifier;
	}

	public Modifier getModificationListener() {
		return modificationListener;
	}

	public void setModificationListener(Modifier modificationListener) {
		this.modificationListener = modificationListener;
	}

	public Stock getTarget() {
		return target;
	}

	public void setTarget(Stock target) {
		this.target = target;
	}

	public static Logger getLogger() {
		return logger;
	}

}
