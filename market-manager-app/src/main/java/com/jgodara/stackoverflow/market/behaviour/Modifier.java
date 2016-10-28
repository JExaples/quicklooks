package com.jgodara.stackoverflow.market.behaviour;

import com.jgodara.stackoverflow.market.model.Stock;

/**
 * @author Jay
 *
 *         This interface is used to listen for market actions fired through
 *         {@link MarketManager} and directs it to an instance of {@link Stock}.
 */
public interface Modifier {

	public void modify(Stock taget, float modifier);

}
