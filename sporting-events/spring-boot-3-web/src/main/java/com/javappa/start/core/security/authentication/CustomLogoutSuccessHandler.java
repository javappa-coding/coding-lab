package com.javappa.start.core.security.authentication;

import com.javappa.start.core.config.MediaTypeConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaTypeConstants.APPLICATION_JSON);
		response.getWriter().write("{\"message\": \"Logout successful\"}");
		response.getWriter().flush();
	}
}
