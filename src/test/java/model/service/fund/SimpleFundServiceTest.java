package model.service.fund;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.fund.Fund;
import model.service.dao.FundDAO;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.common.collect.ImmutableList;

public class SimpleFundServiceTest {
	
	private SimpleFundService fundService;
	@Mock
	private FundDAO fundDAO;

	@Before
	public void init() {
		initMocks(this);
		
		fundService = new SimpleFundService();
		fundService.setFundDAO(fundDAO);
	}
	
	@Test
	public void shouldGetFund() {
		long fundId = 1l;
		Fund fund = mock(Fund.class);
		when(fundDAO.getFund(fundId)).thenReturn(fund);
		
		Fund result = fundService.getFund(fundId);
		assertThat(result).isEqualTo(fund);
		verify(fundDAO).getFund(fundId );
		verifyNoMoreInteractions(fundDAO);
	}
	
	@Test
	public void shouldGetAlFunds() {
		Fund fund = mock(Fund.class);
		List<Fund> funds = ImmutableList.of(fund);
		when(fundDAO.getAllFunds()).thenReturn(funds);
		
		List<Fund> result = fundService.getAllFunds();
		assertThat(result).isEqualTo(funds);
		verify(fundDAO).getAllFunds();
		verifyNoMoreInteractions(fundDAO);
	}
	
	@Test
	public void shouldSaveFund() {
		Fund fund = mock(Fund.class);
		
		fundService.saveFund(fund);
		verify(fundDAO).saveFund(fund);
		verifyNoMoreInteractions(fundDAO, fund);
	}
	
	@Test
	public void shouldUpdateFund() {
		Fund fund = mock(Fund.class);
		
		fundService.updateFund(fund);
		verify(fundDAO).updateFund(fund);
		verifyNoMoreInteractions(fundDAO, fund);
	}

}
