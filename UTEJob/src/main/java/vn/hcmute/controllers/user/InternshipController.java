package vn.hcmute.controllers.user;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.hcmute.entities.internship;
import vn.hcmute.models.internshipModel;
import vn.hcmute.services.IInternshipService;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	@Autowired
	IInternshipService internshipService;


	@GetMapping("")
	public String getListIntershipByJobNature(ModelMap model) {
		// Tìm tất cả các internships với job_nature là "Full Time" trong service
		List<internship> fullTimeInternships = internshipService.findByJobnature("Full time");
		for (Iterator iterator = fullTimeInternships.iterator(); iterator.hasNext();) {
			internship internship = (internship) iterator.next();
			System.out.println(internship.getTitle());
		}
		// Chuyển dữ liệu từ Iterable sang List để sử dụng trong Thymeleaf
		model.addAttribute("fullTimeInternships", fullTimeInternships);
		List<internship> partTimeInternships = internshipService.findByJobnature("Part time");
		// Chuyển dữ liệu từ Iterable sang List để sử dụng trong Thymeleaf
		model.addAttribute("partTimeInternships", partTimeInternships);
		for (Iterator iterator = partTimeInternships.iterator(); iterator.hasNext();) {
			internship internship = (internship) iterator.next();
			System.out.println(internship.getTitle());
		}
		return "user/internship/list";
	}

}
