package vn.hcmute.services.userDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.hcmute.entities.roles;
import vn.hcmute.entities.users;
import vn.hcmute.repository.IUsersRepository;
import vn.hcmute.services.IUsersService;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUsersService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<users> user = userService.findByUsername(username);
		if (user.get() == null) {
			throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		}
		Collection<GrantedAuthority> grantedAuth = new HashSet<>();
		Set<roles> roles = user.get().getRoles();
		for (roles role: roles) {
			grantedAuth.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new MyUserService(user.get(), grantedAuth);
	}
	
}
