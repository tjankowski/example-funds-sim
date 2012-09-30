package controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.User;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.exceptions.NotEnoughMoneyException;
import model.service.TransactionManager;
import model.service.UsersManager;
import model.service.fund.FundService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.google.common.collect.ImmutableList;

public class FundsControllerTest {
	
	private FundsController controller;
	@Mock
    private TransactionManager transactionManager;
	@Mock
    private UsersManager usersManager;
	@Mock
    private FundService fundService;
	
	@Before
	public void init() {
		initMocks(this);
		
		controller = new FundsController();
		controller.setFundService(fundService);
		controller.setUsersManager(usersManager);
		controller.setTransactionManager(transactionManager);
	}
	
	@Test
	public void shouldShowFunds() {
		List<Fund> funds = ImmutableList.of(mock(Fund.class));
		when(fundService.getAllFunds()).thenReturn(funds);
		Model model = mock(Model.class);
		String view = controller.showFunds(model);
		assertThat(view).isEqualTo("/funds");
		verify(fundService).getAllFunds();
		verify(model).addAttribute("funds",funds);
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, model);
	}
	
	@Test
	public void shouldShowFund() {
		long fundId = 1l;
		Fund fund = mock(Fund.class);
		when(fundService.getFund(fundId)).thenReturn(fund);
		Model model = mock(Model.class);
		String view = controller.showFund(fundId, model);
		assertThat(view).isEqualTo("/fund");
		verify(fundService).getFund(fundId);
		verify(fundService).getFund(fundId);
		verify(model).addAttribute("fund",fund);
		verify(model).addAttribute("fundId",fundId);
		verify(model).addAttribute("units",FundUnit.values());
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, model);
	}
	
	@Test
	public void shouldBuyFundUnits() throws NotEnoughMoneyException {
		FundUnit fundUnit = FundUnit.AFundUnit;
		int count = 10;
		long fundId = 1l;
		String userName = "userName";
		
		SecurityContext context = new SecurityContextImpl();
		Authentication authentication = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		context.setAuthentication(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(userName);
		SecurityContextHolder.setContext(context);
		
		Fund fund = mock(Fund.class);
		User user = mock(User.class);
		when(fundService.getFund(fundId)).thenReturn(fund);
		when(usersManager.getUser(userName)).thenReturn(user);
		String view = controller.buyFundUnits(fundId, fundUnit.name(), count);
		assertThat(view).isEqualTo("redirect:/wallet.html");
		verify(authentication).getPrincipal();
		verify(userDetails).getUsername();
		verify(fundService).getFund(fundId);
		verify(usersManager).getUser(userName);
		verify(transactionManager).buyFundUnits(user, fund, fundUnit, count);
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, authentication, userDetails, user, fund);
	}
	
	@Test
	public void shouldThrowExceptionWhenBuyFundUnits() throws NotEnoughMoneyException {
		FundUnit fundUnit = FundUnit.AFundUnit;
		int count = 10;
		long fundId = 1l;
		String userName = "userName";
		
		SecurityContext context = new SecurityContextImpl();
		Authentication authentication = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		context.setAuthentication(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(userName);
		SecurityContextHolder.setContext(context);
		
		Fund fund = mock(Fund.class);
		User user = mock(User.class);
		when(fundService.getFund(fundId)).thenReturn(fund);
		when(usersManager.getUser(userName)).thenReturn(user);
		doThrow(new NotEnoughMoneyException("No funds")).when(transactionManager).buyFundUnits(user, fund, fundUnit, count);
		String view = controller.buyFundUnits(fundId, fundUnit.name(), count);
		assertThat(view).isEqualTo("redirect:/funds/"+fundId +".html?success=false");
		verify(authentication).getPrincipal();
		verify(userDetails).getUsername();
		verify(fundService).getFund(fundId);
		verify(usersManager).getUser(userName);
		verify(transactionManager).buyFundUnits(user, fund, fundUnit, count);
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, authentication, userDetails, user, fund);
	}

}
