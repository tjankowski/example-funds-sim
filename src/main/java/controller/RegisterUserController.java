/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.support.UserValidator;
import model.domain.User;
import model.service.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Form Controller used to register accounts by new users.
 * @author Tomasz Jankowski
 */
@Controller
@RequestMapping("/register.*")
public class RegisterUserController {

    //UsersManager for interaction with User.class objects
	@Autowired
    private UsersManager usersManager;
    //User validator
    @Autowired
    private UserValidator userValidator;

    /**
     * Register user form.
     * @param model model
     * @return view name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(Model model) {
        model.addAttribute(new User());
        return "/register";
    }

    /**
     * Register user action.
     * @param user user
     * @param result user binding result
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String register(@ModelAttribute User user, BindingResult result) {
        getUserValidator().validate(user, result);
        if (result.hasErrors()) {
            return "/register";
        }
        getUsersManager().createUser(user);
        return "redirect:/hello.html";
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public void setUsersManager(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	public UserValidator getUserValidator() {
		return userValidator;
	}
}
