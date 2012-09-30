package model.domain.fund;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;

import model.domain.fund.valuation.Valuation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public abstract class FundTest {

	private Fund fund;
	@Mock
	private Valuation valuation;
	
	public abstract Fund getFund();
	
	@Before
	public void init() {
		initMocks(this);
		fund = getFund();
		fund.setValuation(valuation);
	}
	
	@Test
	public void shouldSetValueToZeroAndScale() {
		fund.setValue(BigDecimal.valueOf(-2));
		assertThat(fund.getValue()).isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
	}
	
	@Test
	public void shouldSetValueAndScale() {
		fund.setValue(BigDecimal.TEN);
		assertThat(fund.getValue()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
	}
	
	@Test
	public void shouldEvaluate() {
		BigDecimal expected = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		when(valuation.evaluate(fund.getValue())).thenReturn(BigDecimal.TEN);
		BigDecimal result = fund.evaluate();
		assertThat(result).isEqualTo(expected);
	}
	
}
