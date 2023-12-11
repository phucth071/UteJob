package vn.hcmute.controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.hcmute.entities.application;
import vn.hcmute.entities.internship;
import vn.hcmute.models.applicationModel;
import vn.hcmute.services.IApplicationService;

@Controller
@RequestMapping("admin/application")
public class ApplicationController {
	@Autowired
	IApplicationService applicationService;
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<application> list = applicationService.findAll();
		model.addAttribute("app", list);
		return "admin/application/list";
	}
	
	@GetMapping("add")
	public String Add(ModelMap model) {
		applicationModel app = new applicationModel();
		app.setEdit(false);
		model.addAttribute("app", app);
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
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="status",required = false) String status) {
		List<application> list=null;
		if (StringUtils.hasText(status)) {
			boolean isAvailable = Boolean.parseBoolean(status);	
			if (isAvailable) {
		        list = applicationService.findByStatus(true); 
		    } else {
		        list = applicationService.findByStatus(false); 
		    }
		}
		else {
			list= applicationService.findAll();
		}
		
		model.addAttribute("app",list);
		return "admin/application/search";
	}
	
	@Autowired
    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<application> applyForJob(@RequestParam Integer studentId, @RequestParam Integer intershipId) {
        application application = applicationService.applyForJob(studentId, intershipId);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/getByStudent/{studentId}")
    public ResponseEntity<List<application>> getApplicationsByStudent(@PathVariable Integer studentId) {
        List<application> applications = applicationService.getApplicationsByStudent(studentId);
        return ResponseEntity.ok(applications);
    }
}
