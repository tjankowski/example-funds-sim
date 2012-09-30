package model.domain.fund;

public class SharesFundTest extends FundTest {
	
	@Override
	public Fund getFund() {
		return new SharesFund();
	}

}
