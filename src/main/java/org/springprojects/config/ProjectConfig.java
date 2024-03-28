package org.springprojects.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springprojects.filters.JwtAuthFilter;
import org.springprojects.services.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaAuditing
public class ProjectConfig
{
    /*
    * JdbcTemplate?
    * */

    @Bean
    public JdbcTemplate template (@Autowired DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

    @Autowired
    private JwtAuthFilter authTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((authorize) -> authorize
            /*    .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/error/**").permitAll()*/
                       // .requestMatchers("communities/posts")
                .anyRequest().permitAll()
        ).addFilterBefore(authTokenFilter, AuthorizationFilter.class)
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);

        http.anonymous(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserService();
    }


}
