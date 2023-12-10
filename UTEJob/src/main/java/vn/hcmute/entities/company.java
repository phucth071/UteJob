package vn.hcmute.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_id;
	@Column(columnDefinition = "nvarchar(255)")
	private String company_name;
	@Column(columnDefinition = "nvarchar(255)")
	private String industry;
	@Column(length = 200)
	private String avatar;
    private Integer user_id; // Trường user_id để tham chiếu đến bảng users

    @OneToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private users user;
    
	@OneToMany(targetEntity = internship.class, mappedBy = "company", fetch = FetchType.EAGER)
	private List<internship> internships;

}
