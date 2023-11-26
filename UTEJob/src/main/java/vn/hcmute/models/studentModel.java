package vn.hcmute.models;
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
	private boolean isEdit;
	public boolean getIsEdit() {
		return isEdit;
	}
}
