package model.domain.fund;

import javax.persistence.Entity;
import javax.persistence.Transient;
import model.domain.fund.valuation.RandomStableGrowthFundValuation;

/**
 * Stable Growth Fund.
 * @author Tomasz Jankowski
 */
@Entity
public class StableGrowthFund extends Fund {

    private static final long serialVersionUID = 1781563629683103749L;

    public StableGrowthFund() {
        this.setType(FundType.StableGrowthFund);
        this.setValuation(new RandomStableGrowthFundValuation());
    }

    @Transient
    public String getName() {
        return "Stable Growth Fund";
    }
}
