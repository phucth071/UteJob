package vn.hcmute.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String category_name;

	@OneToMany(targetEntity = jobs.class, mappedBy = "category", fetch = FetchType.EAGER)
	private List<jobs> products;
}
