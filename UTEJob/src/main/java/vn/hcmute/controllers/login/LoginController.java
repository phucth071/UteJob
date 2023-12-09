package vn.hcmute.controllers.login;

import java.net.http.HttpRequest;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.userDetailsService.MyUserService;

@Controller
public class LoginController {
	@Autowired
	private IUsersService userService;
	@Autowired
	private IRolesRepository rolesRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	@GetMapping("/redirect-after-login")
	public String redirectAfterLogin(HttpServletRequest req, @AuthenticationPrincipal MyUserService user, Model model) {
		model.addAttribute("user", user);
		if (req.isUserInRole("ADMIN"))
			return "redirect:/admin";
		return "redirect:/";
	}
	@GetMapping("/signup")
	public String singupPage() {
		return "/user/signup";
	}
	@PostMapping("/signup")
	public String createUser(@ModelAttribute users user, HttpSession session) {
		boolean f = userService.existsByEmail(user.getEmail()) || userService.existsByUsername(user.getUsername());
		if (f) {
			session.setAttribute("msg", "Tên đăng nhập hoặc mật khẩu đã tồn tại");
			return "redirect:/signup";
		}
		users newUser = new users();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setEnabled(true);
		roles role = rolesRepository.findByName("GUEST").get();
		newUser.setRoles(Collections.singleton(role));
		if (userService.save(newUser) != null) {
			session.setAttribute("msg", "Đăng ký thành công");
		}
		return "redirect:/login";
	}
}
