package model.domain.fund;

public class MoneyMarketFundTest extends FundTest {
	
	@Override
	public Fund getFund() {
		return new MoneyMarketFund();
	}

}
