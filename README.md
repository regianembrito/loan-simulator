# loan-simulator
Aplicação backend para um simulador de crédito que permita aos usuários simular empréstimos, visualizando as condições de pagamento baseadas no valor solicitado, taxa de juros e prazo de pagamento.

## Tecnologias

- Kotlin
- Spring Boot
- Maven
- PostgresSQL
- Docker

## Executando a aplicação

Em um terminal execute:
> docker-compose up -d

No PgAdmin, crie um novo servidor com as seguintes configurações:
- Host name/address: localhost
- Port: 8180
- Database: postgreDB

Execute a seguinte query para criar a tabela de simulações:
```sql
CREATE TABLE loan_proposal (
    proposal_id uuid DEFAULT gen_random_uuid(),
    loan_amount DECIMAL NOT NULL,
    birth_date VARCHAR NOT NULL,
    term INTEGER NOT NULL,
    monthly_payment DECIMAL NOT NULL,
    interest_rate DECIMAL NOT NULL,
    total_amount DECIMAL NOT NULL,
    PRIMARY KEY (proposal_id)
);
```

A aplicação estará disponível em http://localhost:8080
Para visualização do swagger, acesse http://localhost:8080/swagger-ui.html

## Endpoints
> POST /api/v1/loan/simulation
> 
### Exemplo de requisição
```json
{
    "loanAmount": 10000,
    "birthDate": "1990-01-01",
    "term": 12
}
``` 

### Exemplo de resposta
```json
{
    "proposalId": "d1b1b3b4-1b1b-4b1b-8b1b-1b1b1b1b1b1b",
    "loanAmount": 10000,
    "birthDate": "1990-01-01",
    "term": 12,
    "monthlyPayment": 1000,
    "interestRate": 0.02,
    "totalAmount": 12000
}
```

> GET /api/v1/loan-simulation/{proposalId}
### Exemplo de requisição
```json
{
    "proposalId": "d1b1b3b4-1b1b-4b1b-8b1b-1b1b1b1b1b1b"
}
```

### Exemplo de resposta
```json
{
    "proposalId": "d1b1b3b4-1b1b-4b1b-8b1b-1b1b1b1b1b1b",
    "loanAmount": 10000,
    "birthDate": "1990-01-01",
    "term": 12,
    "monthlyPayment": 1000,
    "interestRate": 0.02,
    "totalAmount": 12000
}
```
> GET /api/v1/tax-rate/1964-01-01

Dado enviado no formato yyyy-MM-dd
### Exemplo de resposta
```json
{
    "taxRate": 4.0
}
```

## Testes
Para executar os testes, execute o comando:
> mvn test

## Alterações no código
Para aplicar alterações no código, execute o comando:
> ./mvnw clean package
> docker-compose up -d --build
> docker-compose up

Todas as collections do Postman estão disponíveis na raiz do projeto.