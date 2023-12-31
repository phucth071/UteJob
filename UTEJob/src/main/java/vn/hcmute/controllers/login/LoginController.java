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
import vn.hcmute.models.loginDto;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.userDetailsService.DefaultUserService;
import vn.hcmute.services.userDetailsService.MyUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IUsersService userService;
	@Autowired
	private IRolesRepository rolesRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private DefaultUserService userDetailsService;
	
	@ModelAttribute("user")
	public loginDto userLoginDTO() {
		return new loginDto();
	}
	
	@GetMapping
	public String login() {
		return "/user/login";
	}
	
	@PostMapping
	public void loginUser(@ModelAttribute("user") loginDto userLoginDTO) {
		userDetailsService.loadUserByUsername(userLoginDTO.getUsername());
	}
	
}
