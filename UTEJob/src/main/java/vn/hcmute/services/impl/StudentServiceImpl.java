package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.IStudentRepository;
import vn.hcmute.services.IStudentService;

public class StudentServiceImpl implements IStudentService{
	@Autowired
	IStudentRepository studentRepo;
}
