package controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.User;
import model.domain.Wallet;
import model.domain.WalletItem;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.service.TransactionManager;
import model.service.UsersManager;
import model.service.WalletService;
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

public class WalletControllerTest {
	
	private WalletController controller;
	@Mock
    private TransactionManager transactionManager;
	@Mock
    private UsersManager usersManager;
	@Mock
    private WalletService walletService;
	@Mock
	private FundService fundService;
	
	@Before
	public void init() {
		initMocks(this);
		
		controller = new WalletController();
		controller.setFundService(fundService);
		controller.setUsersManager(usersManager);
		controller.setTransactionManager(transactionManager);
		controller.setWalletService(walletService);
	}
	
	@Test
	public void shouldShowWallet() {
		String userName = "userName";
		
		SecurityContext context = new SecurityContextImpl();
		Authentication authentication = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		context.setAuthentication(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(userName);
		SecurityContextHolder.setContext(context);
		
		Wallet wallet = mock(Wallet.class);
		Model model = mock(Model.class);
		User user = mock(User.class);
		when(walletService.getWallet(user)).thenReturn(wallet);
		when(usersManager.getUser(userName)).thenReturn(user);
		String view = controller.showWallet(model);
		assertThat(view).isEqualTo("/wallet");
		verify(walletService).getWallet(user);
		verify(usersManager).getUser(userName);
		verify(model).addAttribute("wallet",wallet);
		verify(model).addAttribute("user",user);
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, walletService, model, user, wallet);
	}
	
	@Test
	public void shouldSellFundUnit() {
		String userName = "userName";
		int count = 3;
		int position = 0;
		long fundId = 1l;
		FundUnit fundUnit = FundUnit.AFundUnit;
		
		SecurityContext context = new SecurityContextImpl();
		Authentication authentication = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		context.setAuthentication(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(userName);
		SecurityContextHolder.setContext(context);
		
		Wallet wallet = mock(Wallet.class);
		WalletItem walletItem = mock(WalletItem.class);
		User user = mock(User.class);
		Fund fund = mock(Fund.class);
		List<WalletItem> items = ImmutableList.of(walletItem);
		when(walletItem.getFund()).thenReturn(fund);
		when(fund.getId()).thenReturn(fundId);
		when(walletItem.getFundUnit()).thenReturn(fundUnit);
		when(wallet.getItems()).thenReturn(items);
		when(walletService.getWallet(user)).thenReturn(wallet);
		when(usersManager.getUser(userName)).thenReturn(user);
		when(fundService.getFund(fundId)).thenReturn(fund);
		
		String view = controller.sellFundUnits(position, count);
		assertThat(view).isEqualTo("redirect:/wallet.html");
		verify(wallet).getItems();
		verify(walletItem).getFund();
		verify(walletItem).getFundUnit();
		verify(fund).getId();
		verify(walletService).getWallet(user);
		verify(usersManager).getUser(userName);
		verify(fundService).getFund(fundId);
		verify(transactionManager).sellFundUnits(user, fund, fundUnit, count);
		verifyNoMoreInteractions(transactionManager, fundService, usersManager, walletService, user, wallet, walletItem, fund);
	}

}
