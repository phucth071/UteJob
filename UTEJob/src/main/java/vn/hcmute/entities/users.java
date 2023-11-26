package vn.hcmute.entities;

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
	private String user_name;
	@Column(columnDefinition = "varchar(255)")
	private String email;
	@Column(columnDefinition = "varchar(255)")
	private String password;
	private int role;
	
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "student_id") private student student;
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "company_id") private company company;
	 */

	
}
