package br.com.dio.challenge.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuração de segurança para o API Gateway.
 * Utiliza o stack de segurança reativo (WebFlux) para ser compatível com o Spring Cloud Gateway.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Configura a cadeia de filtros de segurança do Spring.
     * @param http O objeto para configurar a segurança HTTP.
     * @return A cadeia de filtros de segurança configurada.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                // Desativa a proteção contra Cross-Site Request Forgery (CSRF),
                // uma prática comum para APIs stateless.
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Configura as regras de autorização.
                .authorizeExchange(exchange -> exchange
                        // Permite todas as requisições por padrão. A lógica de bloqueio
                        // será tratada pelo nosso filtro customizado (AuthenticationGatewayFilterFactory).
                        .anyExchange().permitAll()
                )
                .build();
    }
}