package me.afua.securitystarter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //Add this to indicate that this determines the application's configuration settings
@EnableWebSecurity //Add this to indicate that there will be endpoints visited by users that need to be secured
public class AppConfig extends WebSecurityConfigurerAdapter {

    //Simple in-memory authentication example with Spring Boot 2


    //Override the configuration for security, allowing all users to access the default route ("/"), adn
    //allowing any authenticated request
    //And allowing everyone access to the login form
    //And creating a logout route ("/logout")

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
        .formLogin().permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Creates a password encoder that can be used to encode and decode passwords. You can set it up as a bean so that it can be accessed
        //from the Spring context within the application.
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //This users in-memory authentication. You will need to modify this section AND create additional classes to allow
        //authentication from users in your database

        auth.inMemoryAuthentication().withUser("Jake").password(encoder.encode("pa$$word"))
                .authorities("ADMIN")
                .and()
                .withUser("theuser").password(encoder.encode("pa$$word"))
                .authorities("ADMIN")//This indicates what role the user is logged in with.
                .and()
                .withUser("Alton").password(encoder.encode("pa$$")).authorities("ADMIN")
                .and()
                .passwordEncoder(encoder);
    }
}
