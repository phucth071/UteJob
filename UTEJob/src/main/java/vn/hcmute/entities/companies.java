package vn.hcmute.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class companies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_id;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String company_name;
	
	@OneToMany(targetEntity = jobs.class, mappedBy = "company", fetch = FetchType.EAGER)
	private List<jobs> products;
}
