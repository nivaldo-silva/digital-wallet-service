# Sistema de Carteira Digital

## Sobre o Projeto

Sistema de carteira digital desenvolvido em **Java 21** com **Spring Boot**, implementando operações financeiras como transferências entre usuários, validação de transações e notificações automáticas. O projeto aplica as melhores práticas de desenvolvimento backend, arquitetura em camadas e integração com serviços externos.

## Stack Tecnológica

### Core Framework
- **Java 21** - Linguagem principal
- **Spring Boot 3.5.4 - Framework de desenvolvimento
- **Spring Web** - APIs RESTful
- **Spring Data JPA** - Persistência de dados
- **Spring Cloud OpenFeign** - Cliente HTTP declarativo
- **Spring Validation** - Validação de dados

### Arquitetura e Padrões
- **Arquitetura em Camadas** (Controller, Service, Repository)
- **DTO Pattern** - Objetos de transferência de dados
- **Builder Pattern** - Construção de objetos complexos
- **Repository Pattern** - Abstração da camada de dados
- **Dependency Injection** - Inversão de controle

### Persistência
- **JPA/Hibernate** - ORM
- **UUID** - Identificadores únicos
- **BigDecimal** - Precisão em valores monetários
- **Relacionamentos JPA** - Mapeamento bidirecional

### Resiliência
- **Resilience4j** - Circuit Breaker e Retry
- **OpenFeign** - Integração com APIs externas
- **CompletableFuture** - Processamento assíncrono

## Arquitetura do Sistema

```
src/main/java/io/github/nivaldosilva/wallet/
├── controllers/          # Camada de apresentação
├── services/            # Regras de negócio
├── repositories/        # Acesso a dados
├── entities/            # Modelos de domínio
├── dto/                 # Objetos de transferência
├── clients/             # Integrações externas
├── exceptions/          # Tratamento de erros
└── config/             # Configurações
```

## Funcionalidades

### Gestão de Usuários
- Criação de usuários (Cliente/Comerciante)
- Validação de CPF/CNPJ únicos
- Listagem com filtros por tipo
- Busca por identificador

### Sistema de Carteiras
- Criação automática de carteira
- Controle de saldo em tempo real
- Operações de débito/crédito thread-safe

### Transferências
- Validação de saldo suficiente
- Restrições de negócio
- Autorização via API externa
- Notificações assíncronas
- Histórico completo de transações

## API Endpoints

### Usuários
```
POST   /users                    # Criar usuário
GET    /users                    # Listar usuários
GET    /users?userType=CUSTOMER  # Filtrar por tipo
GET    /users/{id}               # Buscar por ID
```

### Transações
```
POST   /transactions             # Processar transferência
GET    /transactions             # Histórico de transações
```

## Configuração

### Pré-requisitos
- Java 21+
- Maven 
- Banco de dados compatível

### Execução
```bash
git clone [url-do-repositorio]
cd wallet-system
mvn spring-boot:run
```

### Dados de Teste
O sistema inclui um **DatabaseSeeder** que popula dados iniciais:
- 2 usuários cliente (R$ 1.000,00 e R$ 2.000,00)
- 1 comerciante (R$ 5.000,00)

## Exemplo de Integração

### Request - Transferência
```json
POST /transactions
{
  "payerId": "uuid-do-pagador",
  "payeeId": "uuid-do-recebedor", 
  "value": 100.50
}
```

### Response
```json
{
  "id": "uuid-da-transacao",
  "payer": { "id": "uuid-pagador" },
  "payee": { "id": "uuid-recebedor" },
  "value": 100.50,
  "currency": "BRL",
  "transactionDateTime": "2024-01-15T10:30:00Z"
}
```

## Aspectos Técnicos Relevantes

### Concorrência
- Transações atômicas com `@Transactional`
- Validações thread-safe
- Processamento assíncrono de notificações

### Integrações
- Cliente Feign com retry automático
- Tratamento de falhas de rede
- Autorização externa para transações

### Modelagem
- Relacionamentos JPA estruturados
- Precisão monetária com BigDecimal
- Timestamps automáticos

## Competências Técnicas Demonstradas

### Backend Development
- Design e implementação de APIs RESTful
- Arquitetura limpa e escalável
- Aplicação de padrões de projeto
- Tratamento robusto de exceções

### Persistência de Dados
- Modelagem relacional eficiente
- JPA/Hibernate avançado
- Gerenciamento de transações
- Integridade referencial

### Integração de Sistemas
- Comunicação entre serviços
- Implementação de resiliência
- Processamento assíncrono
- Tolerância a falhas

### Qualidade de Software
- Código limpo e manutenível
- Separação clara de responsabilidades
- Logging estruturado
- Validação robusta de dados

