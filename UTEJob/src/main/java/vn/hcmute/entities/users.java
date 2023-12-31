package vn.hcmute.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.annotations.NaturalId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@Column(columnDefinition = "nvarchar(255)")
	private String username;
	@Column(columnDefinition = "varchar(255)")
	@NaturalId
	private String email;
	@Column(columnDefinition = "varchar(255)")
	private String password;
	private boolean isEnabled = false;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<roles> roles = new HashSet<>();
	
	public boolean hasRole(String rolename) {
		Iterator<roles> iter = this.roles.iterator();
		while (iter.hasNext()) {
			roles role = iter.next();
			if (role.getName().equals(rolename)) {
				return true;
			}
		}
		return false;
	}
	
//	@OneToOne(cascade = CascadeType.ALL)
//
//	@JoinColumn(name = "student_id")
//	private student student;

//	@OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
//	private company company;

}
