package br.com.dio.challenge.productcatalog.controller;

import br.com.dio.challenge.productcatalog.model.Product;
import br.com.dio.challenge.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST que expõe os endpoints para gestão de produtos.
 * Todas as requisições para "/produtos" são tratadas por esta classe.
 */
@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Endpoint para criar um novo produto no catálogo.
     * @param product O objeto Product enviado no corpo da requisição (JSON).
     * @return O produto que foi salvo na base de dados, incluindo o seu ID gerado.
     */
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Endpoint para listar todos os produtos disponíveis no catálogo.
     * @return Uma lista de todos os produtos.
     */
    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Endpoint para encontrar um produto específico pelo seu ID.
     * @param id O ID do produto a ser procurado, passado como uma variável no caminho (path variable).
     * @return Um Optional contendo o produto, se encontrado.
     */
    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id);
    }
}