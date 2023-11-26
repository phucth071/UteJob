package vn.hcmute.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminLoginModel {
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	private Boolean rememberMe = false;
}
