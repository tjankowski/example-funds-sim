package model.domain.fund;

public class BalancedFundTest extends FundTest {
	
	@Override
	public Fund getFund() {
		return new BalancedFund();
	}

}
