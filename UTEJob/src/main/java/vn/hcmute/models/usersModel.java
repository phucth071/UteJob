package vn.hcmute.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class usersModel {
	private int user_id;
	private String username;
	private String email;
	private String password;
	private boolean isEnabled = false;
	private boolean isEdit;
	public boolean getIsEdit() {
		return isEdit;
	}
}
