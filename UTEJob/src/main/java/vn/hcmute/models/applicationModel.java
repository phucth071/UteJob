package vn.hcmute.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicationModel {
	private int application_id;
	private boolean status;
	private int internship_id;
	private int student_id;
}
