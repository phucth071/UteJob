package vn.hcmute.controllers.admin;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import vn.hcmute.entities.company;
import vn.hcmute.entities.student;
import vn.hcmute.entities.users;
import vn.hcmute.models.companyModel;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IStorageService;
import vn.hcmute.services.IUsersService;

@Controller
@RequestMapping("admin/company")
public class CompanyController {
	@Autowired
	ICompanyService companyService;
	IUsersService userService;
	@Autowired
	private IStorageService storageService;

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
		model.addAttribute("company", company);
		return "admin/company/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("company") companyModel company, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/company/addOrEdit", model);
		}
		company enity = new company();
		BeanUtils.copyProperties(company, enity);
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
			model.addAttribute("company", company);
			return new ModelAndView("admin/company/addOrEdit", model);
		}
		model.addAttribute("message", "Công ty không tồn tại");
		return new ModelAndView("forward:/admin/company", model);
	}
	
	@GetMapping("delete/{company_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("company_id") int company_id) throws IOException {
		Optional<company> opt = companyService.findById(company_id);
		if (opt.isPresent()) {
			if(!org.springframework.util.StringUtils.hasText(opt.get().getAvatar())) {
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
				"attachment;filename=\""+ file.getFilename() + "\"").body(file);
	}
}
