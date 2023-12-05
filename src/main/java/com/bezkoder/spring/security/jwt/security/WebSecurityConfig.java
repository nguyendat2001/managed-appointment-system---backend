package com.bezkoder.spring.security.jwt.security;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bezkoder.spring.security.jwt.security.jwt.AuthEntryPointJwt;
import com.bezkoder.spring.security.jwt.security.jwt.AuthTokenFilter;
import com.bezkoder.spring.security.jwt.security.services.AccountDetailsServiceImpl;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccountDetailsServiceImpl userDetailsService;
	
	

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
//	      authProvider.setUserDetailsService(userDetailsService);
//	      authProvider.setPasswordEncoder(passwordEncoder());
	      
	      return authProvider;
	  }
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
      return authConfig.getAuthenticationManager();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
		        .antMatchers("/api/test/**").permitAll()
		        .antMatchers("/api/appointments/**").permitAll()
		        .antMatchers("/api/beds/**").permitAll()
		        .antMatchers("/api/caculations/**").permitAll()
		        .antMatchers("/api/caculationresults/**").permitAll()
		        .antMatchers("/api/certificates/**").permitAll()
		        .antMatchers("/api/datasets/**").permitAll()
		        .antMatchers("/api/departments/**").permitAll()
		        .antMatchers("/api/diagnoses/**").permitAll()
		        .antMatchers("/api/diagnoses_image/**").permitAll()
		        .antMatchers("/api/diseases/**").permitAll()
		        .antMatchers("/api/doctors/**").permitAll()
		        .antMatchers("/api/hyperparams/**").permitAll()
		        .antMatchers("/api/hyperparamsresult/**").permitAll()
		        .antMatchers("/api/medicines/**").permitAll()
		        .antMatchers("/api/models/**").permitAll()
		        .antMatchers("/api/prescriptions/**").permitAll()
		        .antMatchers("/api/reservation/**").permitAll()
		        .antMatchers("/api/users/**").permitAll()
		        .antMatchers("/api/workdays/**").permitAll()
		        .antMatchers("/api/worktimes/**").permitAll();
//		        .anyRequest().authenticated();
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
////                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth ->
//                        auth.antMatchers("/api/**").permitAll()
//                                  .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
}
