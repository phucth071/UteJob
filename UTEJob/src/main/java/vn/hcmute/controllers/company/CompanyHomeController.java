package vn.hcmute.controllers.company;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.hcmute.entities.company;
import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.models.companyModel;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IStorageService;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.userDetailsService.MyUserService;

@Controller
@RequestMapping("/company")
public class CompanyHomeController {
	
	@Autowired
	private IStorageService storageService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IUsersService userService;
	@Autowired
	private IRolesRepository rolesRepository;
	
	@RequestMapping("")
	public ModelAndView home(Model model) {
		return new ModelAndView("company/index");
	}
	
	@GetMapping("/signup-company")
	public ModelAndView getsignup(Model model, @AuthenticationPrincipal MyUserService user) {
		company c = new company();
		c.setUser_id(user.getUsers().getUser_id());
		model.addAttribute("company", c);
		return new ModelAndView("company/signup");
	}
	
	@PostMapping("/signup-company")
	public ModelAndView signup(Model model,
			@Valid @ModelAttribute("company") companyModel company, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/signup");
		}
		//Random random = new Random();
		//int randomNumber = random.nextInt(101);
		company enity = new company();
		BeanUtils.copyProperties(company, enity);
		if (!company.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			enity.setAvatar(storageService.getStorageFileName(company.getImageFile(), uuString));
			storageService.store(company.getImageFile(), enity.getAvatar());
		}
		users user = userService.findById(enity.getUser_id()).get();
		roles role = rolesRepository.findByName("COMPANY").get();
		System.out.println(user.getUser_id() + "\n"+ user.getUsername() + "\n" +user.getPassword() + "\n" + user.getRoles().toString() + "\n" + Collections.singleton(role).toString());
		user.setRoles(Collections.singleton(role));
		//userService.save(user);
		companyService.save(enity);
		String message = "";
		if (company.getIsEdit() == true) {
			message = "Công ty đã được cập nhật";
		} else {
			message = "Công ty đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("company/index");
	}
}
