package vn.hcmute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.users;

@Repository
public interface IUsersRepository extends JpaRepository<users, Integer>{
	List<users> findByEmailContaining(String email);
	Page<users> findByEmailContaining(String email, Pageable pageable);
	Optional<users> findByEmail(String email);
}
