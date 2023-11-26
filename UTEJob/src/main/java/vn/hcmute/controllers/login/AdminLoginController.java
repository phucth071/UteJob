package vn.hcmute.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hcmute.entities.users;
import vn.hcmute.models.loginModel;
import vn.hcmute.services.IUsersService;

@Controller
@RequestMapping("alogin")
public class AdminLoginController {
	@Autowired
	private IUsersService userService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("")
	public String login(ModelMap model) {
		model.addAttribute("account", new loginModel());
		return "/admin/login";
	}
	@PostMapping("")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") loginModel loginModel, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/login", model);
		}
		users user = userService.login(loginModel.getEmail(), loginModel.getPassword());
		if (user == null) {
			model.addAttribute("message", "Tên tài khoản hoặc email không hợp lệ!");
			return new ModelAndView("/admin/login", model);
		}
		session.setAttribute("emailAdmin", user.getEmail());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		return new ModelAndView("forward:admin", model);
	}
}
