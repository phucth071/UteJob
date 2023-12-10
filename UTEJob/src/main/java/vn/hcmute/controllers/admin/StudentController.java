package vn.hcmute.controllers.admin;

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
import vn.hcmute.entities.student;
import vn.hcmute.models.studentModel;

import vn.hcmute.services.IStudentService;
import vn.hcmute.services.IUsersService;

@Controller
@RequestMapping("admin/student")
public class StudentController {
	@Autowired
	IStudentService studentService ;
	@Autowired
	IUsersService userService;
	
	@GetMapping("add")
	public String Add(ModelMap model) {
		studentModel student = new studentModel();
		student.setEdit(false);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("student", student);
		return "admin/student/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("student") studentModel student, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/student/addOrEdit", model);
		}
		student enity = new student();
		BeanUtils.copyProperties(student, enity);
		
		studentService.save(enity);
		String message = "";
		if (student.getIsEdit() == true) {
			message = "Thực tập sinh đã được cập nhật";
		} else {
			message = "Thực tập sinh đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/student", model);
	}

	@GetMapping("edit/{student_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("student_id") int student_id) {
		Optional<student> opt = studentService.findById(student_id);
		studentModel student = new studentModel();

		if (opt.isPresent()) {
			student enity = opt.get();
			BeanUtils.copyProperties(enity, student);
			student.setEdit(true);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("student", student);
			int studentUserId = student.getUser_id();
			model.addAttribute("selectedUserId", studentUserId);
			
			return new ModelAndView("admin/student/addOrEdit", model);
		}
		model.addAttribute("message", "Thực tập sinh không tồn tại");
		return new ModelAndView("forward:/admin/student", model);
	}
	@GetMapping("delete/{student_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("student_id") int student_id) {
		studentService.deleteById(student_id);
		model.addAttribute("message", "Xóa thành công");
		return new ModelAndView("forward:/admin/student", model);
	}
	
	@RequestMapping({"searchpage",""})
	public String search(ModelMap model, 
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		int count = (int) studentService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("student_id"));

		Page<student> resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = studentService.findByStudentName(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = studentService.findAll(pageable);
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
		List<student> list = null;
		if (StringUtils.hasText(name)) {
			list = studentService.findByFirstnameContaining(name);

		} else {
			list = studentService.findAll();
		}
		Map<Integer, String> userNames = new HashMap<>();

		for (student student : list) {
			int userId = student.getUser_id();
			String userName = userService.findUserNameByUserId(userId);
			userNames.put(userId, userName);
		}
		model.addAttribute("userNames", userNames);
		model.addAttribute("studentPage",resultPage);
		return "admin/student/list";
		}
}
