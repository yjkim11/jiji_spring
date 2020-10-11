package org.jiji.security;

import org.jiji.domain.MemberVO;
import org.jiji.mapper.MemberMapper;
import org.jiji.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{

	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		log.warn("Load User By UserName : " + arg0);
		
		MemberVO vo = memberMapper.read(arg0);
		
		log.warn("queried by memeber mapper: " + vo);
		
		return vo == null ? null : new CustomUser(vo);
	}

	
}
