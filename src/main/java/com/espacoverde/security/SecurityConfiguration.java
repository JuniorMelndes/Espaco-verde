package com.espacoverde.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de segurança para a aplicação.
 */
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer{
	
	/**
     * Configura o filtro de segurança para as requisições HTTP.
     *
     * @param http O objeto HttpSecurity a ser configurado.
     * @return O filtro de segurança configurado.
     * @throws Exception Se ocorrer um erro durante a configuração do filtro.
     */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/admin/**"))
		.hasRole("ADMIN")
		.requestMatchers("/user/**")
		.permitAll()
		.and()
		.httpBasic();
		 return http.build();
	}
	
	/**
     * Configura o serviço de detalhes do usuário.
     *
     * @return O UserDetailsService configurado.
     */
	@Bean
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
		return new InMemoryUserDetailsManager(admin);
	}
	
	/**
     * Configura a negociação de conteúdo padrão.
     *
     * @param configurer O configurer ContentNegotiationConfigurer a ser configurado.
     */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}	