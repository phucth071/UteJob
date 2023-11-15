package vn.hcmute.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class usersModel {
	private int user_id;
	private String user_name;
	private String email;
	private String password;
	private int role;
}
