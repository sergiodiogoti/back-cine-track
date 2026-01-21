# 🎬 CineTrack API – Backend

API REST para gerenciamento de um **catálogo de filmes**, desenvolvida em **Spring Boot**, com **autenticação JWT**, **controle de acesso por roles**, **paginação**, **filtro dinâmico com Criteria API** e **persistência em MySQL** (Docker).

Este projeto faz parte de um **desenvolvimento Full Stack**, integrando backend (Spring Boot) e frontend (React).

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5.9**
* Spring Web
* Spring Data JPA
* Spring Security
* OAuth2 Resource Server
* JWT (Auth0 – `java-jwt`)
* Bean Validation
* MySQL 8 (Docker)
* H2 Database (ambiente local/testes)
* Lombok
* Maven

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura **em camadas**, com responsabilidades bem definidas:

```
src/main/java/com/catalogo/filmes
├── config          → Configurações (CORS, Security)
├── controller      → Controllers REST
├── dto             → DTOs de entrada e saída
├── exception       → Tratamento de erros
├── mapper          → Conversão Entity ↔ DTO
├── model           → Entidades JPA
├── payload         → Objetos de filtro (Criteria)
├── repository      → Repositórios JPA
├── security        → JWT, filtros e autenticação
├── service         → Regras de negócio
└── util            → Utilitários
```

---

## 🔐 Segurança e Autenticação

A API utiliza **Spring Security + JWT**.

### 🔑 Login

* Endpoint: `POST /auth/login`
* Retorna:

    * **Token JWT**
    * **Lista de roles do usuário**

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "roles": ["ROLE_ADMIN"]
}
```

### 🛡️ Controle de Acesso

* **ROLE_ADMIN**

    * Criar filmes
    * Atualizar filmes
    * Deletar filmes
* **ROLE_USER**

    * Listar filmes
    * Buscar por ID
    * Buscar com filtro

O controle é feito com `@PreAuthorize`:

```java
@PreAuthorize("hasRole('ADMIN')")
```

---

## 🎥 Funcionalidades da API

### 📌 Criar Filme (ADMIN)

```
POST /api/filmes
```

### 📌 Buscar Filme por ID

```
GET /api/filmes/{id}
```

### 📌 Atualizar Filme (ADMIN)

```
PUT /api/filmes/{id}
```

### 📌 Deletar Filme (ADMIN)

```
DELETE /api/filmes/{id}
```

### 📌 Listar Filmes com Paginação

```
GET /api/filmes?page=0&size=10
```

### 📌 Buscar Filmes com Filtro Dinâmico

```
GET /api/filmes/search?texto=matrix
```

A busca utiliza **Criteria API**, permitindo filtros flexíveis por:

* Título (like, case insensitive)
* Gênero

---

## 🔎 Filtro Dinâmico (Criteria API)

A busca avançada é construída dinamicamente com `EntityManager` e `CriteriaBuilder`, permitindo adicionar filtros conforme os parâmetros enviados, sem criar múltiplos métodos no repositório.

---

## 🗄️ Banco de Dados

### 📦 MySQL (Docker)

O projeto possui **Docker Compose** para subir o MySQL automaticamente.

Arquivo:

```
docker-compose.yml
```

Subir o banco:

```bash
docker-compose up -d
```

O script SQL inicial é executado automaticamente:

* Criação das tabelas
* Criação de usuários e roles
* Inserção de filmes de exemplo

### 👤 Usuários Padrão

| Usuário | Senha  | Role       |
| ------- | ------ | ---------- |
| admin   | 123456 | ROLE_ADMIN |
| user    | 123456 | ROLE_USER  |

> As senhas estão criptografadas com **BCrypt**.

---

## 🧪 Banco em Memória (H2)

Para testes e desenvolvimento rápido, o projeto também suporta **H2**, configurado como dependência `runtime`.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java 17+
* Docker
* Maven

### Passos

```bash
# Subir o banco
docker-compose up -d

# Rodar a aplicação
./mvnw spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

---

## 📌 Observações Importantes

* A API é **stateless** (JWT)
* Separação clara entre **DTOs**, **Entities** e **Regras de Negócio**
* Código preparado para integração com frontend React
* Projeto estruturado para fácil evolução (Swagger, novos filtros, novos domínios)

---

## 📚 Autor

Projeto desenvolvido por **Sérgio**, como parte do aprendizado em **Desenvolvimento Full Stack com Spring Boot e React**.

---

🎬 **CineTrack API – Controle completo do seu catálogo de filmes**
