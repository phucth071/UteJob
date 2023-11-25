package vn.hcmute.controllers.admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.hcmute.entities.company;
import vn.hcmute.entities.student;
import vn.hcmute.services.ICompanyService;
 
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	ICompanyService companyService;
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<company> list=companyService.findAll();
		model.addAttribute("company",list);
		return "admin/index";
	}
	
	
	public ModelAndView home(Model model) {
		
		return new ModelAndView("admin/index");
	}
}
