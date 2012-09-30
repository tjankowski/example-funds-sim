package model.domain.fund.valuation;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class RandomBondFundValuationTest {
	
	private RandomBondFundValuation valuation;
	
	@Before
	public void init() {
		valuation = new RandomBondFundValuation();
	}
	
	@Test
	public void shouldReturnZero() {
		BigDecimal result = valuation.evaluate(BigDecimal.valueOf(-50));
		assertThat(result).isEqualTo(BigDecimal.ZERO);
	}

}
