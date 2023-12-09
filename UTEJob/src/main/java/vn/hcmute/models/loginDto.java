package vn.hcmute.models;

import lombok.Data;

@Data
public class loginDto {
	private String usernameOrEmail;
	private String password;
}
