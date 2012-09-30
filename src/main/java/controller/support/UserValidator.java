package controller.support;

import model.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import model.service.UsersManager;


/**
 * Validator for User.class .
 * Validates new created user objects
 * @author Tomasz Jankowski
 */
public class UserValidator implements Validator
{
    private UsersManager usersManager;

	public boolean supports(Class<?> aClass)
	{
		return aClass.equals(User.class);
	}

	public void validate(Object o, Errors errors)
	{
		User user = (User) o;
                User sameUser = getUsersManager().getUser(user.getUserName());

                if(user.getUserName().length() < 5) {
                    errors.rejectValue("userName", "error.invalid.userName", "Username to short (at least 5 characters)");
                }
                if(user.getUserName().length() > 50) {
                    errors.rejectValue("userName", "error.invalid.userName", "Username to long (no more than 50 characters)");
                }
                if(sameUser != null) {
                    errors.rejectValue("userName", "error.invalid.userName", "Username already exist");
                }
                if(user.getPassword().length() < 5) {
                    errors.rejectValue("password", "error.invalid.password", "Password to short (at least 5 characters)");
                }
                if(user.getPassword().length() > 50) {
                    errors.rejectValue("password", "error.invalid.password", "Password to long (no more than 50 characters)");
                }
	}

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public void setUsersManager(UsersManager usersManager) {
        this.usersManager = usersManager;
    }
}