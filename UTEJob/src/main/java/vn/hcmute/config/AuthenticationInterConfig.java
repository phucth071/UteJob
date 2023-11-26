package vn.hcmute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import vn.hcmute.intercep.AdminAuthenticationInter;
import vn.hcmute.intercep.WebAuthenticationInter;

@Configuration
public class AuthenticationInterConfig implements WebMvcConfigurer {
	@Autowired(required = false)
	AdminAuthenticationInter adminAuthenticationInter;
	@Autowired(required = false)
	WebAuthenticationInter authenticationInter;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInter).addPathPatterns("/admin/**");
		registry.addInterceptor(authenticationInter).addPathPatterns("/utejob/**");
	}
}
