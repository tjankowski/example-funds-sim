package model.service;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import model.domain.User;
import model.service.dao.UsersDAO;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.common.collect.ImmutableList;

public class SimpleUsersManagerTest {
	
	private SimpleUsersManager usersManager;
	@Mock
	private UsersDAO usersDao;
	@Mock
    private WalletService walletService;
	
	@Before
	public void init() {
		initMocks(this);
		
		usersManager = new SimpleUsersManager();
		usersManager.setUsersDao(usersDao);
		usersManager.setWalletService(walletService);
	}
	
	@Test
	public void shouldGetAllUsers() {
		List<User> list = ImmutableList.of(mock(User.class));
		when(usersDao.getAllUsers()).thenReturn(list);
		List<User> result = usersManager.getAllUsers();
		assertThat(result).isEqualTo(list);
		verify(usersDao).getAllUsers();
		verifyNoMoreInteractions(usersDao, walletService);
    }

	@Test
    public void shouldGetUserFomrUserId() {
    	long userId = 1l;
    	User user = mock(User.class);
    	when(usersDao.getUser(userId)).thenReturn(user);
    	User result = usersManager.getUser(userId);
		assertThat(result).isEqualTo(user);
		verify(usersDao).getUser(userId);
		verifyNoMoreInteractions(usersDao, walletService);
    }

	@Test
    public void shouldFetUserFromUserName() {
    	String userName = RandomStringUtils.random(10);
    	User user = mock(User.class);
    	when(usersDao.getUser(userName)).thenReturn(user);
    	User result = usersManager.getUser(userName);
		assertThat(result).isEqualTo(user);
		verify(usersDao).getUser(userName);
		verifyNoMoreInteractions(usersDao, walletService);
    }

     
	@Test
    public void createUser() {
    	User user = mock(User.class);
    	usersManager.createUser(user);
		verify(usersDao, times(2)).saveUser(user);
		verify(walletService).createWallet(user);
		verifyNoMoreInteractions(usersDao, walletService);
    }

}
