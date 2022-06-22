package br.com.alura.comex.config.security;

import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            try {
                auth.antMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/produtos/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/swagger-ui/*", "/v3/api-docs/**", "/**.html").permitAll()
                        .anyRequest().authenticated()
                        .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/configuration/**", "/swagger-resources/**");
    }


}
