# Desafio Técnico: Arquitetura de Microsserviços com Spring Cloud

Este projeto é uma implementação de uma arquitetura de microsserviços baseada no desafio técnico proposto pela Digital Innovation One (DIO). O sistema simula um catálogo de produtos simples e um serviço de pedidos, utilizando as principais ferramentas do ecossistema Spring para construir aplicações distribuídas, resilientes e seguras.

## Arquitetura Proposta

O sistema é composto por quatro microsserviços principais, que são registados e descobertos através do Eureka Server. O acesso externo é centralizado por um API Gateway, que também é responsável por aplicar uma camada de segurança baseada em token.

![Diagrama da Arquitetura](https://hermes.dio.me/files/assets/c2e4ece2-999a-4c35-b4b2-3171ac7d0308.png)
*(Sugestão: Crie a pasta `/images` na raiz do seu projeto e coloque a imagem do desafio `image_0749bd.jpg` nela, renomeando-a para `arquitetura.png`)*

---

### Tecnologias Utilizadas
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge) 
![Status](https://img.shields.io/badge/Status-Completo-brightgreen?style=for-the-badge)

| Tecnologia | Ícone | Descrição |
| :--- | :---: | :--- |
| **Java 21** | <img src="https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=openjdk" alt="Java"> | Linguagem de programação principal do projeto. |
| **Spring Boot 3** | <img src="https://img.shields.io/badge/Spring_Boot-3.5.5-green?style=for-the-badge&logo=spring" alt="Spring Boot"> | Framework para criação de aplicações stand-alone e robustas. |
| **Spring Cloud** | <img src="https://img.shields.io/badge/Spring_Cloud-2025.0.0-green?style=for-the-badge&logo=spring" alt="Spring Cloud"> | Conjunto de ferramentas para desenvolvimento de padrões em sistemas distribuídos. |
| **API Gateway** | <img src="https://raw.githubusercontent.com/spring-cloud/spring-cloud-gateway/main/docs/src/main/asciidoc/images/spring-cloud-gateway.png" alt="API Gateway" width="90"> | Ponto de entrada único (Single Point of Entry) para as APIs dos microsserviços. |
| **Eureka** | <img src="https://img.stackshare.io/service/4694/20160228-202202.png" alt="Eureka" width="60"> | Service Discovery para localização dinâmica dos serviços na rede. |
| **Spring Security** | <img src="https://spring.io/images/projects/spring-security-520e78c69d85481a525f0a200a7fd967.svg" alt="Spring Security" width="60"> | Framework de autenticação e controlo de acesso. |
| **OpenFeign** | <img src="https://img.stackshare.io/service/11082/spring-cloud-openfeign.png" alt="OpenFeign" width="60"> | Cliente HTTP declarativo para facilitar a comunicação entre serviços. |
| **Maven** | <img src="https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apachemaven" alt="Maven"> | Ferramenta de automação de compilação e gestão de dependências. |
| **Git** | <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git"> | Sistema de controlo de versões distribuído. |
---

### Como Executar o Projeto

**Pré-requisitos:**
- Java Development Kit (JDK) 21 ou superior
- Apache Maven 3.9 ou superior
- Git

**Ordem de Inicialização (Crucial):**

Os serviços devem ser iniciados na seguinte ordem para garantir que a descoberta de serviços e o roteamento funcionem corretamente.

1.  **`service-discovery`**: É o primeiro a ser iniciado, pois é o "catálogo" onde os outros serviços irão se registar.
2.  **`product-catalog`**: Inicia e regista-se no Eureka.
3.  **`order-simulator`**: Inicia, regista-se no Eureka e depende do `product-catalog` para as suas operações.
4.  **`api-gateway`**: É o último a ser iniciado. Ele irá obter a localização dos outros serviços a partir do Eureka para configurar as suas rotas.

**URLs importantes:**
- **Painel do Eureka:** `http://localhost:8761`
- **API Gateway (Ponto de entrada):** `http://localhost:8765`
- **Consola do Banco H2:** `http://localhost:8100/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

---

### Destaques Técnicos e Trechos de Código

#### 1. Segurança Centralizada no API Gateway

A segurança é gerida exclusivamente pelo API Gateway. Usamos um `AbstractGatewayFilterFactory` customizado para intercetar todas as requisições e validar um token `Bearer` estático. Desta forma, os microsserviços internos não precisam de se preocupar com a autenticação.

*Ficheiro: `API Gateway/.../filter/AuthenticationGatewayFilterFactory.java`*
```java
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private static final String SECRET_TOKEN = "Bearer eyJhbGciOiUzI1NiIsInR5cCI6IkpXVCJ9";

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            // ... validação do token ...
            return chain.filter(exchange);
        };
    }
    // ...
}

