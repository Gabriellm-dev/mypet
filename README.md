# MyPet - Sistema de Gerenciamento de Pets

MyPet é um sistema completo para o gerenciamento de informações sobre pets, incluindo vacinas, histórico de peso e altura, e muito mais. Este sistema permite que usuários criem, atualizem, deletem e consultem dados sobre seus animais de estimação de maneira fácil e eficiente.

Observações

# Linguagens:
  - **Java**

# Frameworks:

  - **Spring Boot - Framework principal para o desenvolvimento do backend e APIs REST.**
  - **Spring Data JPA - Para persistência de dados e integração com o banco de dados.**
  - **Hibernate - Framework de ORM (Object Relational Mapping) que facilita o trabalho com banco de dados relacionais.**

# Banco de Dados:

  - **PostgreSQL - Banco de dados relacional utilizado no projeto.**

## Funcionalidades

### 1. Usuários

- **Cadastrar Usuário**
- **Atualizar Usuário**
- **Excluir Usuário**
- **Listar Usuários**

### 2. Proprietários (Owners)

- **Cadastrar Proprietário**
- **Atualizar Proprietário**
- **Excluir Proprietário**
- **Listar Proprietários**

### 3. Pets

- **Cadastrar Pet**
- **Atualizar Pet**
- **Excluir Pet**
- **Listar Pets**

### 4. Vacinas

- **Cadastrar Vacina**
- **Atualizar Vacina**
- **Excluir Vacina**
- **Listar Vacinas**

### 5. Histórico de Pesos e Alturas

- **Cadastrar Histórico**
- **Listar Histórico**

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- JDK 11 ou superior
- Maven
- PostgreSQL

