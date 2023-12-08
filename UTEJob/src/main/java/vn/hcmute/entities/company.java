package vn.hcmute.entities;

import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
	@Column(name = "user_id") // Đặt tên cột tương ứng trong bảng company
    private Integer user_id; // Trường user_id để tham chiếu đến bảng users

//	@OneToOne
//	@JoinColumn(name = "user_id")
//	private users users;
//	@OneToMany(targetEntity = internship.class, mappedBy = "company", fetch = FetchType.EAGER)
//	private Set<internship> interships;
//	// @OneToMany(targetEntity = jobs.class, mappedBy = "company", fetch = FetchType.EAGER)
//	// private List<jobs> products;

}
