package model.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import model.domain.User;
import model.domain.Wallet;
import model.domain.fund.Fund;
import model.domain.fund.units.FundUnit;
import model.exceptions.NotEnoughMoneyException;
import model.service.fund.FundUnitService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SimpleTransactionManagerTest {
	
	private SimpleTransactionManager manager;
	@Mock
	private FundUnitService fundUnitService;
	@Mock
    private WalletService walletService;
	@Mock
	private User user;
	@Mock
	private Fund fund;
	
	@Before
	public void init() {
		initMocks(this);
		
		manager = new SimpleTransactionManager();
		manager.setFundUnitService(fundUnitService);
		manager.setWalletService(walletService);
	}
	
	@Test
	public void shouldBuyFundUnits() throws NotEnoughMoneyException {
		int count = 2;
		FundUnit unit = FundUnit.AFundUnit;
		Wallet wallet = mock(Wallet.class);
		when(wallet.getMoney()).thenReturn(BigDecimal.valueOf(20));
		when(walletService.getWallet(user)).thenReturn(wallet);
		when(fundUnitService.buy(fund, unit)).thenReturn(BigDecimal.TEN);
		
		manager.buyFundUnits(user, fund, unit, count);
		
		verify(walletService).getWallet(user);
		verify(fundUnitService).buy(fund, unit);
		verify(wallet).getMoney();
		verify(wallet).setMoney(BigDecimal.ZERO);
		verify(wallet).addFundUnits(fund, unit, count);
		verify(walletService).saveWallet(user, wallet);
		verifyNoMoreInteractions(fundUnitService, walletService, user, fund, wallet);
    }
	
	@Test(expected = NotEnoughMoneyException.class)
	public void shouldThrowExceptionWhenBuyFundUnits() throws NotEnoughMoneyException {
		int count = 2;
		FundUnit unit = FundUnit.AFundUnit;
		Wallet wallet = mock(Wallet.class);
		when(wallet.getMoney()).thenReturn(BigDecimal.TEN);
		when(walletService.getWallet(user)).thenReturn(wallet);
		when(fundUnitService.buy(fund, unit)).thenReturn(BigDecimal.TEN);
		
		manager.buyFundUnits(user, fund, unit, count);
		
		verify(walletService).getWallet(user);
		verify(fundUnitService).buy(fund, unit);
		verifyNoMoreInteractions(fundUnitService, walletService, user, fund, wallet);
    }
	
	@Test
	public void shouldSellFundUnits(){
		int count = 1;
		FundUnit unit = FundUnit.AFundUnit;
		Wallet wallet = mock(Wallet.class);
		when(wallet.removeFundUnits(fund, unit, count)).thenReturn(1);
		when(wallet.getMoney()).thenReturn(BigDecimal.TEN);
		when(walletService.getWallet(user)).thenReturn(wallet);
		when(fundUnitService.sell(fund, unit)).thenReturn(BigDecimal.TEN);
		
		manager.sellFundUnits(user, fund, unit, count);
		verify(wallet).getMoney();
		verify(walletService).getWallet(user);
		verify(fundUnitService).sell(fund, unit);
		verify(wallet).setMoney(BigDecimal.valueOf(20));
		verify(wallet).removeFundUnits(fund, unit, count);
		verify(walletService).saveWallet(user, wallet);
		verifyNoMoreInteractions(fundUnitService, walletService, user, fund, wallet);
    }
}
