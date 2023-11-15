package vn.hcmute.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String first_name;
	@Column(columnDefinition = "nvarchar(255)")
	private String last_name;
	@Column(columnDefinition = "nvarchar(255)")
	private String major;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private users user;
	
	@OneToMany(targetEntity = application.class, mappedBy = "student", fetch = FetchType.EAGER)
	private List<application> applications;
}
