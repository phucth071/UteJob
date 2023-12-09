package vn.hcmute.controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import vn.hcmute.entities.internship;
import vn.hcmute.models.internshipModel;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IInternshipService;

@Controller
@RequestMapping("admin/internship")
public class InternshipController {
	@Autowired
	private IInternshipService internService;
	@Autowired
	ICompanyService companyService;

	@RequestMapping("")
	public String list(ModelMap model) {

		List<internship> list = internService.findAll();
		Map<Integer, String> companyNames = new HashMap<>();

		for (internship internship : list) {
			//int companyId = internship.getCompany_id();
			//String companyName = companyService.findCompanyNameByCompanyId(companyId);
			//companyNames.put(companyId, companyName);
		}
		model.addAttribute("companyNames", companyNames);
		model.addAttribute("internship", list);
		return "admin/internship/list";
	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		internshipModel internship = new internshipModel();
		internship.setIsEdit(false);
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("internship", internship);
		return "admin/internship/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("internship") internshipModel internship,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/internship/addOrEdit", model);
		}
		internship enity = new internship();
		BeanUtils.copyProperties(internship, enity);

		internService.save(enity);
		String message = "";
		if (internship.getIsEdit() == true) {
			message = "Thành công";
		} else {
			message = "Thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/internship", model);
	}

	@GetMapping("edit/{internship_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("internship_id") int internship_id) {
		Optional<internship> opt = internService.findById(internship_id);
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

			return new ModelAndView("admin/internship/addOrEdit", model);
		}
		model.addAttribute("message", "Không tồn tại");
		return new ModelAndView("forward:/admin/internship", model);
	}

	@GetMapping("delete/{internship_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("internship_id") int internship_id) {
		internService.deleteById(internship_id);
		model.addAttribute("message", "Xóa thành công");
		return new ModelAndView("forward:/admin/internship", model);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="status",required = false) String status) {
		List<internship> list=null;
		if (StringUtils.hasText(status)) {
			boolean isAvailable = Boolean.parseBoolean(status);	
			if (isAvailable) {
		        list = internService.findByStatus(true); // Nếu là true, tìm kiếm theo trạng thái "Còn tuyển"
		    } else {
		        list = internService.findByStatus(false); // Nếu là false, tìm kiếm theo trạng thái "Hết tuyển"
		    }
		}
		else {
			list= internService.findAll();
		}
		
		Map<Integer, String> companyNames = new HashMap<>();

		for (internship internship : list) {
		    //int companyId = internship.getCompany_id();
		    //String companyName = companyService.findCompanyNameByCompanyId(companyId); 
			//    companyNames.put(companyId, companyName); 
			}
		model.addAttribute("companyNames", companyNames); 
		model.addAttribute("internship",list);
		return "admin/internship/search";
	}
}
