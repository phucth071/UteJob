package vn.hcmute.services;



import java.util.List;
import java.util.Optional;

import vn.hcmute.entities.users;
import vn.hcmute.models.usersModel;

public interface IUsersService {

	void deleteById(Integer id);

	Optional<users> findById(Integer id);

	List<users> findAll();

	<S extends users> S save(S entity);

}
