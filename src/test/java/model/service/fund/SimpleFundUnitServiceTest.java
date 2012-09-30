package model.service.fund;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;

import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;

import org.junit.Before;
import org.junit.Test;

public class SimpleFundUnitServiceTest {
	
	private SimpleFundUnitService service;
	
	@Before
	public void init() {
		service = new SimpleFundUnitService();
	}
	
	@Test
	public void shouldReturnValidBuyValue() {
		BigDecimal expected = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		Fund fund = mock(Fund.class);
		when(fund.getValue()).thenReturn(BigDecimal.TEN);
		BigDecimal result = service.buy(fund, FundUnit.BFundUnit); 
		assertThat(result).isEqualTo(expected);
		verify(fund).getValue();
		verifyNoMoreInteractions(fund);
	}
	
	@Test
	public void shouldReturnValidSellValue() {
		BigDecimal expected = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		Fund fund = mock(Fund.class);
		when(fund.getValue()).thenReturn(BigDecimal.TEN);
		BigDecimal result = service.sell(fund, FundUnit.AFundUnit); 
		assertThat(result).isEqualTo(expected);
		verify(fund).getValue();
		verifyNoMoreInteractions(fund);
	}

}
