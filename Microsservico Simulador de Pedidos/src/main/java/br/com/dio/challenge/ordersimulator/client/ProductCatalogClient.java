package br.com.dio.challenge.ordersimulator.client;

import br.com.dio.challenge.ordersimulator.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

// Adicionamos a referência para a nossa classe de fallback
@FeignClient(name = "product-catalog", fallback = ProductCatalogClientFallback.class)
public interface ProductCatalogClient {

    @GetMapping("/produtos/{id}")
    // O nome 'product-catalog' deve ser o mesmo que definimos no application.properties
    @CircuitBreaker(name = "product-catalog")
    Optional<Product> findById(@PathVariable Long id);
}