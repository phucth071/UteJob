package vn.hcmute.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int job_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String title;
	@Column(columnDefinition = "nvarchar(255)")
	private String full_description;
	@Column(columnDefinition = "nvarchar(255)")
	private String requirements;
	@Column(columnDefinition = "varchar(255)")
	private String job_nature;
	@Column(columnDefinition = "varchar(255)")
	private String salary;
	@Column(columnDefinition = "bit")
	private boolean top_rated;
	
	@ManyToOne(targetEntity = categories.class)
	@JoinColumn(name = "category_id")
	private categories category;
	
	@ManyToOne(targetEntity = companies.class)
	@JoinColumn(name = "company_id")
	private categories company;
	
	@ManyToOne(targetEntity = locations.class)
	@JoinColumn(name = "location_id")
	private categories location;
}
