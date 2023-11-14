package vn.hcmute.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class locations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int location_id;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String location_name;
	
	@OneToMany(targetEntity = jobs.class, mappedBy = "location", fetch = FetchType.EAGER)
	private List<jobs> products;
}
