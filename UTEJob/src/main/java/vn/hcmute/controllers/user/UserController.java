package vn.hcmute.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vn.hcmute.entities.users;
import vn.hcmute.services.IUsersService;
import vn.hcmute.services.impl.UsersServiceImpl;

@RestController
@RequestMapping("/")
public class UserController {
	@RequestMapping
	public ModelAndView home() {
		return new ModelAndView("user/index");
	}
}