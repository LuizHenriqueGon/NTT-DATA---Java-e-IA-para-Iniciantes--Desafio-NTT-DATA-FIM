package br.com.dio.challenge.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal para o Microsserviço de Catálogo de Produtos.
 *
 * Esta aplicação é responsável por gerir todas as operações relacionadas a produtos,
 * como criação, consulta e listagem. Ela também se regista no Eureka Server
 * para ser descoberta por outros serviços na rede.
 */
@SpringBootApplication
@EnableDiscoveryClient // Ativa o registo da aplicação como um cliente no Service Discovery (Eureka).
public class MicrosservicodeCatalogodeProdutosApplication {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(MicrosservicodeCatalogodeProdutosApplication.class, args);
    }

}