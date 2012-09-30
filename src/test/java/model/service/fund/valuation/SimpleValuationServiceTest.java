package model.service.fund.valuation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.fund.Fund;
import model.service.fund.FundService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.common.collect.ImmutableList;

public class SimpleValuationServiceTest {
	
	private SimpleValuationService service;
	@Mock
	private FundService fundService;
	
	@Before
	public void init() {
		initMocks(this);
		
		service = new SimpleValuationService();
		service.setFundService(fundService);
	}
	
	@Test
	public void shouldEvaluateFunds() {
		Fund fund = mock(Fund.class);
		List<Fund> funds = ImmutableList.of(fund);
		when(fundService.getAllFunds()).thenReturn(funds);
		
		service.evaluateFunds();
		
		verify(fund).evaluate();
		verify(fundService).updateFund(fund);
		verify(fundService).getAllFunds();
		verifyNoMoreInteractions(fundService);
	}

}
