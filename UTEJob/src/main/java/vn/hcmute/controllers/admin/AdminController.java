package vn.hcmute.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@RequestMapping("")
	public ModelAndView home(Model model) {
		return new ModelAndView("admin/index");
	}
}
