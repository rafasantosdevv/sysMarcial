# 🥋 SysMarcial - Sistema de Gestão para Academias de Lutas Marciais

> ⚠️ **Status do Projeto**: 🚧 Em Desenvolvimento
> 
> Este projeto ainda está em fase inicial de desenvolvimento e receberá constantes atualizações, melhorias e novas funcionalidades.

## 📋 Sobre o Projeto

**SysMarcial** é um sistema completo de gestão para academias de lutas marciais e fitness. O sistema permite gerenciar desde os dados pessoais dos alunos, passando pela administração de modalidades (Jiu-Jitsu, Muay Thai, Boxe, Musculação, etc.), até o controle financeiro com planos de mensalidade, faturas, assiduidade e geração de relatórios.

## ✨ Funcionalidades Principais

- **👥 Gerenciamento de Alunos**: Cadastro completo com dados pessoais, endereço, telefone e email com validações
- **🥊 Modalidades de Lutas**: Gestão de diferentes artes marciais oferecidas pela academia
- **🏅 Graduações**: Controle de níveis de experiência (Faixa Branca, Azul, Roxa, etc.) por modalidade
- **💳 Planos e Mensalidades**: Definição de planos de pagamento com valores específicos por modalidade
- **📋 Matrículas**: Associação de alunos a modalidades com datas de início, fim e status
- **💰 Faturas**: Geração, controle e acompanhamento de faturas mensais com status (Aberta, Paga, Cancelada, Vencida)
- **📊 Relatórios**: Faturamento mensal, alunos por cidade, e faturas em aberto
- **⏱️ Assiduidade**: Registro de entrada e saída de alunos nas aulas
- **🔍 Filtros Avançados**: Busca de alunos por nome, cidade ou estado com paginação

## 🛠️ Stack Tecnológico

| Componente | Tecnologia | Versão |
|------------|-----------|--------|
| **Linguagem** | Java | 17+ |
| **Framework Web** | Spring Boot | 4.0.6 |
| **ORM** | Spring Data JPA + Hibernate | - |
| **Banco de Dados** | PostgreSQL | 12+ |
| **Migrações** | Flyway | - |
| **Build** | Maven | 3.6+ |
| **Validação** | Jakarta Validation | - |
| **Utility** | Lombok | - |

## 📂 Estrutura do Projeto

```
sysMarcial/
├── src/
│   ├── main/
│   │   ├── java/com/rafasantosdevv/sysMarcial/
│   │   │   ├── controller/          # Controllers REST API
│   │   │   ├── service/             # Lógica de negócio
│   │   │   ├── domain/              # Entidades JPA
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── repository/          # Interfaces de acesso a dados
│   │   │   ├── exception/           # Manipulação de exceções
│   │   │   ├── specification/       # JPA Specifications para filtros
│   │   │   └── projection/          # Projeções para relatórios
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/        # Scripts Flyway (V1, V2, V3...)
│   └── test/
├── pom.xml                          # Configuração Maven
├── alunos.http                      # Requisições HTTP para testes
└── README.md                        # Este arquivo
```

## 🗄️ Modelo de Dados

### Entidades Principais

#### **Aluno** 👤
Armazena informações completas dos alunos da academia:
- **Dados Pessoais**: Nome, Data de Nascimento, Sexo
- **Contato**: Telefone, Celular, Email (com validação)
- **Endereço**: Rua, Número, Complemento, Bairro, Cidade, Estado, CEP
- **Metadados**: Criado em, Atualizado em, Observações

```sql
CREATE TABLE alunos (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  data_nascimento DATE,
  email VARCHAR(150) UNIQUE,
  ...
);
```

#### **Modalidade** 🥊
Define os tipos de lutas/artes marciais oferecidas:
- Nome da modalidade (ex: Jiu-Jitsu, Muay Thai, Boxe, Musculação, Funcional)
- Status ativo/inativo

Exemplos de modalidades incluídas:
- Musculação
- Funcional
- Jiu-Jitsu
- Muay Thai
- Pilates

