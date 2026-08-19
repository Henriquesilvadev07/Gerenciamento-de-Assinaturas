# 🚀 RickSubs™ — Sistema de Gerenciamento de Assinaturas Recorrentes

> Aplicação Full Stack robusta desenvolvida para controle financeiro, organização e gestão de serviços de assinatura (Netflix, Spotify, Prime Video, etc.). O projeto resolve o problema do esquecimento de cobranças recorrentes com cálculo automatizado de datas de vencimento, controle de status e painel financeiro.

---

## 🔗 Links Oficiais do Projeto

- 🌐 **Aplicação em Produção (Front-end):** [Acessar RickSubs™](https://gerenciamento-de-assinaturas.onrender.com)
- 📑 **Documentação Interativa da API (Swagger UI):** [Acessar OpenAPI/Swagger](https://gerenciamento-de-assinaturas.onrender.com/swagger-ui.html)

---

## 🛠️ Tech Stack & Ferramentas

### **Backend**
* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3.x
* **Segurança & Autenticação:** Spring Security + JWT (JSON Web Token)
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Validação de Dados:** Jakarta Validation (`@Valid`)
* **Documentação de API:** OpenAPI 3 / Springdoc Swagger UI (`v2.8.5`)

### **Banco de Dados & Cloud Infrastructure**
* **Banco de Dados:** PostgreSQL (Hospedado em nuvem)
* **Plataforma de Deploy:** Render Cloud Platform
* **Gerenciamento de Dependências:** Apache Maven

### **Front-end**
* **Tecnologias:** HTML5, CSS3, JavaScript Vanilla (ES6+)
* **Comunicação:** API Fetch assíncrona com interceptação de cabeçalhos HTTP
* **UX/UI:** Layout responsivo, suporte a Dark/Light Mode e conversão de formato de moeda brasileira (vírgula para ponto).

---

## ✨ Principais Funcionalidades

- 🔐 **Autenticação Stateless:** Sistema completo de cadastro e login de usuários com senhas criptografadas (`BCrypt`) e sessão controlada por **Token JWT**.
- 📊 **Dashboard Financeiro:** Somatório dinâmico do gasto mensal com assinaturas ativas e acompanhamento em tempo real de pendências.
- 💳 **Gestão Completa de Assinaturas (CRUD):** 
  - Cadastro de novas assinaturas associadas unicamente ao usuário autenticado.
  - Edição de valores, nomes e datas de vencimento.
  - Exclusão e alteração de status (Ativa / Cancelada / Pendente).
- 📅 **Lógica Automatizada de Vencimento:** Algoritmo no Service para cálculo do próximo ciclo de cobrança (`proximoVencimento`), tratando variações de dias nos meses (28, 30 e 31 dias).
- 🇧🇷 **Localização Numérica (Front-end):** Tratamento no cliente para permitir entrada de valores com vírgula (padrão `BR`) e conversão transparente para ponto (padrão `Double`/`BigDecimal` da API).

---

## 🧪 Como Testar a API via Swagger UI

A API conta com documentação interativa gerada via **OpenAPI 3 / Swagger**. Você pode testar todas as rotas protegidas diretamente pelo navegador:

1. Acesse o link da documentação: **[Swagger UI](https://gerenciamento-de-assinaturas.onrender.com/swagger-ui.html)**.
2. **Registro/Login:**
   - Expanda o grupo `Auth` e utilize o endpoint `POST /auth/register` para criar uma conta de testes (ou `POST /auth/login` se já possuir cadastro).
   - Ao executar o login, copie o token JWT retornado no corpo da resposta (`response body`).
3. **Autenticação da Sessão no Swagger:**
   - Clique no botão verde **Authorize** no topo da página à direita.
   - Cole o token JWT no campo de texto e clique em **Authorize**.
4. **Testando Endpoints Protegidos:**
   - Agora você pode executar requisições em `GET /assinaturas`, `POST /assinaturas`, `PUT /assinaturas/{id}` e `DELETE /assinaturas/{id}`. O Swagger enviará o token de autorização automaticamente em cada chamada.

---

## 🏗️ Arquitetura e Estrutura do Backend

O projeto foi construído seguindo o padrão arquitetural em camadas para garantia de desacoplamento e facilidade de manutenção:

```text
src/main/java/com/example/Controle_de_Assinaturas/
├── config/           # Configurações globais e OpenAPI/Swagger
├── controller/       # Endpoints REST e manipulação de DTOs
├── dto/              # Data Transfer Objects (Java Records)
├── model/            # Entidades JPA (Usuario, Assinatura) e Enums
├── repository/       # Mapeamento do banco de dados (Spring Data JPA)
├── security/         # Filtros JWT, Criptografia de senhas e políticas de CORS
└── service/          # Regras de negócio e calculadoras de vencimento
