package model.service.fund.valuation;

import java.util.List;
import model.domain.fund.Fund;
import model.service.fund.FundService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link ValuationService} implementation.
 * @author Tomasz Jankowski
 */
public class SimpleValuationService implements ValuationService {
    
    private FundService fundService;

    public void evaluateFunds() {
        List<Fund> funds = fundService.getAllFunds();
        if(funds != null) {
            for(Fund fund : funds) {
                evaluateFund(fund);
            }
        }
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    private void evaluateFund(Fund fund) {
        fund.evaluate();
        fundService.updateFund(fund);
    }

    public FundService getFundService() {
        return fundService;
    }

    public void setFundService(FundService fundService) {
        this.fundService = fundService;
    }



}
