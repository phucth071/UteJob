package vn.hcmute.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginModel {
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	private Boolean rememberMe = false;
}
