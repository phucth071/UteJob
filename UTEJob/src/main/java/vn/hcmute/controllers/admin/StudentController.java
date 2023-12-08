package vn.hcmute.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import vn.hcmute.models.studentModel;

import vn.hcmute.services.IStudentService;

@Controller
@RequestMapping("admin/student")
public class StudentController {
	@Autowired
	IStudentService studentService ;

	@RequestMapping("")
	public String list(ModelMap model) {
		List<student> list = studentService.findAll();
		model.addAttribute("student", list);
		return "admin/student/list";
	}
	@GetMapping("add")
	public String Add(ModelMap model) {
		studentModel student = new studentModel();
		student.setEdit(false);
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
			model.addAttribute("student", student);
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
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name",required = false) String name) {
		List<student> list =null;
		if (StringUtils.hasText(name)) {
			list =studentService.findByFirstnameContaining(name);
			
		}else {
			list = studentService.findAll();
			
		}
		model.addAttribute("student",list);
		return "admin/student/search";
	}
}
