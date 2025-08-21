package br.com.dio.challenge.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal para o Microsserviço API Gateway.
 *
 * Esta aplicação atua como o ponto de entrada único para todas as requisições externas.
 * É responsável por rotear o tráfego para os microsserviços apropriados e
 * aplicar filtros de segurança.
 */
@SpringBootApplication
@EnableDiscoveryClient // Ativa o registo da aplicação como um cliente no Service Discovery (Eureka).
public class ApiGatewayApplication {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}