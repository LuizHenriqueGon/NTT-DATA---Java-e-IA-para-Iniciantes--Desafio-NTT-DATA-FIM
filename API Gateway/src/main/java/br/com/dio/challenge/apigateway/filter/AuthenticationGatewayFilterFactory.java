package br.com.dio.challenge.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Filtro de Gateway customizado para validar um token de autenticação.
 *
 * Esta classe segue a convenção de nomenclatura do Spring Cloud Gateway. Ao ser nomeada
 * 'AuthenticationGatewayFilterFactory', ela pode ser referenciada como 'Authentication'
 * no ficheiro de configuração application.yml.
 */
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    // Token estático utilizado para a validação, conforme definido no desafio.
    private static final String SECRET_TOKEN = "Bearer eyJhbGciOiUzI1NiIsInR5cCI6IkpXVCJ9";

    public AuthenticationGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * Aplica a lógica do filtro a cada requisição que passa por ele.
     * @param config A configuração do filtro (não utilizada neste caso).
     * @return O filtro de gateway a ser aplicado.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 1. Obtém os cabeçalhos da requisição.
            HttpHeaders headers = exchange.getRequest().getHeaders();

            // 2. Verifica se o cabeçalho 'Authorization' está presente.
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                // Se não estiver, retorna um erro 401 Unauthorized.
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            // 3. Obtém o valor do cabeçalho 'Authorization'.
            String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            // 4. Valida se o token recebido é igual ao token secreto.
            if (!SECRET_TOKEN.equals(authorizationHeader)) {
                // Se for diferente, retorna um erro 401 Unauthorized.
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            // 5. Se o token for válido, permite que a requisição continue para o microsserviço de destino.
            return chain.filter(exchange);
        };
    }

    /**
     * Método auxiliar para retornar uma resposta de erro HTTP.
     * @param exchange O objeto da requisição/resposta atual.
     * @param status O status HTTP a ser retornado (ex: 401 Unauthorized).
     * @return Um Mono<Void> que finaliza a cadeia de filtros.
     */
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    /**
     * Classe de configuração interna vazia, necessária para a superclasse.
     */
    public static class Config {
    }
}