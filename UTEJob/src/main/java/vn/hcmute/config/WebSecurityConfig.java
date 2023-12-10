package vn.hcmute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import vn.hcmute.services.userDetailsService.DefaultUserService;
import vn.hcmute.services.userDetailsService.UserDetailsServiceImpl;

import java.util.*;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private DefaultUserService userDetailsService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		final List<GlobalAuthenticationConfigurerAdapter> configures = new ArrayList<>();
		configures.add(new GlobalAuthenticationConfigurerAdapter() {
			@Override
			public void configure(AuthenticationManagerBuilder auth) {
				
			}
		});
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf().disable()
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers(
								AntPathRequestMatcher.antMatcher("/"), 
								AntPathRequestMatcher.antMatcher("/**"),
								AntPathRequestMatcher.antMatcher("/signup/**"),
								AntPathRequestMatcher.antMatcher("/assets/**"),
								AntPathRequestMatcher.antMatcher("/assets-admin/**"),
								AntPathRequestMatcher.antMatcher(HttpMethod.GET ,"/api/**"),
								AntPathRequestMatcher.antMatcher("/api/**"),
								AntPathRequestMatcher.antMatcher("/admin/company/images/**")
						).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/internship/add")).hasAnyAuthority("ADMIN", "COMPANY")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasAuthority("ADMIN")
						.requestMatchers(AntPathRequestMatcher.antMatcher("/company/**")).hasAuthority("COMPANY")
						.anyRequest().authenticated()
				)
				.formLogin().loginPage("/login").successHandler(loginSuccessHandler).permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.logout().permitAll()
				.and()
				.build();				
	}
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
}
