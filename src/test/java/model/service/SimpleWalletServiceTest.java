package model.service;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import model.domain.User;
import model.domain.Wallet;
import model.service.dao.WalletDAO;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class SimpleWalletServiceTest {
	
	private SimpleWalletService walletService;
	@Mock
	private WalletDAO walletDAO;
	@Mock
	private User user;
	@Mock
	private Wallet wallet;
	
	@Before
	public void init() {
		initMocks(this);
		
		walletService = new SimpleWalletService();
		walletService.setWalletDAO(walletDAO);
	}
	
	@Test
	public void shouldSaveWallet() {
		walletService.saveWallet(user, wallet);
		verify(walletDAO).saveWallet(user, wallet);
		verifyNoMoreInteractions(walletDAO, wallet, user);
	}
	
	@Test
	public void shouldGetWallet() {
		String filePath = RandomStringUtils.random(10);
		when(user.getWalletFilePath()).thenReturn(filePath);
		when(walletDAO.getWallet(filePath)).thenReturn(wallet);
		Wallet result = walletService.getWallet(user);
		assertThat(result).isEqualTo(wallet);
		verify(user).getWalletFilePath();
		verify(walletDAO).getWallet(filePath);
		verifyNoMoreInteractions(walletDAO, wallet, user);
	}
	
	@Test
	public void createWallet() {
		long randomId = Long.valueOf(RandomStringUtils.randomNumeric(2));
		
        ArgumentCaptor<Wallet> argumentCaptor = ArgumentCaptor.forClass(Wallet.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		when(user.getId()).thenReturn(randomId);
		Wallet result = walletService.createWallet(user);
		verify(user).getId();
		verify(walletDAO).saveWallet(userCaptor.capture(), argumentCaptor.capture());
		verify(user).setWalletFilePath(Long.toString(randomId)+".xml");
		assertThat(result).isEqualTo(argumentCaptor.getValue());
		verifyNoMoreInteractions(walletDAO, wallet, user);
	}

}
