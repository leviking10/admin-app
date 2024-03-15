package sn.isi.security;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    @Bean
    @Override
    protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        KeycloakAuthenticationProvider provider = super.keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
        return provider;
    }

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return authorities -> authorities.stream()
            .map(grantedAuthority -> {
                String authority = grantedAuthority.getAuthority();
                if (authority.startsWith("ROLE_")) {
                    return new SimpleGrantedAuthority(authority.substring(5));
                } else {
                    return new SimpleGrantedAuthority(authority);
                }
            })
            .collect(Collectors.toList());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());//keycloakAuthenticationProvider() cest keycloak qui gere tout
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/index/**").permitAll()
            .antMatchers("/appuser/**").permitAll()
            .antMatchers("/produit/**").permitAll()
            .antMatchers("/approles/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutUrl("/logout") // URL interne pour déclencher la déconnexion
            .addLogoutHandler(keycloakLogoutHandler()) // Gestionnaire de déconnexion Keycloak
            .logoutSuccessHandler((request, response, authentication) -> {
                // Custom logout success handler pour rediriger vers l'URL de déconnexion de Keycloak
                String encodedRedirectUri = URLEncoder.encode("http://localhost:8080/sso/login", StandardCharsets.UTF_8);
                String logoutUrl = "http://localhost:8090/auth/realms/Admin-App-realm/protocol/openid-connect/logout?redirect_uri=" + encodedRedirectUri;
                response.sendRedirect(logoutUrl);
            });
    }
}
