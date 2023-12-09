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
	@Column(length = 200)
	private String avatar;
	private int user_id;
//	@OneToOne
//	@JoinColumn(name = "user_id",insertable=false, updatable=false)
//	private users user;
//	
//	@OneToMany(targetEntity = application.class, mappedBy = "student", fetch = FetchType.EAGER)
//	private List<application> applications;
}
