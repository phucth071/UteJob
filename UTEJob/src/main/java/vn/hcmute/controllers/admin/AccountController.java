package vn.hcmute.controllers.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.hcmute.entities.company;
import vn.hcmute.entities.student;
import vn.hcmute.entities.users;
import vn.hcmute.models.companyModel;
import vn.hcmute.models.studentModel;
import vn.hcmute.models.usersModel;
import vn.hcmute.services.IUsersService;

@Controller
@RequestMapping("admin/account")
public class AccountController {
	@Autowired
	IUsersService userService;

	@RequestMapping({"searchpage",""})
	public String search(ModelMap model, 
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		int count = (int) userService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("user_id"));

		Page<users> resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = userService.findByUserNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = userService.findAll(pageable);
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
		
		model.addAttribute("accountPage",resultPage);
		return "admin/account/list";
	}
	
	@GetMapping("add")
	public String Add(ModelMap model) {
		usersModel user = new usersModel();
		user.setEdit(false);
		model.addAttribute("account", user);
		return "admin/account/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("account") usersModel user, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/account/addOrEdit", model);
		}
		users enity = new users();
		BeanUtils.copyProperties(user, enity);
		
		userService.save(enity);
		String message = "";
		if (user.getIsEdit() == true) {
			message = "Thành công";
		} else {
			message = "Thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/account", model);
	}

	@GetMapping("edit/{user_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("user_id") int user_id) {
		Optional<users> opt = userService.findById(user_id);
		usersModel user = new usersModel();

		if (opt.isPresent()) {
			users enity = opt.get();
			BeanUtils.copyProperties(enity, user);
			user.setEdit(true);
			model.addAttribute("account", user);
			return new ModelAndView("admin/account/addOrEdit", model);
		}
		model.addAttribute("message", "Không tồn tại");
		return new ModelAndView("forward:/admin/account", model);
	}
	
	@GetMapping("delete/{user_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("user_id") int user_id) throws IOException {
		Optional<users> opt = userService.findById(user_id);
		if (opt.isPresent()) {
			userService.deleteById(user_id);
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa thất bại");
		}
		return new ModelAndView("forward:/admin/account", model);
	}
}
