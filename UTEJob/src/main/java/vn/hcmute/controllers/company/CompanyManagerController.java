package vn.hcmute.controllers.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.hcmute.entities.company;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.models.companyModel;
import vn.hcmute.models.internshipModel;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IInternshipService;
import vn.hcmute.services.IStorageService;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.userDetailsService.MyUserService;

@Controller
@RequestMapping("/company")
public class CompanyManagerController {
	
	@Autowired
	private IStorageService storageService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IUsersService userService;
	@Autowired
	IInternshipService internshipService;
	@Autowired
	private IRolesRepository rolesRepository;
	
	@RequestMapping("")
	public ModelAndView home(Model model) {
		return new ModelAndView("company/index");
	}
	
	@GetMapping("/update")
	public ModelAndView update(Model model, @AuthenticationPrincipal UserDetails curuser) {
		users user = (users) userService.findByEmail(curuser.getUsername()).get();
		company c = new company();
		c.setUser_id(user.getUser_id());
		if (companyService.findByUserid(user.getUser_id()) != null) {
			c = companyService.findByUserid(user.getUser_id());			
		}
		model.addAttribute("company", c);
		return new ModelAndView("company/information");
	}
	
	@PostMapping("/update")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("company") companyModel company, BindingResult result) {
		System.out.println("user id = " + company.getUser_id());
		/*
		 * if (result.hasErrors()) { return new ModelAndView("/company/information",
		 * model); }
		 */
		//Random random = new Random();
		//int randomNumber = random.nextInt(101);
		company enity = new company();
		BeanUtils.copyProperties(company, enity);
		
//		//xu li user
//		users userEnity =new users();
//		userEnity.setUser_id(company.getUser_id());
//		enity.setUsers(userEnity);
		
		//enity.setCompany_id(randomNumber);
		if (!company.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			enity.setAvatar(storageService.getStorageFileName(company.getImageFile(), uuString));
			storageService.store(company.getImageFile(), enity.getAvatar());
		}
		companyService.save(enity);
		return new ModelAndView("forward:/company", model);
	}
	
	@GetMapping("/listjob")
	public ModelAndView listJob(Model model, @AuthenticationPrincipal UserDetails curuser) {
		users user = (users) userService.findByEmail(curuser.getUsername()).get();
		company c = companyService.findByUserid(user.getUser_id());
		List<internship> list = new ArrayList<>();
		if (c != null) {
			list = c.getInternships();			
		}
		model.addAttribute("internships", list);
		return new ModelAndView("company/listjob");
	}
	@GetMapping("/internship/add")
	public String Add(ModelMap model, @AuthenticationPrincipal UserDetails curuser) {
		users user = (users) userService.findByEmail(curuser.getUsername()).get();
		company c = companyService.findByUserid(user.getUser_id());
		
		internshipModel internship = new internshipModel();
		internship.setIsEdit(false);
		model.addAttribute("company_id", c.getCompany_id());
		model.addAttribute("internship", internship);
		return "company/addOrEditInternship";
	}

	@PostMapping("internship/saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("internship") internshipModel internship,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("company/addOrEditInternship", model);
		}
		internship enity = new internship();
		BeanUtils.copyProperties(internship, enity);
		enity.setCompany_id(internship.getCompany_id());
		internshipService.save(enity);
		
		return new ModelAndView("redirect:/company/listjob", model);
	}

	@GetMapping("edit/{internship_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("internship_id") int internship_id) {
		Optional<internship> opt = internshipService.findById(internship_id);
		internshipModel internship = new internshipModel();

		if (opt.isPresent()) {
			internship enity = opt.get();
			BeanUtils.copyProperties(enity, internship);
			internship.setIsEdit(true);
			model.addAttribute("companies", companyService.findAll());
			model.addAttribute("internship", internship);
			// Suppose you have fetched the internship and have its company_id
			int internshipCompanyId = internship.getCompany_id();
			model.addAttribute("selectedCompanyId", internshipCompanyId);

			return new ModelAndView("company/addOrEditInternship", model);
		}
		model.addAttribute("message", "Không tồn tại");
		return new ModelAndView("forward:/company/listjob", model);
	}

	@GetMapping("delete/{internship_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("internship_id") int internship_id) {
		internshipService.deleteById(internship_id);
		model.addAttribute("message", "Xóa thành công");
		return new ModelAndView("company/listjob", model);
	}
}
