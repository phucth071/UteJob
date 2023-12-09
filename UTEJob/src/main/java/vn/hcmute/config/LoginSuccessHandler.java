package vn.hcmute.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.services.userDetailsService.MyUserService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MyUserService userDetails = (MyUserService) authentication.getPrincipal();
		String redirectURL = request.getContextPath();
		if (userDetails.hasRole("ADMIN")) {
			redirectURL = "/admin";
		} else if (userDetails.hasRole("COMPANY")) {
			redirectURL = "/company";
		} else if (userDetails.hasRole("STUDENT")) {
			redirectURL = "/";
		}
		response.sendRedirect(redirectURL);
	}
}
