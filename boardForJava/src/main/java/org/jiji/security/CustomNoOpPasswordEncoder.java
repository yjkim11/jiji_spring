package org.jiji.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder{@Override

	public String encode(CharSequence arg0) {

		log.warn("before encode :" + arg0);
		
		return arg0.toString();
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {

		log.warn("matches: " + arg0 + ":" + arg1);
		
		return arg0.toString().equals(arg1);
	}

	
}
