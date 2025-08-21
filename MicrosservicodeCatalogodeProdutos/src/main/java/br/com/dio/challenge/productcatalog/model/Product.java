package br.com.dio.challenge.productcatalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entidade JPA que representa um Produto no catálogo.
 * A anotação @Entity indica que esta classe será mapeada para uma tabela na base de dados.
 */
@Entity
@Data // Anotação do Lombok que gera automaticamente getters, setters, toString(), equals() e hashCode().
public class Product {

    /**
     * Identificador único do produto.
     * @Id marca este campo como a chave primária da tabela.
     * @GeneratedValue define a estratégia de geração automática do valor (neste caso, identidade).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Descrição detalhada do produto.
     */
    private String description;

    /**
     * Preço do produto.
     */
    private Double price;

}