package vn.hcmute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.hcmute.entities.roles;

public interface IRolesRepository extends JpaRepository<roles, Long>{
	@Query("Select u from roles u where u.name = :name")
	public roles getUserByName(@Param("name") String name);
	Optional<roles> findByName(String name);
}
