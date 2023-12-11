package vn.hcmute.controllers.login;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vn.hcmute.entities.company;
import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.models.signUpDto;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IStudentService;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.userDetailsService.DefaultUserService;

@Controller
@RequestMapping("/signup")
public class RegisterController {
	@Autowired
	private IUsersService userService;
	@Autowired
	private IRolesRepository rolesRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IStudentService studentService;
	
	private DefaultUserService userDetailsService;
	
	public RegisterController(DefaultUserService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	
	@ModelAttribute("user")
	public signUpDto userSignUpDTO() {
		return new signUpDto();
	}
	
	@GetMapping("")
	public String singupPage() {
		return "signup";
	}
	@PostMapping("")
	public String createUser(@ModelAttribute signUpDto userSignUpDTO, HttpSession session) {
		if (userService.existsByUsername(userSignUpDTO.getUsername()) || userService.existsByEmail(userSignUpDTO.getEmail())) {
			session.setAttribute("msg", "Đã tồn tại tên tài khoản hoặc email");
			return "/signup";
		}
		if (userDetailsService.save(userSignUpDTO) != null) {
			session.setAttribute("msg", "Đăng ký thành công");
		}
		return "redirect:/login";
	}
}
