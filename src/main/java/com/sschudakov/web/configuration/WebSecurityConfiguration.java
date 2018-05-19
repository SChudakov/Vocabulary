package com.sschudakov.web.configuration;

import com.sschudakov.web.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    /*private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;*/

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsService/*,
                                    RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                    SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler*/) {
        this.userDetailsService = userDetailsService;
        /*this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;*/
    }

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) {
//        authenticationManagerBuilder.authenticationProvider(authProvider());
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .exceptionHandling()
                //.authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().authorizeRequests()
                .antMatchers("/").permitAll()

                .antMatchers("/words/**").access("hasRole('ROLE_USER')")
                .antMatchers("/collections/**").access("hasRole('ROLE_USER')")
                .antMatchers("/languages/**").access("hasRole('ROLE_USER')")

                /*.and().httpBasic().realmName("EVocabulary")
                .and().formLogin().successHandler(this.authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/

                .and().formLogin().loginPage("/loginPage").permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/home.html")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordencoder());
        return authenticationProvider;
    }
}
