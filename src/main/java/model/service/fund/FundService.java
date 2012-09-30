
package model.service.fund;

import java.util.List;

import model.domain.fund.Fund;

/**
 * Interface for interaction with user {@link Fund}.
 * Provides all functions related to the {@link Fund}.
 * @author Tomasz Jankowski
 */
public interface FundService {

	/**
     * Returns fund with given contest id.
     * @param fundId the fund id
     * @return the fund with given id
     */
    public Fund getFund(long fundId);

    /**
     * Returns list of all funds.
     * @return list of all funds
     */
    public List<Fund> getAllFunds();

    /**
     * Saves given fund.
     * @param fund the fund to save
     */
    public void saveFund(Fund fund);

    /**
     * Updates given fund.
     * @param fund the fund to update
     */
    public void updateFund(Fund fund);

}
