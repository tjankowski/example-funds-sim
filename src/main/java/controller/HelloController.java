/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello Controller to hello new user.
 * @author Tomasz Jankowski
 */
@Controller
public class HelloController {

	/**
	 * Hello action
	 * @return empty model and view
	 */
    @RequestMapping("/hello.*")
    public ModelAndView hello() {
        return new ModelAndView("/hello");
    }
}
