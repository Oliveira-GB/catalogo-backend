Com certeza. Com base nos arquivos que você enviou (`pom.xml`, `application.yml`, `ProductController.java`, etc.), preparei um `README.md` completo e técnico para o seu projeto **product\_api**.

Ele inclui a stack tecnológica, instruções de configuração do banco de dados (PostgreSQL), como rodar o projeto e a documentação dos endpoints disponíveis.

Aqui está o conteúdo sugerido para o seu arquivo `README.md`:

````markdown
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

##  Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

* [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)

## Configuração

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
````

## Como Executar

1.  Clone o repositório.
2.  Navegue até a pasta raiz do projeto.
3.  Execute o comando Maven para baixar as dependências e rodar a aplicação:

<!-- end list -->

```bash
./mvnw spring-boot:run
```

ou, se tiver o Maven instalado globalmente:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080` (porta padrão do Spring Boot).

## Endpoints da API

Abaixo estão os endpoints disponíveis no `ProductController`.

### 1\. Criar Produto

  * **URL**: `/products`
  * **Método**: `POST`
  * **Corpo da Requisição (JSON)**:

<!-- end list -->

```json
{
  "name": "Nome do Produto",
  "description": "Descrição detalhada do produto com no mínimo 10 caracteres.",
  "price": 99.90,
  "quantity": 10
}
```

  * **Regras de Validação**:

      * `name`: Obrigatório, entre 5 e 100 caracteres.
      * `description`: Obrigatório, entre 10 e 500 caracteres.
      * `price`: Obrigatório, deve ser positivo.
      * `quantity`: Obrigatório, deve ser maior ou igual a 0.

  * **Resposta (201 Created)**: Retorna o produto criado e o cabeçalho `Location` com a URI do recurso.

### 2\. Excluir Produto

  * **URL**: `/products/{id}`
  * **Método**: `DELETE`
  * **Parâmetros**:
      * `id` (UUID): Identificador único do produto.
  * **Resposta (204 No Content)**: Se o produto for excluído com sucesso ou se o ID não existir.

## Testes

Para executar os testes automatizados:

```bash
mvn test
```

## Estrutura do Projeto

  * `controller`: Camada REST (Endpoints).
  * `service`: Regras de negócio.
  * `repository`: Acesso a dados.
  * `model`: Entidades JPA.
  * `dto`: Objetos de transferência de dados (Records).
  * `mapper`: Conversores MapStruct.

<!-- end list -->

```
```
