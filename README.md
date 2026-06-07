# 🥋 Sistema Marcial - Academia de Lutas Marciais

Um sistema completo de gerenciamento para academias de lutas marciais, desenvolvido em Java com Spring Boot. Controla desde os dados dos usuários até as modalidades, graduações, planos de mensalidade e faturas.

## 📋 Sumário

- [Visão Geral](#visão-geral)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Stack Tecnológico](#-stack-tecnológico)
- [Arquitetura](#-arquitetura-e-estrutura)
- [Instalação e Configuração](#-instalação-e-configuração)
- [Como Executar](#-como-executar)
- [API Endpoints](#-api-endpoints)
- [Entidades Principais](#-entidades-principais)
- [Migrações do Banco de Dados](#-migrações-de-banco-de-dados)
- [Documentação da API](#-documentação-da-api)
- [Próximas Atualizações](#-próximas-atualizações)
- [Contribuições](#-contribuições)
- [Autor](#-autor)

---

## 🎯 Visão Geral

O **Sistema Marcial** foi desenvolvido para facilitar o gerenciamento operacional de academias de lutas marciais. A plataforma permite:

- ✅ Gerenciar dados completos de alunos (pessoais, contato, endereço)
- ✅ Controlar modalidades e lutas oferecidas (Jiu-Jitsu, Muay Thai, Boxe, etc.)
- ✅ Definir graduações/níveis para cada modalidade
- ✅ Criar e gerenciar planos de mensalidade
- ✅ Registrar matrículas de alunos
- ✅ Gerar e controlar faturas de pagamento
- ✅ Registrar assiduidade (entrada/saída dos alunos)
- ✅ Gerar relatórios financeiros e gerenciais

---

## 🎯 Funcionalidades Principais

### 👥 Gerenciamento de Alunos
- Cadastro completo com dados pessoais
- Informações de contato (telefone, celular, email)
- Dados de endereço completo
- Histórico de criação e atualização
- Validação de email único
- Filtros por nome, email, celular, cidade e estado

### 🥋 Modalidades de Lutas
- Cadastro de modalidades (Jiu-Jitsu, Muay Thai, Boxe, Taekwondo, etc.)
- Ativação/desativação de modalidades
- Associação com graduações e planos

### 📊 Graduações/Faixas
- Níveis de experiência por modalidade
- Exemplo: Faixa Branca, Faixa Roxa, Faixa Marrom, Faixa Preta

### 💰 Planos e Mensalidades
- Definição de planos de pagamento
- Valores específicos por modalidade
- Ativação/desativação de planos

### 📝 Matrículas
- Associação de alunos à academia
- Registro de data de matrícula
- Dia de vencimento configurável
- Controle de status (Ativa/Encerrada)

### 📄 Matrículas por Modalidade
- Alunos podem se matricular em múltiplas modalidades
- Graduação atual em cada modalidade
- Plano associado
- Datas de início e fim

### 💳 Faturas
- Geração automática de faturas de mensalidade
- Controle de status (Aberta, Paga, Cancelada)
- Registro de data de pagamento
- Registro de cancelamento

### 📌 Assiduidade
- Registro de entrada e saída dos alunos
- Timestamp automático na entrada
- Controle de frequência

### 📈 Relatórios
- **Faturamento Mensal**: Total de faturamento por mês
- **Alunos por Cidade**: Distribuição geográfica dos alunos
- **Faturas em Aberto**: Listagem de pagamentos pendentes

---

## 🛠️ Stack Tecnológico

| Tecnologia | Descrição | Versão |
|------------|-----------|--------|
| **Java** | Linguagem de programação principal | 17+ |
| **Spring Boot** | Framework web e de aplicação | 4.0.6 |
| **Spring Data JPA** | Persistência de dados e ORM | - |
| **PostgreSQL** | Banco de dados relacional | 12+ |
| **Flyway** | Controle de versão do banco de dados | - |
| **Maven** | Gerenciador de dependências e build | 3.6+ |
| **Lombok** | Redução de boilerplate em Java | - |
| **Swagger/OpenAPI** | Documentação interativa da API | 2.8.5 |
| **Spring Validation** | Validação de dados | - |

---

## 📊 Arquitetura e Estrutura

O projeto segue a arquitetura **MVC (Model-View-Controller)** com separação clara de responsabilidades:

```
src/
├── main/
│   ├── java/com/rafasantosdevv/sysMarcial/
│   │   ├── config/               # Configurações (OpenAPI, etc)
│   │   ├── controller/           # Controllers REST API
│   │   ├── service/              # Lógica de negócio
│   │   ├── domain/               # Entidades JPA (Models)
│   │   ├── dto/                  # Data Transfer Objects (Request/Response)
│   │   ├── repository/           # Interfaces de acesso a dados
│   │   ├── specification/        # JPA Specifications para filtros
│   │   ├── exception/            # Tratamento de exceções
│   │   ├── projection/           # Projections para relatórios
│   │   └── doc/                  # Documentação Swagger
│   └── resources/
│       ├── application.properties # Configurações da aplicação
│       └── db/migration/         # Scripts Flyway (versionamento DB)
└── test/
    └── java/                     # Testes unitários
```

### Padrões de Design Utilizados:
- **DTO Pattern**: Separação entre modelos de domínio e transferência de dados
- **Repository Pattern**: Abstração de acesso a dados
- **Service Pattern**: Lógica de negócio centralizada
- **Specification Pattern**: Filtros dinâmicos com JPA Specifications
- **Exception Handling**: Tratamento global de exceções

---

## 📦 Entidades Principais

### **Aluno**
Representa um aluno cadastrado na academia.

**Campos principais:**
- `id`: Identificador único
- `nome`: Nome completo (obrigatório, máx 150 caracteres)
- `dataNascimento`: Data de nascimento (deve estar no passado)
- `sexo`: M (Masculino) ou F (Feminino)
- `telefone`: Telefone de contato
- `celular`: Celular de contato
- `email`: Email único (validado)
- `observacao`: Observações gerais
- `endereco`: Rua do endereço
- `numero`: Número da residência
- `complemento`: Apartamento, bloco, etc
- `bairro`: Bairro
- `cidade`: Cidade
- `estado`: Estado (sigla: SP, RJ, etc)
- `cep`: CEP
- `criadoEm`: Data/hora de criação (preenchida automaticamente)
- `atualizadoEm`: Data/hora da última atualização (preenchida automaticamente)

### **Modalidade**
Define os tipos de lutas/artes marciais oferecidas.

**Campos principais:**
- `id`: Identificador único
- `nome`: Nome da modalidade (ex: Jiu-Jitsu, Muay Thai, Boxe)
- `ativa`: Status ativo/inativo (padrão: true)

### **Graduacao**
Níveis de experiência dentro de uma modalidade.

**Campos principais:**
- `id`: Identificador único
- `nome`: Nome da graduação (ex: Faixa Branca, Faixa Roxa, Faixa Preta)
- `modalidade`: Referência à modalidade (relacionamento ManyToOne)

### **Plano**
Planos de mensalidade oferecidos.

**Campos principais:**
- `id`: Identificador único
- `nome`: Nome do plano
- `valorMensal`: Valor mensal em BigDecimal
- `modalidade`: Referência à modalidade (relacionamento ManyToOne)
- `ativo`: Status ativo/inativo (padrão: true)

### **Matricula**
Associação de um aluno à academia.

**Campos principais:**
- `id`: Identificador único
- `aluno`: Referência ao aluno (relacionamento ManyToOne)
- `dataMatricula`: Data de matrícula
- `diaVencimento`: Dia do mês em que a mensalidade vence
- `dataEncerramento`: Data de encerramento (opcional)
- `status`: Status da matrícula (ATIVA, ENCERRADA)

### **MatriculaModalidade**
Associação entre matrícula e modalidade/plano específico.

**Campos principais:**
- `id`: Identificador único
- `matricula`: Referência à matrícula (relacionamento ManyToOne)
- `modalidade`: Referência à modalidade (relacionamento ManyToOne)
- `graduacao`: Graduação atual do aluno (relacionamento ManyToOne)
- `plano`: Plano contratado (relacionamento ManyToOne)
- `dataInicio`: Data de início (preenchida automaticamente com data atual)
- `dataFim`: Data de término (opcional)

### **FaturaMatricula**
Controle de faturas de mensalidade.

**Campos principais:**
- `id`: Identificador único
- `matricula`: Referência à matrícula (relacionamento ManyToOne)
- `dataVencimento`: Data de vencimento
- `valor`: Valor da fatura (BigDecimal)
- `dataPagamento`: Data/hora do pagamento (preenchida ao pagar)
- `dataCancelamento`: Data de cancelamento (se cancelada)
- `status`: Status da fatura (ABERTA, PAGA, CANCELADA)

### **Assiduidade**
Registro de presença dos alunos.

**Campos principais:**
- `id`: Identificador único
- `matricula`: Referência à matrícula (relacionamento ManyToOne)
- `dataEntrada`: Data/hora de entrada (preenchida automaticamente)
- `dataSaida`: Data/hora de saída (preenchida ao sair)

---

## 🚀 Instalação e Configuração

### Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17+**: [Download Java](https://www.oracle.com/java/technologies/downloads/#java17)
- **Maven 3.6+**: [Download Maven](https://maven.apache.org/download.cgi)
- **PostgreSQL 12+**: [Download PostgreSQL](https://www.postgresql.org/download/)

### 1. Clone o Repositório

```bash
git clone https://github.com/rafasantosdevv/sysMarcial.git
cd sysMarcial
```

### 2. Configuração do Banco de Dados

#### Criar o banco de dados PostgreSQL:

```sql
CREATE DATABASE academia;
```

#### Atualizar as credenciais

Abra `src/main/resources/application.properties` e configure suas credenciais:

```properties
# Configurações do Datasource
spring.datasource.url=jdbc:postgresql://localhost:5432/academia
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configurações JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# Configurações Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

**Explicação das propriedades:**
- `spring.datasource.url`: URL de conexão com o banco
- `spring.datasource.username`: Usuário do PostgreSQL
- `spring.datasource.password`: Senha do PostgreSQL
- `spring.jpa.hibernate.ddl-auto=validate`: Valida as tabelas sem criar/alterar
- `spring.flyway.enabled=true`: Ativa as migrações automáticas

---

## 🚀 Como Executar

### Opção 1: Executar com Maven (desenvolvimento)

```bash
# Compile e execute com Maven
mvn clean spring-boot:run
```

### Opção 2: Compilar e gerar JAR

```bash
# Compile e crie o JAR executável
mvn clean package

# Execute o JAR
java -jar target/sysMarcial-0.0.1-SNAPSHOT.jar
```

### Verificar se a aplicação está rodando

A aplicação estará disponível em:
- **URL Base**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

---

## 📡 API Endpoints

### 👥 Alunos

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `POST` | `/alunos` | Cadastrar novo aluno | 201 |
| `GET` | `/alunos` | Listar alunos (com paginação e filtros) | 200 |
| `GET` | `/alunos/{id}` | Buscar aluno por ID | 200 |
| `PUT` | `/alunos/{id}` | Atualizar aluno | 200 |
| `DELETE` | `/alunos/{id}` | Deletar aluno | 204 |

### 📈 Relatórios

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/relatorios/faturamento-mensal` | Faturamento mensal | 200 |
| `GET` | `/relatorios/alunos-por-cidade` | Distribuição de alunos por cidade | 200 |
| `GET` | `/relatorios/faturas-em-aberto` | Faturas pendentes de pagamento | 200 |

### 📝 Exemplo de Requisição - Cadastrar Aluno

**Request:**
```http
POST /alunos HTTP/1.1
Content-Type: application/json
Host: localhost:8080

{
  "nome": "João Silva",
  "dataNascimento": "1995-05-15",
  "sexo": "M",
  "telefone": "1133334444",
  "celular": "11999999999",
  "email": "joao@example.com",
  "observacao": "Aluno iniciante",
  "endereco": "Rua Principal",
  "numero": "123",
  "complemento": "Apartamento 202",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01310100"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "dataNascimento": "1995-05-15",
  "sexo": "M",
  "telefone": "1133334444",
  "celular": "11999999999",
  "email": "joao@example.com",
  "observacao": "Aluno iniciante",
  "endereco": "Rua Principal",
  "numero": "123",
  "complemento": "Apartamento 202",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01310100",
  "criadoEm": "2025-06-07T12:30:45",
  "atualizadoEm": null
}
```

### 📝 Exemplo de Requisição - Listar Alunos com Paginação

```http
GET /alunos?page=0&size=10&sort=nome,asc HTTP/1.1
Host: localhost:8080
```

**Parâmetros de Query:**
- `page`: Número da página (começa em 0)
- `size`: Quantidade de registros por página
- `sort`: Campo para ordenação (padrão: id)

### 📝 Exemplo de Requisição - Listar Alunos com Filtros

```http
GET /alunos?nome=João&cidade=São Paulo&page=0&size=10 HTTP/1.1
Host: localhost:8080
```

**Parâmetros de Filtro:**
- `nome`: Filtrar por nome (parcial)
- `email`: Filtrar por email
- `celular`: Filtrar por celular
- `cidade`: Filtrar por cidade
- `estado`: Filtrar por estado

---

## 🗄️ Migrações de Banco de Dados

O projeto utiliza **Flyway** para controle de versão do banco de dados. As migrações são scripts SQL que versionam o schema do banco.

### 📂 Localização das Migrações

```
src/main/resources/db/migration/
```

### 🔄 Comportamento das Migrações

- As migrações são executadas **automaticamente** ao iniciar a aplicação
- Cada migração tem um número de versão (ex: `V1__init.sql`)
- Migrações anteriormente executadas não são repetidas
- Flyway mantém histórico em uma tabela `flyway_schema_history`

### 📝 Exemplo de Migração

```sql
-- V1__create_alunos_table.sql
CREATE TABLE alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE,
    sexo CHAR(1),
    telefone VARCHAR(30),
    celular VARCHAR(30),
    email VARCHAR(140) UNIQUE,
    observacao TEXT,
    endereco VARCHAR(150),
    numero VARCHAR(5),
    complemento VARCHAR(150),
    bairro VARCHAR(130),
    cidade VARCHAR(130),
    estado VARCHAR(2),
    cep VARCHAR(20),
    criado_em TIMESTAMP,
    atualizado_em TIMESTAMP
);
```

---

## 📚 Documentação da API

### Acessar Swagger UI

Após iniciar a aplicação, acesse a documentação interativa em:

```
http://localhost:8080/swagger-ui.html
```

### Recursos Disponíveis

- **Títlo**: Academia API
- **Versão**: v1.0.0
- **Descrição**: API para gerenciamento de academia com cadastro de alunos, matrículas, controle financeiro e relatórios

### Contato

- **Nome**: Rafael Dos Santos
- **Email**: rafaeldosantos__@outlook.com

---

## 🛡️ Tratamento de Erros

A aplicação implementa um tratamento global de exceções. Todos os erros retornam no seguinte formato:

### Erro de Validação (400 Bad Request)

```json
{
  "timestamp": "2025-06-07T12:30:45.123",
  "status": 400,
  "message": "Erro de validação.",
  "errors": [
    "nome: O Nome é obrigatório",
    "email: Email inválido."
  ]
}
```

### Erro de Regra de Negócio (400 Bad Request)

```json
{
  "timestamp": "2025-06-07T12:30:45.123",
  "status": 400,
  "message": "Erro de regra de negócio",
  "errors": [
    "Já existe um aluno com esse email"
  ]
}
```

### Erro de Recurso não Encontrado (404 Not Found)

```json
{
  "timestamp": "2025-06-07T12:30:45.123",
  "status": 404,
  "message": "Recurso não encontrado",
  "errors": [
    "Esse aluno não existe"
  ]
}
```

---

## 📈 Próximas Atualizações

Este projeto está em constante desenvolvimento. Algumas melhorias planejadas incluem:

- [ ] Interface web (Frontend com React/Angular)
- [ ] Autenticação e autorização (JWT/OAuth2)
- [ ] Relatórios avançados (PDF, Excel)
- [ ] Notificações de vencimento de mensalidade (Email/SMS)
- [ ] Dashboard de estatísticas em tempo real
- [ ] Integração com sistemas de pagamento (Stripe, PayPal)
- [ ] Backup automático do banco de dados
- [ ] Endpoints completos para todas as entidades
- [ ] Testes unitários e de integração
- [ ] Docker e Docker Compose para deploy
- [ ] CI/CD com GitHub Actions
- [ ] Cache com Redis
- [ ] WebSockets para atualizações em tempo real

---

## 🤝 Contribuições

Este é um projeto em desenvolvimento e sugestões de melhorias são bem-vindas! 

Se você deseja contribuir:

1. Faça um **fork** do repositório
2. Crie uma **branch** para sua feature (`git checkout -b feature/MinhaFeature`)
3. **Commit** suas mudanças (`git commit -m 'Adiciona minha feature'`)
4. **Push** para a branch (`git push origin feature/MinhaFeature`)
5. Abra um **Pull Request**

### Diretrizes de Contribuição:
- Sempre crie testes para novas funcionalidades
- Siga as convenções de código existentes
- Mantenha o padrão de nomes em português
- Atualize a documentação conforme necessário

---

## 👤 Autor

**Rafael Santos**
- GitHub: [@rafasantosdevv](https://github.com/rafasantosdevv)
- Email: rafaeldosantos__@outlook.com
- LinkedIn: [Rafael Santos](#)

---

## 📝 Licença

Este projeto ainda não possui uma licença definida. Verifique as configurações do repositório para mais detalhes.

Para mais informações sobre as licenças disponíveis, consulte [Choose a License](https://choosealicense.com/).

---

## 📞 Suporte

Se encontrar algum problema ou tiver dúvidas:

1. Verifique as [Issues](https://github.com/rafasantosdevv/sysMarcial/issues) existentes
2. Consulte a documentação Swagger: `http://localhost:8080/swagger-ui.html`
3. Abra uma nova [Issue](https://github.com/rafasantosdevv/sysMarcial/issues/new)

---

## 🎓 Sobre o Projeto

Este projeto foi desenvolvido como uma aplicação de aprendizado próprio com foco em:
- Arquitetura clean code
- Boas práticas de desenvolvimento Java
- Design patterns e princípios SOLID
- Desenvolvimento RESTful com Spring Boot
- Persistência de dados com JPA/Hibernate
- Validação e tratamento de exceções

---

**Última atualização**: Junho de 2025

Desenvolvido com ❤️ por [Rafael Santos](https://github.com/rafasantosdevv)
