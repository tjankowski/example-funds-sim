package model.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

/**
 * User wallet item.
 * @author Tomasz Jankowski
 */
@XStreamAlias("walletItem")
public class WalletItem implements Serializable {
    
    private static final long serialVersionUID = -1588510970994269187L;

    private FundUnit fundUnit;
    private Fund fund;
    private int quantity;

    /**
     * Returns wallet item fund unit.
     * @return the wallet item fund unit
     */
    public FundUnit getFundUnit() {
        return fundUnit;
    }

    /**
     * Sets wallet item fund unit.
     * @param fundUnit wallet item fund unit to set
     */
    public void setFundUnit(FundUnit fundUnit) {
        this.fundUnit = fundUnit;
    }

    /**
     * Returns wallet item quantity.
     * @return the wallet item quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets wallet item quantity.
     * @param quantity wallet item quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns wallet item fund.
     * @return the wallet item fund
     */
    public Fund getFund() {
        return fund;
    }

    /**
     * Sets wallet item fund.
     * @param fund wallet fund quantity to set
     */
    public void setFund(Fund fund) {
        this.fund = fund;
    }

}
