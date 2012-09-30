package model.domain.fund;

public class StableGrowthFundTest extends FundTest {
	
	@Override
	public Fund getFund() {
		return new StableGrowthFund();
	}

}
