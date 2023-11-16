package vn.hcmute.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/utejob")
public class UserController {
	
	@RequestMapping("")
	public ModelAndView home() {
		return new ModelAndView("user/index");
	}
}