#### **Graduação** 🏅
Níveis de experiência dentro de uma modalidade:
- Nome da graduação (ex: Faixa Branca, Faixa Azul, Faixa Roxa)
- Associada a uma modalidade específica

Exemplos para Jiu-Jitsu:
- Faixa Branca
- Faixa Azul
- Faixa Roxa

#### **Plano** 💳
Planos de mensalidade oferecidos:
- Nome do plano (ex: Mensal, Trimestral)
- Valor mensal em BigDecimal
- Modalidade associada
- Status ativo/inativo

Exemplos:
- Musculação - Mensal: R$ 120,00
- Musculação - Trimestral: R$ 330,00
- Funcional - Mensal: R$ 150,00
- Jiu-Jitsu - Mensal: R$ 180,00

#### **Matrícula** 📋
Associação entre aluno e academia:
- Data de matrícula
- Dia de vencimento (1-31) para cobrança recorrente
- Data de encerramento (opcional)
- Status: ATIVA, ENCERRADA, CANCELADA

#### **MatriculaModalidade** 🔗
Associação entre matrícula, modalidade, graduação e plano:
- Permite que um aluno se matricule em múltiplas modalidades
- Armazena a graduação atual do aluno
- Vincula ao plano contratado
- Datas de início e fim da modalidade

#### **FaturaMatricula** 💰
Registro de pagamentos mensais:
- Valor da fatura
- Data de vencimento
- Data de pagamento
- Data de cancelamento
- Status: ABERTA, PAGA, CANCELADA, VENCIDA

#### **Assiduidade** ⏱️
Registro de presença em aulas:
- Matrícula do aluno
- Data e hora de entrada (auto-preenchida com CURRENT_TIMESTAMP)
- Data e hora de saída (preenchida manualmente)

## 🌐 API REST - Endpoints

### Alunos

#### Cadastrar novo aluno
```http
POST /alunos
Content-Type: application/json

{
  "nome": "João Silva",
  "dataNascimento": "1995-05-15",
  "sexo": "M",
  "email": "joao@example.com",
  "celular": "11999999999",
  "telefone": "1133334444",
  "endereco": "Rua Principal",
  "numero": "123",
  "complemento": "Apto 45",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01310100",
  "observacao": "Aluno referência"
}
```

#### Listar alunos
```http
GET /alunos?page=0&size=10&sort=nome,asc
GET /alunos?nome=Rafael&page=0&size=10
GET /alunos?cidade=SãoPaulo&page=0&size=10
GET /alunos?estado=SP&page=0&size=10
```

#### Buscar aluno por ID
```http
GET /alunos/1
```

#### Atualizar aluno
```http
PUT /alunos/1
Content-Type: application/json

{
  "nome": "João Silva Updated",
  "email": "novo@email.com",
  ...
}
```

#### Deletar aluno
```http
DELETE /alunos/1
```

### Relatórios

#### Faturamento mensal
```http
GET /relatorios/faturamento-mensal
```
Retorna o faturamento agrupado por mês.

#### Alunos por cidade
```http
GET /relatorios/alunos-por-cidade
```
Retorna a quantidade de alunos agrupada por cidade.

#### Faturas em aberto
```http
GET /relatorios/faturas-em-aberto
```
Retorna todas as faturas com status ABERTA ou VENCIDA.

## 🚀 Como Executar

### Pré-requisitos

