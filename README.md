# 💰 Carteira de pagamentos Digital

##  Sobre o Projeto

Sistema de carteira digital desenvolvido em **Java 21** com **Spring Boot**, simulando operações financeiras reais como transferências entre usuários, validação de transações e notificações automáticas. O projeto implementa as melhores práticas de desenvolvimento backend, arquitetura em camadas e integração com APIs externas.

##  Tecnologias e Ferramentas Utilizadas

### Backend Core
- **Java 21** - Linguagem principal com recursos modernos
- **Spring Boot 3.x** - Framework principal para desenvolvimento
- **Spring Web** - APIs RESTful
- **Spring Data JPA** - Persistência de dados com ORM
- **Spring Cloud OpenFeign** - Cliente HTTP declarativo para microserviços
- **Spring Validation** - Validação robusta de dados

### Arquitetura e Padrões
- **Arquitetura em Camadas** (Controller, Service, Repository)
- **DTO Pattern** - Transfer Objects para segurança de dados
- **Builder Pattern** - Construção limpa de objetos complexos
- **Repository Pattern** - Abstração da camada de dados
- **Dependency Injection** - Inversão de controle

### Banco de Dados
- **JPA/Hibernate** - ORM robusto
- **UUID** como chave primária - Identificadores únicos globais
- **BigDecimal** - Precisão em valores monetários
- **Relacionamentos JPA** - OneToOne, ManyToOne com mapeamento bidirecional

### Resiliência e Integração
- **Resilience4j** - Circuit Breaker e Retry patterns
- **OpenFeign** - Integração com APIs externas
- **Programação Assíncrona** - CompletableFuture para notificações

### Qualidade de Código
- **Lombok** - Redução de boilerplate
- **SLF4J** - Sistema de logging profissional
- **Bean Validation** - Validações declarativas
- **Exception Handling** - Tratamento centralizado de erros

##  Arquitetura do Sistema

```
📦 src/main/java/io/github/nivaldosilva/wallet/
├── 🎮 controllers/          # Camada de apresentação (REST APIs)
├── 🔧 services/            # Regras de negócio
├── 🗃️  repositories/        # Acesso a dados
├── 🏢 entities/            # Modelos de domínio
├── 📋 dto/                 # Objetos de transferência
├── 🌐 clients/             # Integrações externas
├── ⚠️  exceptions/          # Tratamento de erros
└── ⚙️  config/             # Configurações
```

## 💼 Funcionalidades Implementadas

### 👥 Gestão de Usuários
- ✅ Criação de usuários (Cliente/Comerciante)
- ✅ Validação de CPF/CNPJ únicos
- ✅ Listagem com filtros por tipo
- ✅ Busca por ID

### 💳 Sistema de Carteiras
- ✅ Criação automática de carteira
- ✅ Controle de saldo em tempo real
- ✅ Operações de débito/crédito thread-safe

### 💸 Transferências
- ✅ Validação de saldo suficiente
- ✅ Restrições de negócio (comerciantes não podem pagar)
- ✅ Autorização via API externa
- ✅ Notificações assíncronas
- ✅ Histórico completo de transações

### 🛡️ Segurança e Validação
- ✅ Validação de entrada robusta
- ✅ Tratamento centralizado de exceções
- ✅ Códigos de erro padronizados
- ✅ Logging estruturado

## 📊 Endpoints da API

### Usuários
```http
POST   /users                    # Criar usuário
GET    /users                    # Listar todos os usuários
GET    /users?userType=CUSTOMER  # Filtrar por tipo
GET    /users/{id}               # Buscar por ID
```

### Transações
```http
POST   /transactions             # Processar transferência
GET    /transactions             # Histórico de transações
```

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Banco de dados (H2/PostgreSQL/MySQL)

### Executando o projeto
```bash
# Clone o repositório
git clone [url-do-repositorio]

# Entre no diretório
cd wallet-system

# Execute com Maven
mvn spring-boot:run
```

### Dados de Teste
O sistema possui um **DatabaseSeeder** que popula automaticamente dados de teste:
- 2 usuários cliente com saldos de R$ 1.000,00 e R$ 2.000,00
- 1 comerciante com saldo de R$ 5.000,00

## 🧪 Exemplo de Uso

### Criar Transferência
```json
POST /transactions
{
  "payerId": "uuid-do-pagador",
  "payeeId": "uuid-do-recebedor", 
  "value": 100.50
}
```

### Resposta
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

## 🌟 Destaques Técnicos

### Tratamento de Concorrência
- Uso de `@Transactional` para operações atômicas
- Validações de saldo thread-safe
- Processamento assíncrono de notificações

### Integração com APIs Externas
- Cliente Feign com retry automático
- Tratamento robusto de falhas de rede
- Autorização externa para transações

### Modelagem de Dados
- Relacionamentos JPA bem estruturados
- Uso de BigDecimal para precisão monetária
- Timestamps automáticos com Instant

## 🎯 Competências Demonstradas

### Desenvolvimento Backend
- ✅ APIs RESTful profissionais
- ✅ Arquitetura limpa e escalável
- ✅ Padrões de projeto consolidados
- ✅ Tratamento robusto de erros

### Banco de Dados
- ✅ Modelagem relacional eficiente
- ✅ JPA/Hibernate avançado
- ✅ Transações e integridade de dados

### Integração e Microserviços
- ✅ Comunicação entre serviços
- ✅ Resiliência e tolerância a falhas
- ✅ Processamento assíncrono

### Qualidade e Manutenibilidade
- ✅ Código limpo 
- ✅ Separação clara de responsabilidades
- ✅ Logging e monitoramento

---

