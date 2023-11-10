package com.javappa.start.security.authentication;

import com.javappa.start.config.MediaTypeConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	private final Logger LOG = LoggerFactory.getLogger(CustomAuthFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException {
		LOG.info("onAuthenticationFailure - set status to HttpServletResponse.SC_UNAUTHORIZED");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaTypeConstants.APPLICATION_JSON);
		response.getWriter().write("{\"error\": \"" + exception.getMessage() + "\"}");
		response.getWriter().flush();
	}
}
