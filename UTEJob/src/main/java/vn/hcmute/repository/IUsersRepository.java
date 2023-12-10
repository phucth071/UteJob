package vn.hcmute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.hcmute.entities.users;

@Repository
public interface IUsersRepository extends JpaRepository<users, Integer> {
	@Query("SELECT u FROM users u WHERE u.username = :username")
	public users getUserByUsername(@Param("username") String username);

	@Query("SELECT c.username FROM users c WHERE c.user_id = :userId")
	String findUserNameByUserId(@Param("userId") int userId);

	List<users> findByEmailContaining(String email);

	Page<users> findByEmailContaining(String email, Pageable pageable);

	Optional<users> findByEmail(String email);

	Optional<users> findByUsername(String username);

	Optional<users> findByUsernameOrEmail(String username, String email);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
}
