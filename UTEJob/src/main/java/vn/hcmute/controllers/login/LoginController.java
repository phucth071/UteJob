package vn.hcmute.controllers.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hcmute.entities.users;
import vn.hcmute.models.loginModel;
import vn.hcmute.services.IUsersService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IUsersService userService;
	@Autowired
	HttpSession session;
	
	@GetMapping("")
	public String login(ModelMap model) {
		model.addAttribute("account", new loginModel());
		return "/user/login";
	}
	@PostMapping("")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") loginModel logModel, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/user/login", model);
		}
		users user = userService.login(logModel.getEmail(), logModel.getPassword());
		if (user == null) {
			model.addAttribute("message", "Đăng nhập thất bại! Sai tên tài khoản hoặc mật khẩu");
			return new ModelAndView("/user/login", model);
		}
		session.setAttribute("email", user.getEmail());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		return new ModelAndView("forward:utejob", model);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest req, HttpServletResponse resp) {
		session.invalidate();
		return "redirect:user/index";
	}
}
