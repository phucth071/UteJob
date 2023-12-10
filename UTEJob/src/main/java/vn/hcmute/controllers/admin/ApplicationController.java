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
import vn.hcmute.entities.application;

import vn.hcmute.models.applicationModel;
import vn.hcmute.services.IApplicationService;
import vn.hcmute.services.IInternshipService;
import vn.hcmute.services.IStudentService;

@Controller
@RequestMapping("admin/application")
public class ApplicationController {
	@Autowired
	IApplicationService applicationService;
	@Autowired
	IInternshipService internshipService;
	@Autowired
	IStudentService studentService;

	@GetMapping("add")
	public String Add(ModelMap model) {
		applicationModel app = new applicationModel();
		app.setEdit(false);
		model.addAttribute("app", app);
		model.addAttribute("internships", internshipService.findAll());
		model.addAttribute("students", studentService.findAll());
		return "admin/application/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("app") applicationModel app, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/application/addOrEdit", model);
		}
		application enity = new application();
		BeanUtils.copyProperties(app, enity);
	
		applicationService.save(enity);
		String message = "";
		if (app.getIsEdit() == true) {
			message = "Thành công";
		} else {
			message = "Thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/application", model);
	}

	@GetMapping("edit/{application_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("application_id") int application_id) {
		Optional<application> opt = applicationService.findById(application_id);
		applicationModel application = new applicationModel();

		if (opt.isPresent()) {
			application enity = opt.get();
			BeanUtils.copyProperties(enity, application);
			application.setEdit(true);
			model.addAttribute("internships", internshipService.findAll());
			model.addAttribute("students", studentService.findAll());
			int applicationInternshipId = application.getInternship_id();
			model.addAttribute("selectedInternshipId", applicationInternshipId);
			int applicationStudentId = application.getStudent_id();
			model.addAttribute("selectedStudentId", applicationStudentId);
			
			model.addAttribute("app", application);
			return new ModelAndView("admin/application/addOrEdit", model);
		}
		model.addAttribute("message", "Không tồn tại");
		return new ModelAndView("forward:/admin/application", model);
	}

	@GetMapping("delete/{application_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("application_id") int application_id) {
		applicationService.deleteById(application_id);
		model.addAttribute("message", "Xóa thành công");
		return new ModelAndView("forward:/admin/application", model);
	}
	
	@RequestMapping({"searchpage",""})
	public String search(ModelMap model, 
			@RequestParam(name = "status", required = false) String status,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		int count = (int) applicationService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("application_id"));
		List<application> list=null;	
		Page<application> resultPage ;
		
		if (StringUtils.hasText(status)) {
			boolean isAvailable = Boolean.parseBoolean(status);	
			if (isAvailable==true) {
		        list = applicationService.findByStatus(true);
		    } else {
		        list = applicationService.findByStatus(false); 
		    }
			resultPage = applicationService.findByStatus(isAvailable, pageable);
			model.addAttribute("status", status);
		} else {
			list= applicationService.findAll();
			resultPage = applicationService.findAll(pageable);
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
		
		Map<Integer, String> internshipNames = new HashMap<>();
		Map<Integer, String> studentNames = new HashMap<>();
		for (application application : list) {
			int internshipId = application.getInternship_id();
			int studentId = application.getStudent_id();
			String internshipName = internshipService.findInternshipByInternshipId(internshipId);
			String studentName = studentService.findStudentNameByStudentId(studentId);
			internshipNames.put(internshipId, internshipName);
			studentNames.put(studentId, studentName);
		}
		model.addAttribute("studentNames", studentNames);	
		model.addAttribute("internshipNames", internshipNames);
		model.addAttribute("applicationPage",resultPage);
		return "admin/application/list";
	}
}
