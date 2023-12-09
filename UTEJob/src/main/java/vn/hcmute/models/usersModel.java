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
}
