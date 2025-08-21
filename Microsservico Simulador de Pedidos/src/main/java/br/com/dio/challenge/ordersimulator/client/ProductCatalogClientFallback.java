package br.com.dio.challenge.ordersimulator.client;

import br.com.dio.challenge.ordersimulator.model.Product;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductCatalogClientFallback implements ProductCatalogClient {

    @Override
    public Optional<Product> findById(Long id) {
        // Lógica de fallback: simplesmente imprime uma mensagem e retorna um Optional vazio.
        // Numa aplicação real, poderia registar o erro num sistema de ou retornar um objeto padrão.
        System.out.println("Circuit Breaker ativado! Fallback para o produto ID: " + id);
        return Optional.empty();
    }
}