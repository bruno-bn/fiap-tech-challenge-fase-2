# FIAP Tech Challenge - Fase 2 | Gestão de Restaurantes

Este projeto é uma API RESTful para gestão de restaurantes, cardápios e perfis de usuários (proprietários e clientes).

## Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 4.1.1
* **Arquitetura:** Clean Architecture e Domain-Driven Design (DDD)
* **Banco de Dados:** PostgreSQL 15 (via Docker)
* **Migrações:** Flyway Database Migrations
* **Testes:** JUnit 5, Mockito, MockMvc
* **Cobertura de Código:** Jacoco 0.8.11 (Configurado para falhar o build se a cobertura for < 80%)
* **Documentação:** Springdoc OpenAPI 3.1.0 (Swagger)
* **Infraestrutura:** Docker e Docker Compose (Multi-stage build)

## Arquitetura do Projeto (Clean Architecture)

O projeto foi desenhado respeitando a **Clean Architecture**, garantindo o isolamento da regra de negócio de bibliotecas 
externas, frameworks e bancos de dados. A separação de pacotes está estruturada da seguinte forma:

* **`core`**: O coração da aplicação, focado exclusivamente no negócio. Não possui nenhuma dependência do Spring ou JPA.
  * **`domain`**: Entidades ricas (`Usuario`, `Restaurante`, `ItemCardapio`, `TipoUsuario`) utilizando factory methods e exceções de domínio isoladas.
  * **`application`**: Casos de uso segregados por domínio (ex: `CriarRestauranteUseCase`, `BuscarItemCardapioUseCase`) e portas/interfaces para comunicação externa (`Gateways`).
* **`infra`**: A camada de infraestrutura que interage com o mundo externo e implementa as interfaces do Core.
  * **`api`**: Controllers REST (`RestauranteController`, `ItemCardapioController`) e DTOs (Request/Response) protegidos por validações `@Valid`. Tratamento de erros centralizado via `GlobalExceptionHandler`.
  * **`database`**: Repositórios do Spring Data JPA, Entidades de Banco de Dados (`Entity`), Mappers de conversão explícitos e implementações dos Gateways (`GatewayImpl`).
  * **`config`**: Configuração de Beans do Spring para injeção de dependências dos UseCases e configuração do Swagger.

## Como Executar o Projeto

A aplicação foi totalmente "dockerizada", utilizando um *multi-stage build* no Dockerfile para otimizar o tamanho da imagem.

### Pré-requisitos
* Docker e Docker Compose instalados.
* Portas `8080`, `8081` e `5432` livres no seu computador.

### Passo a passo via Docker Compose

1. Faça o clone deste repositório e acesse a pasta raiz.
2. Execute o comando para construir e subir os containers em segundo plano:
   ```bash
   docker-compose up --build -d
   ```
3. A infraestrutura garantirá a seguinte orquestração:
   * **`db`**: O banco de dados PostgreSQL iniciará primeiro.
   * **`app`**: A aplicação Spring Boot aguardará o *healthcheck* do banco ficar pronto antes de iniciar. As migrações do Flyway rodarão automaticamente, criando as tabelas e inserindo os dados iniciais.
   * **`adminer`**: Uma interface web para visualizar o banco de dados estará disponível em `http://localhost:8081`.

## Documentação da API (Swagger)

A API possui documentação interativa gerada automaticamente através do Springdoc OpenAPI.
Com a aplicação em execução via Docker ou localmente, acesse o link abaixo no seu navegador para explorar, visualizar contratos e testar os *endpoints*:

🔗 **[Acessar Swagger UI](http://localhost:8080/swagger-ui/index.html)**

## Como Executar os Testes e Cobertura (Jacoco)

O projeto possui um conjunto de testes automatizados, refletindo a arquitetura em ambas as camadas (`core` e `infra`).

Foi configurado um controle de qualidade no `pom.xml` via **Jacoco Maven Plugin**: o build do Maven falhará automaticamente (`BUILD FAILURE`) se a cobertura de testes da regra de negócio for inferior a **80%**. Boilerplates (DTOs, Configurações e Entidades do banco) foram explicitamente ignorados na contagem para refletir a cobertura real do código útil.

Para executar os testes e gerar o relatório localmente (requer Maven e JDK 21 instalados):

1. Na raiz do projeto, execute:
   ```bash
   mvn clean test
   ```
2. Após o `BUILD SUCCESS`, acesse o relatório visual de cobertura abrindo o seguinte arquivo no seu navegador:
   * `target/site/jacoco/index.html`

## Autor

* **Bruno Bastos Nogueira** - RM375275