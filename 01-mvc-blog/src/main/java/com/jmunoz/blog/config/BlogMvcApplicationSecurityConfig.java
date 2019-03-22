package com.jmunoz.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jmunoz.blog.services.UserService;

@Configuration
@EnableWebSecurity
public class BlogMvcApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService userService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
        	.csrf().disable()
            .authorizeRequests()
                .antMatchers("/","/users/login**","/login*", "/logout*", "/signup/**", "/customLogin",
                        "/user/register*", "/registrationConfirm*", "/expiredAccount*", "/registration*",
                        "/badUser*", "/user/resendRegistrationToken*" ,"/forgetPassword*", "/user/resetPassword*",
                        "/user/changePassword*", "/resources/**","/old/user/registration*","/successRegister*").permitAll()
                .and()
    			.formLogin()
    				.loginPage("/users/login")
    				.defaultSuccessUrl("/")
    				.loginProcessingUrl("/users/login")
    				.permitAll()
    			.and()
    			.logout().permitAll();
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	
}
