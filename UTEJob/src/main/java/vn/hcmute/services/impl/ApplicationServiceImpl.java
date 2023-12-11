package vn.hcmute.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vn.hcmute.entities.application;
import vn.hcmute.entities.internship;
import vn.hcmute.entities.student;
import vn.hcmute.repository.IApplicationRepository;
import vn.hcmute.repository.IInternshipRepository;
import vn.hcmute.repository.IStudentRepository;
import vn.hcmute.services.IApplicationService;
@Service
public class ApplicationServiceImpl implements IApplicationService{
	@Autowired
	IApplicationRepository applicationRepo;
	IStudentRepository	studentRepo;
	IInternshipRepository internshipRepo;
	@Override
	public <S extends application> S save(S entity) {
		return applicationRepo.save(entity);
	}

	@Override
	public List<application> findAll() {
		return applicationRepo.findAll();
	}

	@Override
	public Optional<application> findById(Integer id) {
		return applicationRepo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		applicationRepo.deleteById(id);
	}

	@Override
	public List<application> findByStatus(boolean status) {
		// TODO Auto-generated method stub
		return applicationRepo.findByStatus(status);
	}
    @Autowired
    public ApplicationServiceImpl(IApplicationRepository applicationRepository, IStudentRepository studentRepository, IInternshipRepository internshipRepository) {
        this.applicationRepo = applicationRepository;
        this.studentRepo = studentRepository;
        this.internshipRepo = internshipRepository;
    }

    @Override
    public application applyForJob(Integer studentId, Integer jobId) {
        student student = studentRepo.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy học sinh: " + studentId));

        internship internship = internshipRepo.findById(jobId)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Job" + jobId));

        application application = new application();
        application.setStudent(student);
        application.setInternship(internship);
        application.setStatus("Pending"); // Trạng thái mặc định khi nộp đơn

        return applicationRepo.save(application);
    }

    @Override
    public List<application> getApplicationsByStudent(Integer studentId) {
        return applicationRepo.findByStudent_UserId(studentId);
    }
	
}
