package fi.haagahelia.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.course.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/public/**","/api/user/register").permitAll()
			.and()
		.formLogin()
			.defaultSuccessUrl("/", true)
			.permitAll()
			.and()
		.httpBasic()
			.and()
		.csrf().disable()
		.logout()
			.logoutSuccessUrl("/");

		http.authorizeRequests().antMatchers("/projects","/projects/**").
				hasAnyRole("MANAGER","DEVELOPER","ROLE_MANAGER","ROLE_DEVELOPER");
    }
    	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
    	return new BCryptPasswordEncoder();
	}
     
}