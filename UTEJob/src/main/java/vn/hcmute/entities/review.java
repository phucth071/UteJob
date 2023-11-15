package vn.hcmute.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int review_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String comment;
	@Column(columnDefinition = "decimal(3, 1)")
	private double rating;
	
	@ManyToOne(targetEntity = application.class)
	@JoinColumn(name = "application_id", insertable=false, updatable=false)
	private student application;
}
