package model.domain.fund;

import javax.persistence.Entity;
import javax.persistence.Transient;
import model.domain.fund.valuation.RandomBondFundValuation;

/**
 * Bond Fund.
 * @author Tomasz Jankowski
 */
@Entity
public class BondFund extends Fund {
    
    private static final long serialVersionUID = 2910029351103044232L;

    public BondFund() {
        this.setType(FundType.BondFund);
        this.setValuation(new RandomBondFundValuation());
    }

    @Transient
    public String getName() {
        return "Bond Fund";
    }

}
