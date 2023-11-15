package vn.hcmute.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class internshipskill {
	@EmbeddedId
	private internshipskill_id internshipskill_id;
	
	@ManyToOne
	@JoinColumn(name = "internship_id", insertable=false, updatable=false)
	private internship internship;
	
	@ManyToOne
	@JoinColumn(name = "skill_id", insertable=false, updatable=false)
	private skill skill;
}
