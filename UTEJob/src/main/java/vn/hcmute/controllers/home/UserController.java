package vn.hcmute.controllers.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vn.hcmute.entities.users;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.impl.UsersServiceImpl;

@Controller
@RequestMapping("/testLayout")
public class UserController {
	@RequestMapping
	public ModelAndView home() {
		return new ModelAndView("user/index");
	}
}