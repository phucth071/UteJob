package vn.hcmute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hcmute.repository.ISkillRepository;
import vn.hcmute.services.ISkillService;

public class SkillServiceImpl implements ISkillService{
	@Autowired
	ISkillRepository skillRepo;
}
