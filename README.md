# 🥋 SysMarcial - Sistema de Gerenciamento de Academia de Lutas Marciais

> ⚠️ **Status do Projeto**: Em Desenvolvimento
> 
> Este projeto ainda está em fase de desenvolvimento e receberá constantes atualizações e melhorias.

## 📋 Descrição

**SysMarcial** é um sistema completo de gestão para academias de lutas marciais. O sistema permite controlar e gerenciar desde os dados dos alunos até as modalidades de lutas oferecidas, graduações, planos de mensalidade e registros de assiduidade.

## 🎯 Funcionalidades Principais

- **Gerenciamento de Alunos**: Cadastro completo com dados pessoais, endereço e contato
- **Modalidades de Lutas**: Gestão das lutas/artes marciais oferecidas (Jiu-Jitsu, Muay Thai, Boxe, etc.)
- **Graduações**: Controle de níveis de experiência/faixa por modalidade
- **Planos e Mensalidades**: Definição de planos de pagamento com valores específicos por modalidade
- **Matrículas**: Associação de alunos a modalidades com datas de início e fim
- **Faturas**: Geração e controle de faturas de mensalidade com status (Aberta, Paga, Cancelada)
- **Assiduidade**: Registro de entrada e saída de alunos nas aulas

## 🛠️ Stack Tecnológico

| Tecnologia | Descrição |
|------------|-----------|
| **Java** | Linguagem principal (Java 17) |
| **Spring Boot 4.0.6** | Framework web e aplicação |
| **Spring Data JPA** | Persistência de dados e ORM |
| **PostgreSQL** | Banco de dados relacional |
| **Flyway** | Controle de versão do banco de dados |
| **Maven** | Gerenciador de dependências |
| **Lombok** | Redução de boilerplate em Java |

## 📊 Arquitetura e Estrutura

O projeto segue a arquitetura **MVC (Model-View-Controller)** com divisão clara de responsabilidades:

### Estrutura de Diretórios

```
src/
├── main/
│   ├── java/com/rafasantosdevv/sysMarcial/
│   │   ├── controller/      # Controllers REST API
│   │   ├── service/         # Lógica de negócio
│   │   ├── domain/          # Entidades JPA
│   │   ├── dto/             # Data Transfer Objects
│   │   └── repository/      # Interfaces de acesso a dados
│   └── resources/
│       ├── application.properties  # Configurações
│       └── db/migration/          # Scripts Flyway
└── test/
    └── java/                # Testes unitários
```

## 📦 Entidades Principais

### **Aluno**
Representa os alunos da academia com informações completas:
- Dados pessoais: nome, data de nascimento, sexo
- Contato: telefone, celular, email
- Endereço completo: rua, número, bairro, cidade, estado, CEP
- Observações e datas de criação/atualização

### **Modalidade**
Define os tipos de lutas/artes marciais oferecidas:
- Nome da modalidade (ex: Jiu-Jitsu, Muay Thai)
- Status ativo/inativo

### **Graduação**
Nivéis de experiência dentro de uma modalidade:
- Nome da graduação (ex: Faixa Branca, Faixa Roxa)
- Associada a uma modalidade específica

### **Plano**
Planos de mensalidade oferecidos:
- Nome do plano
- Valor mensal
- Associado a uma modalidade
- Status ativo/inativo

### **Matricula**
Associação entre aluno e academia:
- Data de matrícula
- Dia de vencimento da mensalidade
- Data de encerramento (opcional)
- Status (Ativa, Encerrada)

### **MatriculaModalidade**
Associação entre matrícula e modalidade/plano:
- Aluno → Modalidade específica
- Graduação atual do aluno
- Plano contratado
- Datas de início e fim

### **FaturaMatricula**
Registro de pagamentos mensais:
- Valor da fatura
- Data de vencimento
- Data de pagamento
- Status (Aberta, Paga, Cancelada)

### **Assiduidade**
Registro de presença em aulas:
- Matrícula do aluno
- Data/hora de entrada e saída

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- PostgreSQL 12+

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE academia;
```

2. Atualize as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/academia
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Executar a Aplicação

```bash
# Clone o repositório
git clone https://github.com/rafasantosdevv/sysMarcial.git
cd sysMarcial

# Compile e rode com Maven
mvn spring-boot:run

# Ou compile e execute o JAR
mvn clean package
java -jar target/sysMarcial-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 API Endpoints

### Alunos
- `POST /alunos` - Cadastrar novo aluno
- `GET /alunos` - Listar alunos (com paginação)
- `GET /alunos/{id}` - Buscar aluno por ID
- `PUT /alunos/{id}` - Atualizar aluno
- `DELETE /alunos/{id}` - Deletar aluno

### Exemplo de Requisição

```http
POST /alunos HTTP/1.1
Content-Type: application/json

{
  "nome": "João Silva",
  "dataNascimento": "1995-05-15",
  "sexo": "M",
  "email": "joao@example.com",
  "celular": "11999999999",
  "endereco": "Rua Principal",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01310100"
}
```

## 🗄️ Migrações de Banco de Dados

O projeto utiliza **Flyway** para controle de versão do banco de dados. As migrações estão localizadas em:

```
src/main/resources/db/migration/
```

As migrações são executadas automaticamente ao iniciar a aplicação.

## 📈 Próximas Atualizações

Este projeto está em constante desenvolvimento. Algumas melhorias planejadas incluem:

- [ ] Interface web (Frontend)
- [ ] Autenticação e autorização
- [ ] Relatórios de receita e frequência
- [ ] Notificações de vencimento de mensalidade
- [ ] Dashboard de estatísticas
- [ ] Integração com sistemas de pagamento
- [ ] Backup automático do banco de dados
- [ ] API mais completa com todos os endpoints

## 👤 Autor

**Rafael Santos** - [@rafasantosdevv](https://github.com/rafasantosdevv)

## 📝 Licença

Este projeto ainda não possui uma licença definida. Verifique as configurações do repositório para mais detalhes.

## 🤝 Contribuições

Este é um projeto em desenvolvimento. Sugestões e contribuições são bem-vindas! Sinta-se à vontade para:

- Reportar bugs
- Sugerir melhorias
- Fazer pull requests

## 📞 Suporte

Para dúvidas ou problemas, abra uma **Issue** no repositório.

---

**Última atualização**: Junho de 2026
