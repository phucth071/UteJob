package vn.hcmute.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skill_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String skill_name;
	
	@OneToMany(targetEntity = internshipskill.class, mappedBy = "skill", fetch = FetchType.EAGER)
	private Set<internshipskill> internshipskills;
}
