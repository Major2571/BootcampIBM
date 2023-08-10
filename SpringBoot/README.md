
# Projeto Bootcamp IBM - Gestão de Clientes, Produtos e Pedidos

Este é um projeto Spring Boot desenvolvido como parte do Bootcamp IBM. O objetivo principal deste projeto é criar uma aplicação backend para gerenciar clientes e produtos de uma empresa.

## Recursos

-   Operações de Criação, Leitura, Atualização e Exclusão (CRUD) para clientes, produtos e pedidos.
-   Classes DTO (Data Transfer Object) para manipulação de dados entre o frontend e o backend.
-   Testes unitários para as classes de serviço para garantir a funcionalidade e correção do aplicativo.

## Tecnologias Utilizadas

-   Java 17
-   Spring Boot
-   Spring Data JPA
-   Banco de dados H2 (in-memory database)
-   Mockito (para testes)
-   Lombok (para geração de código boilerplate)
-   Jackson (para processamento de dados JSON)

## Como Executar

1.  Clone este repositório para sua máquina local.
2.  Certifique-se de ter o JDK 17 (ou superior) instalado.
3.  Compile o projeto usando `mvn clean install`.
4.  Execute a aplicação com o comando `mvn spring-boot:run`.
5.  A aplicação estará acessível em `http://localhost:8080`.

## Endpoints da API

### Clientes

-   `POST /clientes`: Cria um novo cliente.
-   `GET /clientes`: Recupera uma lista de todos os clientes.
-   `GET /clientes/{id}`: Recupera um cliente específico por ID.
-   `PUT /clientes/{id}`: Atualiza um cliente específico por ID.
-   `DELETE /clientes/{id}`: Exclui um cliente específico por ID.

### Produtos

-   `POST /produtos`: Cria um novo produto.
-   `GET /produtos`: Recupera uma lista de todos os produtos.
-   `GET /produtos/{id}`: Recupera um produto específico por ID.
-   `PUT /produtos/{id}`: Atualiza um produto específico por ID.
-   `DELETE /produtos/{id}`: Exclui um produto específico por ID.

### Pedidos

-   `POST /pedidos`: Cria um novo pedido.
-   `GET /pedidos`: Recupera uma lista de todos os pedidos.
-   `GET /pedidos/{id}`: Recupera um pedido específico por ID.
-   `PUT /pedidos/{id}`: Atualiza um pedido específico por ID.
-   `DELETE /pedidos/{id}`: Exclui um pedido específico por ID.

## Testes Unitários
O aplicativo inclui testes unitários para as classes de serviço para garantir o bom funcionamento da lógica de negócios.

## Observação
Este projeto é destinado a fins educacionais e não cobre todos os aspectos de uma aplicação completa pronta para produção. Ele fornece uma base básica para o gerenciamento de clientes e produtos e pode ser estendido e aprimorado com base em requisitos específicos.