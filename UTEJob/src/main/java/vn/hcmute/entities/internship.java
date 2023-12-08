package vn.hcmute.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class internship {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int internship_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String title;
	@Column(columnDefinition = "nvarchar(255)")
	private String full_description;
	@Column(columnDefinition = "varchar(255)")
	private String job_nature;
	@Column(columnDefinition = "varchar(255)")
	private String salary;
	@Column(columnDefinition = "bit")
	private boolean status;

	private int company_id;
//	@OneToMany(targetEntity = application.class, mappedBy = "internship", fetch = FetchType.EAGER)
//	private Set<application> applications;
////	
//	@OneToMany(targetEntity = internshipskill.class, mappedBy = "internship", fetch = FetchType.EAGER)
//	private Set<internshipskill> internshipskills;
}
