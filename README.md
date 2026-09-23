# 🎬 DSMovie — Testes Unitários com JUnit, Mockito e Jacoco

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162)
![Mockito](https://img.shields.io/badge/Mockito-mocks-blue)
![Jacoco](https://img.shields.io/badge/Jacoco-0.8.14-red)

Implementação dos **testes unitários da camada de service** do projeto DSMovie, com cobertura de código medida pelo **Jacoco**.

Este repositório é a resolução do **Desafio DSMovie Jacoco**, parte do módulo **Cobertura de código com Jacoco** do curso **Java Spring Expert**, da [DevSuperior](https://devsuperior.com.br).

---

## 📋 Sobre o desafio

O objetivo é implementar todos os testes unitários de service do DSMovie, isolando cada service de suas dependências com mocks.

- **15 cenários** de teste: 9 em `MovieService`, 2 em `ScoreService` e 4 em `UserService`
- **Mínimo para aprovação:** 12 dos 15 testes
- **Meta de cobertura:** pelo menos **90%** reportados pelo Jacoco com todos os testes

### Competências trabalhadas

- Testes unitários em projeto Spring Boot com Java
- Implementação de testes com JUnit 5 e Mockito
- Cobertura de código com Jacoco

---

## 🎞️ Sobre o projeto DSMovie

O DSMovie é uma API REST de filmes e avaliações de filmes.

- A **consulta** de filmes é pública (não exige login)
- **Inserir, atualizar e deletar** filmes é permitido apenas para usuários `ADMIN`
- **Avaliar** um filme pode ser feito por qualquer usuário logado (`CLIENT` ou `ADMIN`)
- A entidade `Score` guarda a nota (de 0 a 5) que cada usuário deu a cada filme
- A cada nova avaliação, o sistema recalcula a **média** das notas de todos os usuários e a grava no filme (`score`), junto com a contagem de votos (`count`)

### Endpoints

| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| `GET` | `/movies` | Público | Lista filmes paginados (filtro opcional `title`) |
| `GET` | `/movies/{id}` | Público | Busca um filme por id |
| `POST` | `/movies` | `ADMIN` | Insere um filme |
| `PUT` | `/movies/{id}` | `ADMIN` | Atualiza um filme |
| `DELETE` | `/movies/{id}` | `ADMIN` | Remove um filme |
| `PUT` | `/scores` | `CLIENT` ou `ADMIN` | Registra uma nota e recalcula a média do filme |
| `POST` | `/oauth2/token` | Cliente OAuth2 | Obtém o token de acesso (grant `password`) |

---

## 🛠️ Tecnologias

- **Java 25**
- **Spring Boot 4.0.6** (Web MVC, Data JPA, Validation)
- **Spring Security** com OAuth2 Authorization Server e Resource Server (JWT)
- **Banco H2** em memória (perfil `test`)
- **JUnit 5** e **Mockito** para testes
- **Jacoco 0.8.14** para cobertura de código
- **Maven**

---

## 🧪 Cenários de teste

### MovieServiceTests

- [ ] `findAllShouldReturnPagedMovieDTO`
- [ ] `findByIdShouldReturnMovieDTOWhenIdExists`
- [ ] `findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist`
- [ ] `insertShouldReturnMovieDTO`
- [ ] `updateShouldReturnMovieDTOWhenIdExists`
- [ ] `updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist`
- [ ] `deleteShouldDoNothingWhenIdExists`
- [ ] `deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist`
- [ ] `deleteShouldThrowDatabaseExceptionWhenDependentId`

### ScoreServiceTests

- [ ] `saveScoreShouldReturnMovieDTO`
- [ ] `saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId`

### UserServiceTests

- [ ] `authenticatedShouldReturnUserEntityWhenUserExists`
- [ ] `authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists`
- [ ] `loadUserByUsernameShouldReturnUserDetailsWhenUserExists`
- [ ] `loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists`

### Estrutura dos testes

```
src/test/java/com/devsuperior/dsmovie
├── services
│   ├── MovieServiceTests.java
│   ├── ScoreServiceTests.java
│   └── UserServiceTests.java
└── tests
    ├── MovieFactory.java
    ├── ScoreFactory.java
    ├── UserFactory.java
    ├── UserDetailsFactory.java
    └── TokenUtil.java
```

As classes do pacote `tests` são factories que centralizam a criação dos objetos usados nos cenários.

## 🚀 Como executar

**Pré-requisitos:** JDK 25 e Maven instalados.

```bash
# clonar o repositório
git clone https://github.com/obrenoxs/dsmovie-jacoco.git
cd dsmovie-jacoco

# executar os testes
mvn test

# executar os testes e gerar o relatório do Jacoco
mvn clean package
```

Depois do `package`, abra `target/jacoco-report/index.html` no navegador.

### Executar a aplicação

```bash
mvn spring-boot:run
```

A API sobe com o perfil `test` (H2 em memória), e o console do H2 fica disponível em `/h2-console`.

---

## 👨‍💻 Autor

**Breno Oliveira de Souza**
Desenvolvedor Backend Java · Estudante de Engenharia de Software

[![GitHub](https://img.shields.io/badge/GitHub-obrenoxs-181717?logo=github)](https://github.com/obrenoxs)
