
package model.domain.fund.valuation;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Random Money Market Fund Valuation.
 * @author Tomasz Jankowski
 */
public class RandomMoneyMarketFundValuation implements Valuation{

    public BigDecimal evaluate(BigDecimal previousValue) {
        int change = new Random().nextInt(46) - 5;
        previousValue = previousValue.add(BigDecimal.valueOf(change).divide(BigDecimal.valueOf(100)));
        if (previousValue.intValue() < 0) {
            return BigDecimal.ZERO;
        } else {
            return previousValue;
        }
    }

}
