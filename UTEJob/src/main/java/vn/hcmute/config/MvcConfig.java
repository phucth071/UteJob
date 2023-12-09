package vn.hcmute.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/").setViewName("utejob_home");
		registry.addViewController("/admin").setViewName("admin_home");
		registry.addViewController("/company").setViewName("company_home");
	}
}
