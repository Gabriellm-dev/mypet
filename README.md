# Gerenciamento de PetShop

---

## Linha do Tempo de Usuários

### 1. Cadastro de Proprietário

**Endpoint:** `POST /owners`

**Descrição:** Um usuário (secretário) insere um novo proprietário no sistema.

**Exemplo de Requisição:**
```http
POST /owners
Content-Type: application/json

{
  "name": "Maria Silva",
  "gender": "F",
  "email": "maria@gmail.com",
  "phone": "1234567890"
}
```

**Resposta:**
```http
HTTP/1.1 201 Created
```

### 2. Cadastro de Pet

**Endpoint:** `POST /pets`

**Descrição:** O usuário (secretário) cadastra um novo pet associado ao proprietário.

**Exemplo de Requisição:**
```http
POST /pets
Content-Type: application/json

{
  "name": "Rex",
  "ownerId": 1,
  "age": 2,
  "gender": "M",
  "species": "Cachorro",
  "breed": "Labrador"
}
```

**Resposta:**
```http
HTTP/1.1 201 Created
```

### 3. Cadastro de Vacina

**Endpoint:** `POST /vaccines`

**Descrição:** O usuário (veterinário) cadastra uma vacina para um pet.

**Exemplo de Requisição:**
```http
POST /vaccines
Content-Type: application/json

{
  "petId": 1,
  "vaccineName": "Vacina Antirrábica",
  "description": "Vacina para prevenção de raiva.",
  "applicationDate": "2024-09-25",
  "registrationDate": "2024-09-25T14:30:00"
}
```

**Resposta:**
```http
HTTP/1.1 201 Created
```

### 4. Cadastro de Histórico de Evolução

**Endpoint:** `POST /histories`

**Descrição:** O usuário (veterinário) registra o histórico de evolução de altura e peso de um pet.

**Exemplo de Requisição:**
```http
POST /histories
Content-Type: application/json

{
  "petId": 1,
  "weight": 25.0,
  "height": 60.0,
  "registrationDate": "2024-09-25T14:45:00"
}
```

**Resposta:**
```http
HTTP/1.1 201 Created
```

### 5. Listar Proprietários

**Endpoint:** `GET /owners`

**Descrição:** O usuário (secretário) busca os dados dos proprietários cadastrados.

**Exemplo de Requisição:**
```http
GET /owners
```

**Resposta:**
```http
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "name": "Maria Silva",
    "gender": "F",
    "email": "maria@gmail.com",
    "phone": "1234567890"
  }
]
```

### 6. Listar Pets

**Endpoint:** `GET /pets`

**Descrição:** O usuário (secretário) busca os dados dos pets cadastrados.

**Exemplo de Requisição:**
```http
GET /pets
```

**Resposta:**
```http
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "name": "Rex",
    "ownerId": 1,
    "age": 2,
    "gender": "M",
    "species": "Cachorro",
    "breed": "Labrador"
  }
]
```

### 7. Listar Vacinas

**Endpoint:** `GET /vaccines`

**Descrição:** O usuário (veterinário) busca as vacinas aplicadas aos pets.

**Exemplo de Requisição:**
```http
GET /vaccines?petId=1
```

**Resposta:**
```http
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "petId": 1,
    "vaccineName": "Vacina Antirrábica",
    "description": "Vacina para prevenção de raiva.",
    "applicationDate": "2024-09-25",
    "registrationDate": "2024-09-25T14:30:00"
  }
]
```

### 8. Listar Histórico de Evolução

**Endpoint:** `GET /histories`

**Descrição:** O usuário (veterinário) busca o histórico de evolução de altura e peso de um pet.

**Exemplo de Requisição:**
```http
GET /histories?petId=1
```

**Resposta:**
```http
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "petId": 1,
    "weight": 25.0,
    "height": 60.0,
    "registrationDate": "2024-09-25T14:45:00"
  }
]
```

### 9. Exclusão de Proprietário

**Endpoint:** `DELETE /owners/{id}`

**Descrição:** O usuário (secretário) exclui um proprietário do sistema.

**Exemplo de Requisição:**
```http
DELETE /owners/1
```

**Resposta:**
```http
HTTP/1.1 204 No Content
```

### 10. Exclusão de Pet

**Endpoint:** `DELETE /pets/{id}`

**Descrição:** O usuário (secretário) exclui um pet do sistema.

**Exemplo de Requisição:**
```http
DELETE /pets/1
```

**Resposta:**
```http
HTTP/1.1 204 No Content
```

### 11. Exclusão de Vacina

**Endpoint:** `DELETE /vaccines/{id}`

**Descrição:** O usuário (veterinário) exclui uma vacina aplicada a um pet.

**Exemplo de Requisição:**
```http
DELETE /vaccines/1
```

**Resposta:**
```http
HTTP/1.1 204 No Content
```

### 12. Exclusão de Histórico

**Endpoint:** `DELETE /histories/{id}`

**Descrição:** O usuário (veterinário) exclui um registro do histórico de evolução de um pet.

**Exemplo de Requisição:**
```http
DELETE /histories/1
```

**Resposta:**
```http
HTTP/1.1 204 No Content
```

---
