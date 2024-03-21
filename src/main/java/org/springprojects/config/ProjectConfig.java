package org.springprojects.config;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http.
                authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable).
                build();
    }

}
