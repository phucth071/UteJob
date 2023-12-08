package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.users;
import vn.hcmute.repository.IUsersRepository;
import vn.hcmute.services.IUsersService;
@Service
public class UsersServiceImpl implements IUsersService{
	@Autowired
	IUsersRepository userRepo;

	

	@Override
	public List<users> findAll() {
		return userRepo.findAll();
	}



	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Optional<users> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



	@Override
	public <S extends users> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
