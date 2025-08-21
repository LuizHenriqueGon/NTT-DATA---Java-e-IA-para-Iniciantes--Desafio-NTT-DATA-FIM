package br.com.dio.challenge.productcatalog.repository;

import br.com.dio.challenge.productcatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade Product.
 *
 * Ao estender JpaRepository, esta interface herda automaticamente métodos para
 * operações CRUD (Create, Read, Update, Delete) e outras operações de consulta
 * comuns, sem a necessidade de implementação manual.
 *
 * @param /<Product> A entidade que este repositório gere.
 * @param /<Long> O tipo da chave primária da entidade (o ID do produto).
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}