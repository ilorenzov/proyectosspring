package com.example.springbootdatajpa.app;

import com.example.springbootdatajpa.app.auth.handler.LoginSuccessHandler;
import com.example.springbootdatajpa.app.auth.handler.filter.JWTAuthenticationFilter;
import com.example.springbootdatajpa.app.auth.handler.filter.JWTAuthorizationFilter;
import com.example.springbootdatajpa.app.auth.service.IJWTService;
import com.example.springbootdatajpa.app.models.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//para poder usar @Secured("NOMBREROL") en los controller
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JpaUserDetailsService userDetailsService;
    @Autowired
    private IJWTService ijwtService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
/*        builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder).usersByUsernameQuery("select username , password , enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username , a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");metodo con jdbc */
    }

    @Autowired
    private DataSource dataSource;
    @Autowired
    private LoginSuccessHandler successHandler;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/locale").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), ijwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),ijwtService))
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