- **Java 17 ou superior** ([Download JDK](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** ([Download Maven](https://maven.apache.org/download.cgi))
- **PostgreSQL 12+** ([Download PostgreSQL](https://www.postgresql.org/download/))

### 1. Configurar Banco de Dados

Crie um banco de dados PostgreSQL:

```bash
# Acesse o PostgreSQL
psql -U postgres

# Crie o banco de dados
CREATE DATABASE academia;

# Crie um usuário (opcional)
CREATE USER sysmarcial WITH PASSWORD 'sua_senha';
ALTER ROLE sysmarcial WITH CREATEDB;
```

### 2. Configurar a Aplicação

Atualize as credenciais do banco em `src/main/resources/application.properties`:

```properties
spring.application.name=sysMarcial
spring.datasource.url=jdbc:postgresql://localhost:5432/academia
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

**Explicação das propriedades:**
- `ddl-auto=validate`: Valida o schema sem fazer alterações (Flyway gerencia isso)
- `show-sql=true`: Exibe as SQL executadas (remova em produção)
- Flyway executa as migrações automaticamente na inicialização

### 3. Executar a Aplicação

**Opção 1 - Com Maven**
```bash
# Clone o repositório
git clone https://github.com/rafasantosdevv/sysMarcial.git
cd sysMarcial

# Execute a aplicação
mvn spring-boot:run
```

**Opção 2 - Build e execução JAR**
```bash
# Compile e empacote
mvn clean package

# Execute o JAR
java -jar target/sysMarcial-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: **http://localhost:8080**

## 📝 Migrações de Banco de Dados

O projeto utiliza **Flyway** para versionamento e controle de migrações do banco de dados. As migrações são executadas automaticamente na inicialização.

### Arquivos de Migração

#### `V1__create_academia_schema.sql`
Cria todas as tabelas principais:
- alunos
- modalidades
- graduacoes
- planos
- matriculas
- matriculas_modalidades
- faturas_matriculas
- assiduidade

#### `V2__insert_dados_iniciais.sql`
Insere dados iniciais:
- 5 Modalidades (Musculação, Funcional, Jiu-Jitsu, Muay Thai, Pilates)
- Planos padrão para cada modalidade
- Graduações para Jiu-Jitsu

#### `V3__inserir_dados_financeiro.sql`
Insere dados de teste para demonstração:
- 2 Matrículas de alunos
- MatrículaModalidade associadas
- Faturas mensais com diferentes status

### Adicionar Novas Migrações

Para criar uma nova migração:

1. Crie um arquivo em `src/main/resources/db/migration/`
2. Nomeie como `V<numero>__<descricao>.sql` (ex: `V4__add_column_ativo.sql`)
3. Escreva o SQL desejado
4. Na próxima execução, Flyway executará automaticamente

```sql
-- V4__add_column_ativo.sql
ALTER TABLE planos ADD COLUMN descricao VARCHAR(500);
```

## 🧪 Testando a API

### Usando o arquivo HTTP incluído

O projeto inclui um arquivo `alunos.http` com exemplos de requisições. Use uma extensão HTTP Client (como REST Client do VS Code):

```http
@baseUrl = http://localhost:8080

### Cadastrando aluno
POST {{baseUrl}}/alunos
Content-Type: application/json

{
  "nome":"João Silva",
  "dataNascimento":"1995-05-15",
  "sexo": "M",
  "email": "joao@example.com",
  "celular": "11999999999"
}

### Listando alunos com paginação
GET {{baseUrl}}/alunos?page=0&size=10&sort=nome,asc

### Buscando por ID
GET {{baseUrl}}/alunos/1

### Faturamento mensal
GET {{baseUrl}}/relatorios/faturamento-mensal
```

### Usando cURL

```bash
# Cadastrar aluno
curl -X POST http://localhost:8080/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"João Silva",
    "dataNascimento":"1995-05-15",
    "email":"joao@example.com"
  }'

# Listar alunos
curl http://localhost:8080/alunos?page=0&size=10

# Relatório de faturamento
curl http://localhost:8080/relatorios/faturamento-mensal
```

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas:

```
┌─────────────────────────────────────────┐
│         REST Controllers                 │
│    (AlunoController, Relatórios)        │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         Services (Lógica)                │
│       (AlunoService, etc)               │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│      Repository (JPA/Hibernate)         │
│     (AlunoRepository, etc)              │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│      PostgreSQL Database                 │
└─────────────────────────────────────────┘
```

### Padrões Utilizados

- **MVC**: Model-View-Controller
- **DTO**: Data Transfer Objects para requisições/respostas
- **JPA Specification**: Para consultas dinâmicas com filtros
- **Exceptions**: GlobalExceptionHandler para tratamento centralizado
- **Projections**: Para otimizar queries de relatórios
- **Validação**: Bean Validation com Jakarta Validation

## 🛡️ Tratamento de Exceções

A aplicação possui tratamento centralizado de exceções através de `GlobalExceptionHandler`:

- **RegraNegocioException**: Para violações de regra de negócio
- **MethodArgumentNotValidException**: Para erros de validação
- **Respostas estruturadas** com timestamp, código HTTP e mensagens

## 📚 Validações Implementadas

- Email deve ser único e válido
- Nome obrigatório (até 150 caracteres)
- Data de nascimento deve estar no passado
- Sexo deve ser M ou F
- CEP, telefone e celular com validação de tamanho
- Valores de planos devem ser >= 0
- Dia de vencimento entre 1 e 31

## 🔄 Fluxo de Uso Típico

1. **Cadastrar Aluno**: Criar novo aluno com dados pessoais
2. **Criar Matrícula**: Associar aluno à academia
3. **Adicionar Modalidades**: Vincular aluno a modalidades específicas
4. **Gerar Faturas**: Sistema gera faturas mensais automaticamente
5. **Registrar Assiduidade**: Controlar entrada/saída nas aulas
6. **Gerar Relatórios**: Acompanhar faturamento e receita

## 📈 Próximas Atualizações Planejadas

- [ ] **Interface Web/Frontend**: Dashboard com Angular/React
- [ ] **Autenticação e Autorização**: JWT e Spring Security
- [ ] **Mais Relatórios**: Frequência de alunos, receita por modalidade
- [ ] **Notificações**: Email de vencimento de mensalidade
- [ ] **Bulk Operations**: Gerar múltiplas faturas em lote
- [ ] **Integração com Pagamento**: Stripe, PagSeguro, etc.
- [ ] **Mobile App**: App nativa para controle de assiduidade
- [ ] **Backup Automático**: Agendamento de backups
- [ ] **Histórico de Alterações**: Auditoria de dados
- [ ] **API mais completa**: Endpoints para todas as entidades

## 🐛 Tratamento de Erros

### Exemplos de Respostas de Erro

**Email duplicado (400 Bad Request):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "titulo": "Erro de regra de negócio",
  "mensagens": ["Já existe um aluno com esse email"]
}
```

**Validação falha (400 Bad Request):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "titulo": "Erro de validação.",
  "mensagens": [
    "email: Email inválido.",
    "nome: O Nome é obrigatório"
  ]
}
```

**Aluno não encontrado (404 Not Found):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "titulo": "Erro de regra de negócio",
  "mensagens": ["Esse aluno não existe"]
}
```

## 📞 Suporte e Contribuições

Este é um projeto em desenvolvimento ativo. 

### Reportar Problemas
Para reportar bugs ou problemas, abra uma [Issue no GitHub](https://github.com/rafasantosdevv/sysMarcial/issues)

### Sugerir Melhorias
Tem uma ideia? Abra uma [Discussion](https://github.com/rafasantosdevv/sysMarcial/discussions)

### Contribuir
Pull requests são bem-vindas! 

## 👤 Autor

**Rafael Santos**
- GitHub: [@rafasantosdevv](https://github.com/rafasantosdevv)
- Email: rafaeldosantos__@outlook.com

## 📝 Licença

Este projeto ainda não possui uma licença definida. Verifique o repositório para mais detalhes.

---

## 📌 Notas Importantes

- ⚠️ O projeto está em desenvolvimento inicial
- 🔐 Dados sensíveis (banco de dados, credenciais) devem ser protegidos
- 🗂️ Flyway gerencia o schema do banco - não edite tabelas manualmente
- 📊 Use relatórios para análises, não queries diretas no banco em produção
- 🔄 Sempre teste novas funcionalidades antes de fazer deploy

---

**Última atualização**: Junho de 2026  
**Versão**: 0.0.1-SNAPSHOT
