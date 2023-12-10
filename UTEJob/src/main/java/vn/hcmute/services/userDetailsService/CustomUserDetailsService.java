package vn.hcmute.services.userDetailsService;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.models.signUpDto;
import vn.hcmute.repository.IRolesRepository;
import vn.hcmute.repository.IUsersRepository;

@Service
public class CustomUserDetailsService implements DefaultUserService{
	@Autowired
	private IUsersRepository userRepo;
	@Autowired
	private IRolesRepository roleRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		users user = userRepo.findByUsernameOrEmail(username, username)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản"));
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<roles> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}
	
	@Override
	public users save(signUpDto dto) {
		roles role = new roles();
		if (dto.getRole().equals("STUDENT")) {
			role = roleRepo.findByName("STUDENT").get();
		} else if (dto.getRole().equals("COMPANY")) {
			role = roleRepo.findByName("COMPANY").get();
		} else if (dto.getRole().equals("ADMIN")) {
			role = roleRepo.findByName("ADMIN").get();
		}
		users user = new users();
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEnabled(true);
		user.setRoles(Collections.singleton(role));
		
		return userRepo.save(user);
	}

}
