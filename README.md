# catalogo-backend

# Product API

API REST desenvolvida em Java com Spring Boot para o gerenciamento de produtos.

## 🛠 Tecnologias Utilizadas

* **Java 21**: Linguagem de programação.
* **Spring Boot 3.5.7**: Framework principal.
* **Spring Data JPA**: Camada de persistência de dados.
* **PostgreSQL**: Banco de dados relacional.
* **Lombok**: Para redução de código boilerplate.
* **MapStruct**: Para mapeamento entre Entidades e DTOs.
* **Spring Security & OAuth2**: Para segurança e autorização (Authorization Server e Client).
* **Flyway/Hibernate DDL**: Gerenciamento de schema (configurado como `update` no momento).
* **Maven**: Gerenciamento de dependências.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

* [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)

## ⚙️ Configuração

### Banco de Dados

O projeto está configurado para conectar-se a um banco de dados PostgreSQL local.

1.  Crie um banco de dados chamado `product_api_db`.
2.  Certifique-se de que o PostgreSQL esteja rodando na porta `5432`.

As configurações padrão estão no arquivo `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/product_api_db
    username: admin   # Altere conforme seu ambiente
    password: admin123 # Altere conforme seu ambiente
