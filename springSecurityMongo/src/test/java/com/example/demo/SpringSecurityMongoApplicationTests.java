package com.example.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.UserRepository;

import net.bytebuddy.build.Plugin.Engine.Source.InMemory;

@SpringBootTest
class SpringSecurityMongoApplicationTests {
	
	@Autowired
	UserRepository userrepo;
	
	 private MockMvc mvc;

//	@Test
//	void contextLoads() {
//	}
	@Test
	public void getusername() {
		return;
	}
	@Test
	public void getpassword() {
		return;
	}
	 @Test
	    public void httpBasicAuthenticationSuccess() throws Exception {
	        mvc
	            .perform(get("/secured/butnotfound").with(httpBasic("user","password")))
	            .andExpect(status().isNotFound())
	            .andExpect(authenticated().withUsername("user"));
	    }
	 
	 @Test
	    public void requiresAuthentication() throws Exception {
	        mvc
	            .perform(get("/"))
	            .andExpect(status().isMovedTemporarily());
	    }
	 @Test
	    public void authenticationFailed() throws Exception {
	        mvc
	            .perform(formLogin().user("user").password("invalid"))
	            .andExpect(status().isMovedTemporarily())
	            .andExpect(redirectedUrl("/login?error"))
	            .andExpect(unauthenticated());
	    }

}
