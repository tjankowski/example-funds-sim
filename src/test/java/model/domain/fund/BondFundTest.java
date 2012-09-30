package model.domain.fund;

public class BondFundTest extends FundTest {
	
	@Override
	public Fund getFund() {
		return new BondFund();
	}

}
