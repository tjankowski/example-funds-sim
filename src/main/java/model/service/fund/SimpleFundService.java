
package model.service.fund;

import java.util.List;
import model.domain.fund.Fund;
import model.service.dao.FundDAO;

/**
 * {@link FundService} implementation.
 * @author Tomasz Jankowski
 */
public class SimpleFundService implements FundService {

    private FundDAO fundDAO;

    public Fund getFund(long fundId) {
        return getFundDAO().getFund(fundId);
    }

    public List<Fund> getAllFunds() {
        return getFundDAO().getAllFunds();
    }

    public void saveFund(Fund fund) {
        getFundDAO().saveFund(fund);
    }

    public void updateFund(Fund fund) {
        getFundDAO().updateFund(fund);
    }

    public FundDAO getFundDAO() {
        return fundDAO;
    }

    public void setFundDAO(FundDAO fundDAO) {
        this.fundDAO = fundDAO;
    }

}
