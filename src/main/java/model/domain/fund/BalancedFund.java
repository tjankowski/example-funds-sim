package model.domain.fund;

import javax.persistence.Entity;
import javax.persistence.Transient;
import model.domain.fund.valuation.RandomBalancedFundValuation;

/**
 * Balanced Fund.
 * @author Tomasz Jankowski
 */
@Entity
public class BalancedFund extends Fund{
    
    private static final long serialVersionUID = 2576421688764202778L;

    public BalancedFund() {
        this.setType(FundType.BalancedFund);
        this.setValuation(new RandomBalancedFundValuation());
    }

    @Transient
    public String getName() {
        return "Balanced Fund";
    }
    
}
