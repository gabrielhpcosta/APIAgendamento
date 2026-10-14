# API de Agendamento

API REST desenvolvida em **Java** e **Spring Boot** para gerenciamento de agendamentos.

O projeto foi criado com foco em praticar desenvolvimento backend, organização de uma API REST e persistência de dados utilizando o ecossistema Spring.

## Tecnologias

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Maven

## Funcionalidades

- Criar agendamentos
- Consultar agendamentos
- Buscar agendamentos por data
- Atualizar agendamentos
- Excluir agendamentos

## Endpoints

Base URL: `http://localhost:8080`

| Método | Rota | Parâmetros | Descrição |
|---|---|---|---|
| `POST` | `/agendamento` | corpo JSON | Cria um novo agendamento (retorna `201 Created`) |
| `GET` | `/agendamento` | `data` (ex.: `2026-10-20`) | Lista os agendamentos de um dia |
| `PUT` | `/agendamento` | `cliente`, `dataHoraAgendamento` + corpo JSON | Altera um agendamento existente |
| `DELETE` | `/agendamento` | `cliente`, `dataHoraAgendamento` | Exclui um agendamento (retorna `204 No Content`) |

### Exemplo de requisição

`POST /agendamento`

```json
{
  "servico": "Corte de cabelo",
  "profissional": "Ana",
  "dataHoraAgendamento": "2026-10-20T14:30:00",
  "cliente": "João Silva",
  "telefoneCliente": "19999999999"
}
```

### Exemplos de consulta, alteração e exclusão

```text
GET    /agendamento?data=2026-10-20
PUT    /agendamento?cliente=João Silva&dataHoraAgendamento=2026-10-20T14:30:00
DELETE /agendamento?cliente=João Silva&dataHoraAgendamento=2026-10-20T14:30:00
```

> O banco H2 roda em memória. O console fica disponível em `http://localhost:8080/h2-console` com a aplicação rodando.

## Arquitetura

O projeto segue uma estrutura em camadas, separando responsabilidades da aplicação entre os principais componentes do backend.

```text
src/main/java
├── controller
├── service
├── repository
├── entity
└── dto
```

> A estrutura exata pode variar conforme a evolução do projeto.

## Como executar

### Pré-requisitos

- Java 21
- Git

O projeto inclui o Maven Wrapper, portanto não é obrigatório possuir o Maven instalado globalmente.

### Clonar o repositório

```bash
git clone https://github.com/gabrielhpcosta/APIAgendamento.git
cd APIAgendamento
```

### Executar no Windows

```bash
mvnw.cmd spring-boot:run
```

### Executar no Linux/macOS

```bash
./mvnw spring-boot:run
```

Após a inicialização, a aplicação ficará disponível por padrão em:

```text
http://localhost:8080
```

## Banco de dados

O projeto utiliza **H2 Database**, facilitando a execução local e os estudos sem necessidade de configurar um banco de dados externo.

## Objetivo do projeto

Este projeto faz parte dos meus estudos de desenvolvimento backend com Java e Spring Boot, com foco em:

- APIs REST
- Persistência com JPA
- Separação de responsabilidades
- Boas práticas de desenvolvimento backend
- Evolução da arquitetura de aplicações Spring

## Autor

**Gabriel Henrique Costa**

[LinkedIn](https://www.linkedin.com/in/gabrielhpcosta) · [GitHub](https://github.com/gabrielhpcosta)

Backend Developer focado em Java e Spring Boot.
