package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IUsersRepository;
import vn.hcmute.services.IUsersService;

public class UsersServiceImpl implements IUsersService{
	@Autowired
	IUsersRepository userRepo;
	
	
}
