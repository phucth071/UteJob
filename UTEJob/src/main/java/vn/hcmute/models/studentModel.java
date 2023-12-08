package vn.hcmute.models;
import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentModel {
	private int student_id;
	private String first_name;
	private String last_name;
	private String major;
	private int user_id;
	private String avatar;
	private MultipartFile imageFile;
	private boolean isEdit;
	public boolean getIsEdit() {
		return isEdit;
	}
}
