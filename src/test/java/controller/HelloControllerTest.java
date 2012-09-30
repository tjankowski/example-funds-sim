package controller;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class HelloControllerTest {
	
	private HelloController controller;
	
	@Before
	public void init() {
		
		controller = new HelloController();
	}
	
	@Test
	public void shouldHello() {
		ModelAndView modelAndView = controller.hello();
		
		assertThat(modelAndView.getViewName()).isEqualTo("/hello");
		
	}

}
