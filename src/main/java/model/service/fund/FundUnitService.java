/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service.fund;

import java.math.BigDecimal;

import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

/**
 * Interface for interaction with user {@link FundUnit}.
 * Provides all functions related to the {@link FundUnit}.
 * @author Tomasz Jankowski
 */
public interface FundUnitService {

	/**
	 * Valuation method of buy cost.
	 * @param fund fund
	 * @param unit fund unit
	 * @return fund unit buy value
	 */
    public BigDecimal buy(Fund fund, FundUnit unit);

    /**
	 * Valuation method of sell cost.
	 * @param fund fund
	 * @param unit fund unit
	 * @return fund unit sell value
	 */
    public BigDecimal sell(Fund fund, FundUnit unit);

}
