package br.com.dio.challenge.ordersimulator.controller;

import br.com.dio.challenge.ordersimulator.client.ProductCatalogClient;
import br.com.dio.challenge.ordersimulator.model.Order;
import br.com.dio.challenge.ordersimulator.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    private ProductCatalogClient productCatalogClient;

    @PostMapping
    public Order simulateOrder(@RequestBody List<Long> productIds) {
        List<Product> products = new ArrayList<>();
        Double total = 0.0;

        for (Long productId : productIds) {
            Optional<Product> productOpt = productCatalogClient.findById(productId);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                products.add(product);
                total += product.getPrice();
            }
        }

        return new Order(products, total);
    }
}