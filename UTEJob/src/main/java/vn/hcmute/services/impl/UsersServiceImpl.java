package vn.hcmute.services.impl;

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
	
	
	
}
