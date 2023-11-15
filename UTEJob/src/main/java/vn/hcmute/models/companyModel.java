package vn.hcmute.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class companyModel {
	private int company_id;
	private String company_name;
	private String industry;
	private int user_id;
	private int internship_id;
}
