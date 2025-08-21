package br.com.dio.challenge.ordersimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Classe principal para o Microsserviço Simulador de Pedidos.
 *
 * Esta aplicação simula a criação de um pedido, comunicando-se com o
 * microsserviço de catálogo de produtos para obter os detalhes dos itens.
 * É um serviço sem estado (stateless) e não possui a sua própria base de dados.
 */
@SpringBootApplication
@EnableDiscoveryClient // Ativa o registo da aplicação como um cliente no Service Discovery (Eureka).
@EnableFeignClients // Ativa a funcionalidade do OpenFeign para criar clientes REST declarativos.
public class MicrosservicoSimuladorDePedidosApplication {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(MicrosservicoSimuladorDePedidosApplication.class, args);
    }
}