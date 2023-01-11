package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.bytebuddy.build.Plugin.Engine.Source.InMemory;  
@SpringBootTest
public class TestDemoService {
  
    static ApplicationContext applicationContext = null;
    static InMemory userDetailsService = null;
  
    @BeforeClass
    public static void setup()
    {
        applicationContext
            = new ClassPathXmlApplicationContext(
                "application-security.xml");
        userDetailsService = applicationContext.getBean(
            InMemory.class);
    }
    @Test
    // To test the valid user with valid role
    public void testValidRole()
    {
        UserDetails userDetails
            = ((UserDetailsService) userDetailsService).loadUserByUsername(
                "ganga");
        Authentication authToken
            = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext()
            .setAuthentication(authToken);
        TestDemoService service
            = (TestDemoService)applicationContext.getBean(
                "demoService");
        
    }
}
//    // Test the INVALID user
//    @Test(expected = AccessDeniedException.class)
//    public void testInvalidUser()
//    {
//        UserDetails userDetails
//            = userDetailsService.loadUserByUsername(
//                "geeksforgeeks2");
//        List<GrantedAuthority> authorities
//            = new ArrayList<GrantedAuthority>();
//  
//        authorities.add(
//            new GrantedAuthorityImpl("ROLE_INVALID"));
//        Authentication authToken
//            = new UsernamePasswordAuthenticationToken(
//                userDetails.getUsername(),
//                userDetails.getPassword(), authorities);
//  
//        SecurityContextHolder.getContext()
//            .setAuthentication(authToken);
//        DemoService service
//            = (DemoService)applicationContext.getBean(
//                "demoService");
//  
//        service.method();
//    }
//}