package vn.hcmute.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int application_id;
	@Column(columnDefinition = "bit")
	private boolean status;
	
	@ManyToOne(targetEntity = internship.class)
	@JoinColumn(name = "internship_id", insertable=false, updatable=false)
	private internship internship;
	
	@ManyToOne(targetEntity = student.class)
	@JoinColumn(name = "student_id", insertable=false, updatable=false)
	private student student;
	
	@OneToMany(targetEntity = review.class, mappedBy = "application", fetch = FetchType.EAGER)
	private Set<review> reviews;
}
