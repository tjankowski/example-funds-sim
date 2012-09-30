package controller.support;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import model.domain.User;
import model.service.UsersManager;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.Errors;

public class UserValidatorTest {
	
	private UserValidator userValidator;
	
	@Mock
	private UsersManager usersManager;
	@Mock
	private User user;
	@Mock
	private Errors errors;

	
	@Before
	public void init() {
		initMocks(this);
		
		userValidator = new UserValidator();
		userValidator.setUsersManager(usersManager);
		
	}
	
	@Test
	public void shouldSupportUserClass() {
		assertThat(userValidator.supports(User.class)).isTrue();
	}
	
	@Test
	public void shouldSupportOtherClass() {
		assertThat(userValidator.supports(Object.class)).isFalse();
	}
	
	@Test
	public void shouldValidate() {
		String validName = RandomStringUtils.random(6);
		String validPassword = RandomStringUtils.random(6);
		
		when(user.getUserName()).thenReturn(validName);
		when(user.getPassword()).thenReturn(validPassword);
		when(usersManager.getUser(validName)).thenReturn(null);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(validName);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
	
	@Test
	public void shouldRejectWithNameToShortError() {
		String name = RandomStringUtils.random(3);
		String validPassword = RandomStringUtils.random(6);
		
		when(user.getUserName()).thenReturn(name);
		when(user.getPassword()).thenReturn(validPassword);
		when(usersManager.getUser(name)).thenReturn(null);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(name);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		verify(errors).rejectValue("userName", "error.invalid.userName", "Username to short (at least 5 characters)");
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
	
	@Test
	public void shouldRejectWithNameToLongError() {
		String name = RandomStringUtils.randomAlphabetic(51);
		String validPassword = RandomStringUtils.random(6);
		
		when(user.getUserName()).thenReturn(name);
		when(user.getPassword()).thenReturn(validPassword);
		when(usersManager.getUser(name)).thenReturn(null);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(name);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		verify(errors).rejectValue("userName", "error.invalid.userName", "Username to long (no more than 50 characters)");
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
	
	@Test
	public void shouldRejectWithPasswordToShortError() {
		String validName = RandomStringUtils.random(6);
		String password = RandomStringUtils.random(3);
		
		when(user.getUserName()).thenReturn(validName);
		when(user.getPassword()).thenReturn(password);
		when(usersManager.getUser(validName)).thenReturn(null);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(validName);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		verify(errors).rejectValue("password", "error.invalid.password", "Password to short (at least 5 characters)");
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
	
	@Test
	public void shouldRejectWithPasswordToLongError() {
		String validName = RandomStringUtils.randomAlphabetic(6);
		String password = RandomStringUtils.random(51);
		
		when(user.getUserName()).thenReturn(validName);
		when(user.getPassword()).thenReturn(password);
		when(usersManager.getUser(validName)).thenReturn(null);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(validName);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		verify(errors).rejectValue("password", "error.invalid.password", "Password to long (no more than 50 characters)");
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
	
	@Test
	public void shouldRejectWithUserAlreadyExists() {
		String validName = RandomStringUtils.random(6);
		String validPassword = RandomStringUtils.random(6);
		User otherUser = mock(User.class);
		
		when(user.getUserName()).thenReturn(validName);
		when(user.getPassword()).thenReturn(validPassword);
		when(usersManager.getUser(validName)).thenReturn(otherUser);
		
		userValidator.validate(user, errors);
		
		verify(usersManager).getUser(validName);
		verify(user, times(3)).getUserName();
		verify(user, times(2)).getPassword();
		verify(errors).rejectValue("userName", "error.invalid.userName", "Username already exist");
		
		verifyNoMoreInteractions(usersManager, user, errors);
	}
}
