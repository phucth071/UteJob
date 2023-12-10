  package vn.hcmute.controllers.admin;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import jakarta.persistence.Entity;

import jakarta.validation.Valid;
import vn.hcmute.config.StorageProperties;
import vn.hcmute.entities.company;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.student;
import vn.hcmute.entities.users;
import vn.hcmute.models.companyModel;
import vn.hcmute.models.usersModel;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IStorageService;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.impl.StorageServiceImpl;

@Controller
@RequestMapping("admin/company")
public class CompanyController {
	@Autowired
	ICompanyService companyService;
	@Autowired
	IUsersService userService;
	@Autowired
	IStorageService storageService;
//	@ModelAttribute("users")
//	public List<usersModel> getUsers(){
//		return userService.findAll().stream().map(item->{
//			usersModel users =new usersModel();
//			BeanUtils.copyProperties(item, users);
//			return users;
//		}).toList();
//	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<company> list = companyService.findAll();
		model.addAttribute("company", list);
		return "admin/company/list";
	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		companyModel company = new companyModel();
		company.setEdit(false);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("company", company);
		return "admin/company/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("company") companyModel company,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/company/addOrEdit", model);
		}
		// Random random = new Random();
		// int randomNumber = random.nextInt(101);
		company enity = new company();
		BeanUtils.copyProperties(company, enity);

//		//xu li user
//		users userEnity =new users();
//		userEnity.setUser_id(company.getUser_id());
//		enity.setUsers(userEnity);

		// enity.setCompany_id(randomNumber);
		if (!company.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			enity.setAvatar(storageService.getStorageFileName(company.getImageFile(), uuString));
			storageService.store(company.getImageFile(), enity.getAvatar());
		}
		companyService.save(enity);
		String message = "";
		if (company.getIsEdit() == true) {
			message = "Công ty đã được cập nhật";
		} else {
			message = "Công ty đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/company", model);
	}

	@GetMapping("edit/{company_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("company_id") int company_id) {
		Optional<company> opt = companyService.findById(company_id);
		companyModel company = new companyModel();

		if (opt.isPresent()) {
			company enity = opt.get();
			BeanUtils.copyProperties(enity, company);
			company.setEdit(true);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("company", company);
			
			int companyUserId = company.getUser_id();
			model.addAttribute("selectedUserId", companyUserId);
			return new ModelAndView("admin/company/addOrEdit", model);
		}
		model.addAttribute("message", "Công ty không tồn tại");
		return new ModelAndView("forward:/admin/company", model);
	}

	@GetMapping("delete/{company_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("company_id") int company_id) throws IOException {
		Optional<company> opt = companyService.findById(company_id);
		if (opt.isPresent()) {
			if (!StringUtils.hasText(opt.get().getAvatar())) {
				storageService.delete(opt.get().getAvatar());
			}
			companyService.deleteById(company_id);
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa thất bại");
		}
		return new ModelAndView("forward:/admin/company", model);
	}

	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@RequestMapping({"searchpage",""})
	public String search(ModelMap model, 
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		int count = (int) companyService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("company_id"));

		Page<company> resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = companyService.findByCompanyName(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = companyService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
				else if (start == 1)
					end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers",pageNumbers);
		}
		List<company> list = null;
		if (StringUtils.hasText(name)) {
			list = companyService.findByCompanyNameContaining(name);

		} else {
			list = companyService.findAll();

		}
		Map<Integer, String> userNames = new HashMap<>();

		for (company company : list) {
			int userId = company.getUser_id();
			String userName = userService.findUserNameByUserId(userId);
			userNames.put(userId, userName);
		}
		model.addAttribute("userNames", userNames);
		model.addAttribute("companyPage",resultPage);
		return "admin/company/list";
	}

}
