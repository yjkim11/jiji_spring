package org.jiji.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;


@Log4j
public class CustomAcessDeniedHandler implements AccessDeniedHandler{@Override
	
	
	public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2)
			throws IOException, ServletException {

		log.error("Access Denied Handler");
		log.error("Redirect........");
		
		arg1.sendRedirect("/accessError");
	
	}

	
}