## Instalação

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/mypet.git
   cd mypet
   ```

2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `mypet`.
   - Altere as credenciais no arquivo `application.properties` para corresponder ao seu banco de dados.

3. Compile e inicie a aplicação:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints da API

### 1. Usuários

#### Criar Usuário

- **Endpoint:** `POST /api/users`
- **Corpo da Requisição:**
  ```json
  {
      "name": "João Silva",
    "gender": "M",
    "email": "joao.silva@example.com",
    "password": "senhaSegura123",
    "group": "admin"
  }
  ```
- **Resposta:**
  ```json
  {
      "message": "Usuário criado com sucesso: ID 1"
  }
  ```

#### Listar Usuários

- **Endpoint:** `GET /users`
- **Resposta:**
  ```json
  [
      {
      "name": "João Silva",
    "gender": "M",
    "email": "joao.silva@example.com",
    "password": "senhaSegura123",
    "group": "admin"
  }
  ]
  ```

### 2. Proprietários (Owners)

#### Criar Proprietário

- **Endpoint:** `POST /owners`
- **Corpo da Requisição:**
  ```json
  {
    "name": "Maria Oliveira",
    "gender": "F",
    "cpf": "123.456.789-00",
    "email": "maria.oliveira@example.com",
    "cellphone": "(11) 91234-5678"
  } 
  ```
- **Resposta:**
  ```json
  {
      "message": "Proprietário criado com sucesso: ID 1"
  }
  ```

#### Listar Proprietários

- **Endpoint:** `GET /owners`
- **Resposta:**
  ```json
  [
    {
    "id": 1,
    "name": "Maria Oliveira",
    "gender": "F",
    "cpf": "123.456.789-00",
    "email": "maria.oliveira@example.com",
    "cellphone": "(11) 91234-5678",
    "pets": []
    }
  ]
  ```

### 3. Pets

#### Criar Pet

- **Endpoint:** `POST /pets`
- **Corpo da Requisição:**
  ```json
    {
      "name": "Rex",
      "species": "Cão",
      "breed": "Labrador",
      "sex": "M",
      "age": 3,
      "owner": {
        "id": 1
      }
    }
  ```
- **Resposta:**
  ```json
  {
      "message": "Pet criado com sucesso: ID 1"
  }
  ```

#### Listar Pets (Acertar)

- **Endpoint:** `GET /pets`
- **Resposta:**
  ```json
  [
      {
          "id": 1,
          "name": "Rex",
          "ownerId": 1,
          "age": 3,
          "gender": "Macho",
          "species": "Cachorro",
          "breed": "Labrador"
      }
  ]
  ```

### 4. Vacinas

#### Criar Vacina

- **Endpoint:** `POST api/vaccines`
- **Corpo da Requisição:**
  ```json
  {
  "name": "Vacina Anti-rábica",
  "description": "Vacina para prevenção da raiva.",
  "applicationDate": "2024-10-03",
  "pet": {
    "id": 1
  }
  }
  ```
- **Resposta:**
  ```json
  {
  "id": 1,
  "name": "Vacina Anti-rábica",
  "description": "Vacina para prevenção da raiva.",
  "applicationDate": "2024-10-03",
  "createdAt": "2024-10-03T21:31:20.187338332"
  }
  ```

#### Listar Vacinas

- **Endpoint:** `GET /api/vaccines`
- **Parâmetros Opcionais:**
  - `petId` - Filtra vacinas pelo ID do pet.
  - `name` - Filtra vacinas pelo nome.

- **Resposta:**
  ```json
  [
    {
    "id": 1,
    "name": "Vacina Anti-rábica",
    "description": "Vacina para prevenção da raiva.",
    "applicationDate": "2024-10-03",
    "createdAt": "2024-10-03T21:31:20.187338"
    }
  ] 
  ```

### 5. Histórico de Pesos e Alturas

#### Criar Histórico

- **Endpoint:** `POST /api/pet-history`
- **Corpo da Requisição:**
  ```json
  {
  "weight": 12.5,
  "height": 45.0,
  "dateTime": "2024-10-03T14:30:00",
  "pet": {
    "id": 1
  }
  }

  ```
- **Resposta:**
  ```json
  {
  "id": 1,
  "pet": {
    "id": 1,
    "name": null,
    "species": null,
    "breed": null,
    "sex": null,
    "age": null,
    "vaccines": []
  },
  "weight": 12.5,
  "height": 45.0,
  "dateTime": "2024-10-03T14:30:00"
  } 
  ```

#### Listar Histórico (Acertar)

- **Endpoint:** `GET /api/pet-history`
- **Parâmetro Opcionais:**
  - `petId` - Filtra o histórico pelo ID do pet.

- **Resposta:**
  ```json
  [
      {
          "id": 1,
          "petId": 1,
          "weight": 20.5,
          "height": 60.0,
          "dateTime": "2023-10-01T10:00:00"
      }
  ]
  ```

  Para orientar as pessoas a utilizarem o seu projeto no GitHub, você pode adicionar uma seção de **Como Executar o Projeto** no seu README. Vou sugerir um modelo de como organizar essa seção, explicando passo a passo como elas podem clonar, configurar e executar o projeto. Aqui está um exemplo:

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) (ou versão compatível)
- [Maven](https://maven.apache.org/) - Para gerenciar as dependências e build do projeto
- [PostgreSQL](https://www.postgresql.org/) - Banco de dados utilizado pelo projeto
- [Git](https://git-scm.com/) - Para clonar o repositório

### Passos para Instalação

1. **Clone este repositório**
   ```
   git clone https://github.com/Gabriellm-dev/mypet.git
   ```
   
2. **Acesse o diretório do projeto**
   ```
   cd seu-repositorio
   ```

3. **Configure o Banco de Dados**
   - Crie um banco de dados PostgreSQL com o nome `mypet`.
   - Configure as credenciais do banco no arquivo `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/mypet
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     ```
   - Execute o seguinte comando SQL no PostgreSQL para criar o banco:
     ```sql
     CREATE DATABASE mypet;
     ```

4. **Instale as dependências do projeto**
   No diretório raiz do projeto, execute o Maven para baixar as dependências:
   ```
   mvn clean install
   ```

5. **Execute o Projeto**
   Agora que tudo está configurado, você pode executar o projeto com o Maven:
   ```
   mvn spring-boot:run
   ```

6. **Acessando a Aplicação**
   A aplicação será executada em `http://localhost:8080`. Para acessar as APIs de gerenciamento de pets, você pode usar ferramentas como o Postman ou diretamente em seu navegador.
