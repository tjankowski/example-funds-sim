
package model.service;

import model.domain.fund.units.FundUnit;
import model.domain.User;
import model.domain.fund.Fund;
import model.exceptions.NotEnoughMoneyException;

/**
 * Money transaction manager.
 * @author Tomasz Jankowski
 */
public interface TransactionManager {

	/**
	 * Method to buy fund units
	 * @param user user
	 * @param fund fund
	 * @param unit fund unit
	 * @param count quantity
	 * @throws NotEnoughMoneyException when a user tries to spend more money than has
	 */
	public void buyFundUnits(User user, Fund fund, FundUnit unit, int count) throws NotEnoughMoneyException;

	/**
	 * Method to sell fund units
	 * @param user user
	 * @param fund fund
	 * @param unit fund unit
	 * @param count quantity
	 */
	public void sellFundUnits(User user, Fund fund, FundUnit unit, int count);

}