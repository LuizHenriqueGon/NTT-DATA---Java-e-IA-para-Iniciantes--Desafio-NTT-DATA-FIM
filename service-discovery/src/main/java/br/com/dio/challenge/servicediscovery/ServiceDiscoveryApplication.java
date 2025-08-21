package br.com.dio.challenge.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Classe principal para o Microsserviço de Service Discovery (Eureka Server).
 *
 * Esta aplicação atua como o "catálogo de serviços" da nossa arquitetura de microsserviços.
 * Todos os outros serviços irão se registar aqui para que possam ser descobertos
 * dinamicamente por outros componentes, como o API Gateway.
 */
@SpringBootApplication
@EnableEurekaServer // Esta é a anotação mais importante. Ela transforma esta aplicação Spring Boot num servidor Eureka.
public class ServiceDiscoveryApplication {

    /**
     * Ponto de entrada da aplicação.
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }

}
