package vn.hcmute.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.hcmute.services.ICompanyService;
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	ICompanyService companyService;
	
	@RequestMapping("")
	public ModelAndView home(Model model) {
		return new ModelAndView("admin/index");
	}
}
