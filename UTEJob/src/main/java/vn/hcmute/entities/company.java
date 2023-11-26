package vn.hcmute.entities;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private users user;
	@OneToMany(targetEntity = internship.class, mappedBy = "company", fetch = FetchType.EAGER)
	private Set<internship> interships;
	// @OneToMany(targetEntity = jobs.class, mappedBy = "company", fetch = FetchType.EAGER)
	// private List<jobs> products;
}
