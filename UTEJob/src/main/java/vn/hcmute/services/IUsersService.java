package vn.hcmute.services;

import java.util.Optional;

import vn.hcmute.entities.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.users;
import vn.hcmute.models.usersModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hcmute.entities.users;

@Service
public interface IUsersService {


	<S extends users> List<S> findAll(Example<S> example);

	<S extends users> S save(S entity);

	Page<users> findByEmailContaining(String email, Pageable pageable);

	List<users> findByEmailContaining(String email);

	Page<users> findAll(Pageable pageable);

	List<users> findAll();

	boolean existsById(Integer id);

	void deleteById(Integer id);

	void deleteAll();

	void delete(users entity);
	
	Optional<users> findByEmail(String email);

	Optional<users> findById(Integer id);

	Optional<users> findByUsernameOrEmail(String username, String email);

	Optional<users> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
    String findUserNameByUserId(@Param("userId") int userId);


}
