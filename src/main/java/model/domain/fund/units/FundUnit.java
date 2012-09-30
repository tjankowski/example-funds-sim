
package model.domain.fund.units;

import java.math.BigDecimal;

/**
 * Funds units.
 * @author Tomasz Jankowski
 */
public enum FundUnit {

    AFundUnit("Jednostka A", 1.02, 1),
    BFundUnit("Jednostka B", 1, 0.98);

    private String name;
    private BigDecimal buyProvision;
    private BigDecimal sellProvision;

    private FundUnit(String name, double buyProvision, double sellProvision) {
        this.name = name;
        this.buyProvision = BigDecimal.valueOf(buyProvision);
        this.sellProvision = BigDecimal.valueOf(sellProvision);
    }

    /**
     * Returns unit name.
     * @return unit name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns buy provision.
     * @return buy provision
     */
    public BigDecimal getBuyProvision() {
        return buyProvision;
    }

    /**
     * Returns sell provision.
     * @return sell provision
     */
    public BigDecimal getSellProvision() {
        return sellProvision;
    }

}
