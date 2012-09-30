
package model.service.dao;

import java.util.List;
import model.domain.fund.Fund;

/**
 * Data Access Object interface for {@link Fund} objects
 * @author Tomasz Jankowski
 */
public interface FundDAO {

	/**
     * Returns fund with given id.
     * @param fundId the fund id
     * @return the fund with given id
     */
     public Fund getFund(long fundId);

     /**
      * Returns list of all fund.
      * @return list of all fund
      */
     public List<Fund> getAllFunds();

     /**
      * Saves given fund.
      * @param fund the fund to save
      */
     public void saveFund(Fund fund);

     /**
      * Update given fund.
      * @param fund the fund to update
      */
     public void updateFund(Fund fund);

}
