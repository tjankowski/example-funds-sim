package model.domain.fund;

import javax.persistence.Entity;
import javax.persistence.Transient;
import model.domain.fund.valuation.RandomMoneyMarketFundValuation;

/**
 * Money Market Fund.
 * @author Tomasz Jankowski
 */
@Entity
public class MoneyMarketFund extends Fund {
    
    private static final long serialVersionUID = -4385036765019284545L;

    public MoneyMarketFund() {
        this.setType(FundType.MoneyMarketFund);
        this.setValuation(new RandomMoneyMarketFundValuation());
    }

    @Transient
    public String getName() {
        return "Money Market Fund";
    }
    
}
