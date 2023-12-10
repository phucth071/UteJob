package vn.hcmute.controllers.company;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hcmute.entities.company;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.users;
import vn.hcmute.services.ICompanyService;
import vn.hcmute.services.IInternshipService;
import vn.hcmute.services.IUsersService;

@RestController
@RequestMapping("/api/internship")
public class CompanyManagerRestController {
	@Autowired
	IInternshipService internshipService;
	@Autowired
	ICompanyService companyService;
	@Autowired
	IUsersService usersService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Set<internship>> listAllInternShipByCompany(@AuthenticationPrincipal UserDetails curuser) {
		users user = (users) usersService.findByEmail(curuser.getUsername()).get();
		company c = companyService.findByUserid(user.getUser_id());
		Set<internship> list = c.getInternships();
		if (list.isEmpty()) {
			return new ResponseEntity<Set<internship>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<internship>>(list, HttpStatus.OK);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public internship save(@Valid @RequestBody internship entity) {
		return internshipService.save(entity);
	}
}
