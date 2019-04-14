package com.miniProject.emergencyCare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miniProject.emergencyCare.service.AttentionService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AttentionService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/*.css", "/*.js").permitAll().anyRequest().authenticated()
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/", true)
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(authenticationProvider());
//	    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
	 
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}
	
}
