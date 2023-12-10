package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import vn.hcmute.entities.users;
import vn.hcmute.repository.IUsersRepository;
import vn.hcmute.services.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService{
	@Autowired
	private IUsersRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsersServiceImpl(IUsersRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Optional<users> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public Optional<users> findByUsernameOrEmail(String username, String email) {
		return userRepo.findByUsernameOrEmail(username, email);
	}

	@Override
	public void delete(users entity) {
		userRepo.delete(entity);
	}
	@Override
	public void deleteAll() {
		userRepo.deleteAll();
	}
	@Override
	public void deleteById(Integer id) {
		userRepo.deleteById(id);
	}
	@Override
	public boolean existsById(Integer id) {
		return userRepo.existsById(id);
	}
	@Override
	public List<users> findAll() {
		return userRepo.findAll();
	}
	@Override
	public Page<users> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}
	@Override
	public List<users> findByEmailContaining(String email) {
		return userRepo.findByEmailContaining(email);
	}
	@Override
	public Page<users> findByEmailContaining(String email, Pageable pageable) {
		return userRepo.findByEmailContaining(email, pageable);
	}
	
	@Override
	public <S extends users> S save(S entity) {
		Optional<users> opt = findById(entity.getUser_id());
		if (opt.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(opt.get().getPassword());
			} else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		}
		return userRepo.save(entity);
	}
	
	@Override
	public <S extends users> List<S> findAll(Example<S> example) {
		return userRepo.findAll(example);
	}

	@Override
	public Optional<users> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public Optional<users> findById(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public String findUserNameByUserId(int userId) {
		// TODO Auto-generated method stub
		return userRepo.findUserNameByUserId(userId);
	}
	

	
}
