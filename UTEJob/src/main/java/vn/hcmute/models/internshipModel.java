package vn.hcmute.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class internshipModel {
	private int internship_id;
	private String title;
	private String full_description;
	private String jobnature;
	private String salary;
	private boolean status;
	private int company_id;
	
	private Boolean isEdit=false;
	public boolean getIsEdit() {
		return isEdit;
	}
}
