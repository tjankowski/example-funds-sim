package controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import model.domain.User;
import model.service.UsersManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import controller.support.UserValidator;

public class RegisterUserControllerTest {

	private RegisterUserController controller;
	@Mock
	private UsersManager usersManager;
	@Mock
	private UserValidator userValidator;

	@Before
	public void init() {
		initMocks(this);

		controller = new RegisterUserController();
		controller.setUsersManager(usersManager);
		controller.setUserValidator(userValidator);
	}

	@Test
	public void shouldReturnUserForm() {
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		Model model = mock(Model.class);
		String view = controller.getRegisterForm(model);
		assertThat(view).isEqualTo("/register");
		verify(model).addAttribute(argumentCaptor.capture());
		verifyNoMoreInteractions(usersManager, userValidator, model);
	}
	
	@Test
	public void shouldRegister() {
		User user = mock(User.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);
		
		String view = controller.register(user, bindingResult);
		assertThat(view).isEqualTo("redirect:/hello.html");
		verify(userValidator).validate(user, bindingResult);
		verify(usersManager).createUser(user);
		verify(bindingResult).hasErrors();
		verifyNoMoreInteractions(usersManager, userValidator, user, bindingResult);
	}
	
	@Test
	public void shouldViewError() {
		User user = mock(User.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		
		String view = controller.register(user, bindingResult);
		assertThat(view).isEqualTo("/register");
		verify(userValidator).validate(user, bindingResult);
		verify(bindingResult).hasErrors();
		verifyNoMoreInteractions(usersManager, userValidator, user, bindingResult);
	}

}
