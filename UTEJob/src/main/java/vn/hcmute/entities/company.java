package vn.hcmute.entities;

import java.util.List;

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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "internship_id")
	private internship internship;
	// @OneToMany(targetEntity = jobs.class, mappedBy = "company", fetch = FetchType.EAGER)
	// private List<jobs> products;
}
