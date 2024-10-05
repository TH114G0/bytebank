# Bytebank

**Bytebank** Bytebank é um sistema bancário moderno e robusto, desenvolvido com as melhores práticas de programação em Java. Utilizando a poderosa combinação de Spring Boot, Spring Data JPA e MariaDB, o Bytebank oferece uma experiência de usuário interativa e intuitiva, permitindo a gestão eficiente de dados de usuários, endereços e contas.

## Funcionalidades

### Sistema
- **Cadastro de Usuário**: Permite o cadastro de novos usuários com validações de atributos.
- **Login**: O usuário pode inserir seu email e senha para acessar sua conta.

### Usuário
- **Visualizar Informações**: Permite visualizar informações como nome e email do usuário.
- **Excluir Conta**: Possibilidade de excluir a conta do usuário.

### Endereço
- **Cadastro de Endereço**: Cadastro de endereço completo, incluindo rua, cidade, estado e número do imóvel.
- **Visualizar Informações**: Permite visualizar as informações do endereço cadastrado.

### Conta
- **Cadastro de Conta**: Criação de contas bancárias (poupança ou corrente) associadas aos usuários.
- **Visualizar Informações da Conta**: Visualiza informações como número da conta, tipo de conta e saldo.
- **Operações Bancárias**: Funcionalidades para depósitos, saques e transferências entre contas.

## Tecnologias Utilizadas

<img align="center" alt="Java" src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"/> - A base do projeto, garantindo performance e escalabilidade.</br></br> <img align="center" alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/> - Facilita a configuração e a inicialização do projeto, permitindo um desenvolvimento ágil.</br></br> <img align="center" alt="Spring Data JPA" src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/> - Proporciona uma interface simples para acesso a dados, com suporte a consultas complexas.</br></br> <img align="center" alt="MariaDB" src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white"/> - Um sistema de gerenciamento de banco de dados relacional robusto e confiável, ideal para a estrutura de dados do Bytebank.</br></br> <img align="center" alt="DBeaver" src="https://img.shields.io/badge/DBeaver-6DB33F?style=for-the-badge&logo=dbeaver&logoColor=white"/> - Uma ferramenta de gerenciamento de banco de dados versátil que facilita a manipulação e visualização dos dados, proporcionando uma experiência mais fluida para desenvolvedores e administradores de banco de dados.</br>

### Pré-requisitos

- JDK 11 ou superior
- Maven
- MariaDB
- DBeaver (opcional, para gerenciamento do banco de dados)

