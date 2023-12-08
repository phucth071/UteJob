package vn.hcmute.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String company_name;
	@Column(columnDefinition = "nvarchar(255)")
	private String industry;
	@Column(length = 200)
	private String avatar;
    private Integer user_id; // Trường user_id để tham chiếu đến bảng users
	@OneToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private users user;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "company_internship",
			joinColumns = @JoinColumn(name = "company_id"),
			inverseJoinColumns = @JoinColumn(name = "internship_id")
	)
	private Set<internship> internships = new HashSet<>();
	// @OneToMany(targetEntity = jobs.class, mappedBy = "company", fetch = FetchType.EAGER)
	// private List<jobs> products;




	


	
}
