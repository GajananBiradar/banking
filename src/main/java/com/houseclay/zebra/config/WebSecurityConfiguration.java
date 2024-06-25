package com.houseclay.zebra.config;

import com.houseclay.zebra.filter.CustomAuthenticationFilter;
import com.houseclay.zebra.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/webjars/**",
            "/zebra/login/**"
    };



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/zebra/login");

         // disable this token mechanism to use our own token mechanism
        http.sessionManagement().sessionCreationPolicy(STATELESS); // session management will be stateless
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
//        http.authorizeRequests().antMatchers("/zebra/login/**").permitAll();
        http.authorizeRequests().antMatchers("*").permitAll();
//        http.authorizeRequests().antMatchers("/users/v1/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
//        http.authorizeRequests().antMatchers("/roles/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
//        http.authorizeRequests().antMatchers("/users/v1/register-user").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/lead-manager/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/tenant-leads/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/tenant-management/**").hasAnyAuthority("ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/post-property-for-rent/**").hasAnyAuthority("ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/owner-leads/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_LEAD_MANAGEMENT");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
