package model.domain.fund.valuation;

import java.math.BigDecimal;

/**
 * Fund valuation strategy.
 * @author Tomasz Jankowski
 */
public interface Valuation {

	/**
	 * Valuation method
	 * @param previous previous value
	 * @return new value
	 */
    public BigDecimal evaluate(BigDecimal previousValue);

}
