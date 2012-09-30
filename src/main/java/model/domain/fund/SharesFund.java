package model.domain.fund;

import javax.persistence.Entity;
import javax.persistence.Transient;
import model.domain.fund.valuation.RandomSharesFundValuation;

/**
 * Shares Fund.
 * @author Tomasz Jankowski
 */
@Entity
public class SharesFund extends Fund {

    private static final long serialVersionUID = -6594760242916472746L;

    public SharesFund() {
        this.setType(FundType.SharesFund);
        this.setValuation(new RandomSharesFundValuation());
    }

    @Transient
    public String getName() {
        return "Shares Fund";
    }
}
