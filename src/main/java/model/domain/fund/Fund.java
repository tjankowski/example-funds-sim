package model.domain.fund;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import model.domain.fund.valuation.Valuation;

/**
 * Funds abstract base class.
 * @author Tomasz Jankowski
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Fund implements Serializable {

    private static final long serialVersionUID = -3334236143243493124L;
    private long id;
    @XStreamOmitField
    private Valuation valuation;
    @XStreamOmitField
    private BigDecimal value = new BigDecimal(100);
    private FundType type;

    /**
     * Method to evaluate fund.
     * @return new fund value
     */
    public BigDecimal evaluate() {
        this.setValue(this.getValuation().evaluate(this.getValue()));
        return this.getValue();
    }

    /**
     * Returns fund name.
     * @return fund name
     */
    @Transient
    public abstract String getName();

    /**
     * Returns fund id.
     * @return the fund id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    /**
     * Sets fund id.
     * @param id fund id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns fund valuation.
     * @return the fund valuation
     */
    @Transient
    public Valuation getValuation() {
        return valuation;
    }

    /**
     * Sets fund valuation.
     * @param valuation fund valuation to set
     */
    public void setValuation(Valuation valuation) {
        this.valuation = valuation;
    }

    /**
     * Returns fund value.
     * @return the fund value
     */
    @Column(name = "fund_value")
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets fund value.
     * @param value fund value to set
     */
    public void setValue(BigDecimal value) {
        if (value.intValue() < 0) {
            this.value = BigDecimal.ZERO;
        } else {
            this.value = value;
        }
        this.value = this.value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Returns fund type.
     * @return the fund type
     */
    @Column(name = "fund_type")
    @Enumerated(EnumType.STRING)
    public FundType getType() {
        return type;
    }

    /**
     * Sets fund type.
     * @param type fund type to set
     */
    public void setType(FundType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fund) {
            Fund otherFund = (Fund)obj;
            if(this.id == otherFund.getId() && this.getType().equals(otherFund.getType())) {
                return true;
            }
        }
        return false;
    }
    
}
