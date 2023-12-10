package vn.hcmute.services.userDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;

import vn.hcmute.entities.users;
import vn.hcmute.models.signUpDto;

public interface DefaultUserService extends UserDetailsService {

	users save(signUpDto dto);
	
}
