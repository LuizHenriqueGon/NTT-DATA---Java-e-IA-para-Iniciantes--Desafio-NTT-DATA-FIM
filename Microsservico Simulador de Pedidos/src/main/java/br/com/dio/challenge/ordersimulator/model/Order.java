package br.com.dio.challenge.ordersimulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private List<Product> products;
    private Double total;
}