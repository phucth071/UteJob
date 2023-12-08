package vn.hcmute.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class signUpDto {
	private String username;
	private String email;
	private String password;
	private boolean enabled;
}
