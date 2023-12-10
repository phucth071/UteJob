package vn.hcmute.controllers.admin;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
import vn.hcmute.entities.application;
import vn.hcmute.entities.review;
import vn.hcmute.models.applicationModel;
import vn.hcmute.models.reviewModel;
import vn.hcmute.services.IApplicationService;
import vn.hcmute.services.IReviewService;

@Controller
@RequestMapping("admin/review")
public class ReviewController {
	@Autowired
	IReviewService reviewService;
	@Autowired
	IApplicationService appService;
	
//	@ModelAttribute("application")
//	public List<applicationModel> getApplicatons(){
//		return appService.findAll().stream().map(item->{
//			applicationModel app =new applicationModel();
//			BeanUtils.copyProperties(item, app);
//			return app;
//		}).toList();
//	}

	@RequestMapping("")
	public String list(ModelMap model) {
		List<review> list = reviewService.findAll();
		model.addAttribute("review", list);
		return "admin/review/list";
	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		reviewModel review = new reviewModel();
		review.setEdit(false);
		model.addAttribute("review", review);
		return "admin/review/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("review") reviewModel review,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/review/addOrEdit", model);
		}
		
		
		review enity = new review();
		BeanUtils.copyProperties(review, enity);

		
//		//xu li user
//		application appEnity =new application();
//		appEnity.setApplication_id(review.getApplication_id());
//		enity.setApplication(appEnity);
		
		
		reviewService.save(enity);
		String message = "";
		if (review.getIsEdit() == true) {
			message = "Thành công";
		} else {
			message = "Thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/review", model);
	}

	@GetMapping("edit/{review_id}")
	public ModelAndView edit(ModelMap model, @PathVariable("review_id") int review_id) {
		Optional<review> opt = reviewService.findById(review_id);
		reviewModel review = new reviewModel();

		if (opt.isPresent()) {
			review enity = opt.get();
			BeanUtils.copyProperties(enity, review);
			review.setEdit(true);
			
			model.addAttribute("apps", appService.findAll());
			
			model.addAttribute("review", review);
			int reviewAppId = review.getApplication_id();
			model.addAttribute("selectedAppId", reviewAppId);
			return new ModelAndView("admin/review/addOrEdit", model);
		}
		model.addAttribute("message", "Không tồn tại");
		return new ModelAndView("forward:/admin/application", model);
	}

	@GetMapping("delete/{review_id}")
	public ModelAndView delete(ModelMap model, @PathVariable("review_id") int review_id) {
		reviewService.deleteById(review_id);
		model.addAttribute("message", "Xóa thành công");
		return new ModelAndView("forward:/admin/review", model);
	}
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name",required = false) String name) {
		List<review> list =null;
		if (StringUtils.hasText(name)) {
			list =reviewService.findByStatusContaining(name);
			
		}else {
			list = reviewService.findAll();
			
		}
		model.addAttribute("review",list);
		return "admin/review/search";
	}
}
