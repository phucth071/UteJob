package vn.hcmute.models;

import org.springframework.web.multipart.MultipartFile;

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
	private String avatar;
	private MultipartFile imageFile;
	private boolean isEdit;
	public boolean getIsEdit() {
		return isEdit;
	}
}
