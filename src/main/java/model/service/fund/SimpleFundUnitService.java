package model.service.fund;

import java.math.BigDecimal;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

/**
 * {@link FundUnitService} implementation.
 * @author Tomasz Jankowski
 */
public class SimpleFundUnitService implements FundUnitService {

    public BigDecimal buy(Fund fund, FundUnit unit) {
             return fund.getValue().multiply(unit.getBuyProvision()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal sell(Fund fund, FundUnit unit) {
             return fund.getValue().multiply(unit.getSellProvision()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
