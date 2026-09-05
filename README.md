# 🌙 RITUALE

### E-commerce de fragrâncias com Java, Spring Boot, PostgreSQL e JavaScript

A **RITUALE** é uma aplicação web de e-commerce desenvolvida para uma loja de fragrâncias, com foco em uma experiência de compra moderna, catálogo organizado e integração entre frontend e backend.

O projeto possui uma arquitetura separada entre **frontend** e **backend**, utilizando uma API REST desenvolvida em Java/Spring Boot, banco de dados PostgreSQL e autenticação baseada em JWT.

O fechamento do pedido é realizado através do **WhatsApp**, permitindo que o cliente monte seu carrinho e encaminhe o pedido diretamente para atendimento.

---

## ✨ Demonstração

> Projeto em desenvolvimento e estruturado para execução local.

**Repositório:**

[github.com/JeffersonAraujo-Dev/RITUALE](https://github.com/JeffersonAraujo-Dev/RITUALE)

---

# 🛍️ Sobre a RITUALE

A RITUALE foi projetada como uma loja virtual de fragrâncias, oferecendo ao cliente um fluxo simples:

```text
Página inicial
      ↓
Catálogo
      ↓
Escolha da fragrância
      ↓
Página do produto
      ↓
Adicionar ao carrinho
      ↓
Revisar pedido
      ↓
Finalizar pelo WhatsApp
```

A aplicação também possui recursos de **autenticação, favoritos, carrinho por usuário, pedidos, controle de estoque e área administrativa**.

---

# 🚀 Funcionalidades

## 👤 Usuário

* Cadastro de usuário
* Login
* Autenticação utilizando JWT
* Controle de sessão
* Perfis de usuário
* Área de favoritos
* Carrinho associado ao usuário

---

## 🧴 Catálogo

O sistema possui um catálogo público de produtos.

Cada produto pode possuir informações como:

* Nome
* Slug
* Descrição
* Preço
* Estoque
* Categoria
* Status de disponibilidade

O catálogo é organizado através de categorias de produtos.

---

## 🔎 Produtos

A aplicação possui uma página específica para visualização de cada produto.

O cliente pode:

* Visualizar informações da fragrância
* Consultar preço
* Ver disponibilidade em estoque
* Adicionar o produto ao carrinho

---

## ❤️ Favoritos

Usuários autenticados podem adicionar produtos aos favoritos.

O sistema mantém os favoritos associados à conta do usuário.

---

## 🛒 Carrinho

O carrinho permite:

* Adicionar produtos
* Controlar quantidade
* Remover produtos
* Consultar itens selecionados
* Trabalhar com carrinho associado ao usuário

---

## 📦 Pedidos

A aplicação possui estrutura de pedidos no backend.

O pedido pode reunir os produtos selecionados pelo cliente e gerar uma mensagem estruturada para encaminhamento ao WhatsApp.

---

## 💬 WhatsApp

A RITUALE utiliza o WhatsApp como canal final de atendimento.

Em vez de utilizar um gateway de pagamento online nesta versão, o sistema prepara as informações do pedido para que o cliente possa continuar o atendimento através do WhatsApp.

### Fluxo:

```text
Cliente
   ↓
Seleciona produtos
   ↓
Carrinho
   ↓
Pedido
   ↓
Mensagem estruturada
   ↓
WhatsApp
```

---

## 📊 Estoque

O backend possui controle de estoque dos produtos.

Isso permite trabalhar com:

* Quantidade disponível
* Verificação de disponibilidade
* Controle de itens
* Integração com pedidos

---

# 🔐 Segurança

A aplicação possui uma camada de segurança baseada em:

* Spring Security
* JWT
* Autenticação de usuários
* Autorização de acesso
* Separação entre recursos públicos e protegidos

A estrutura de segurança está localizada no módulo:

```text
backend/src/main/java/com/rituale/security/
```

---

# ⚙️ Backend

O backend foi desenvolvido utilizando **Java 21** e **Spring Boot**.

A aplicação segue uma organização modular para facilitar manutenção e evolução.

### Principais módulos:

```text
com.rituale
│
├── auth
├── cart
├── category
├── config
├── favorite
├── health
├── order
├── product
├── security
└── user
```

Essa divisão separa as responsabilidades de cada parte do sistema.

---

# 🧰 Tecnologias

## Backend

| Tecnologia      | Utilização                     |
| --------------- | ------------------------------ |
| Java 21         | Linguagem principal            |
| Spring Boot     | Framework da aplicação         |
| Spring Security | Segurança e autenticação       |
| JWT             | Autenticação baseada em tokens |
| Spring Data JPA | Persistência de dados          |
| Hibernate       | ORM                            |
| Flyway          | Migrações do banco             |
| PostgreSQL      | Banco de dados                 |
| Maven           | Gerenciamento e build          |

---

## Frontend

| Tecnologia | Utilização                          |
| ---------- | ----------------------------------- |
| HTML5      | Estrutura                           |
| CSS3       | Estilização                         |
| JavaScript | Interatividade e integração com API |

O frontend é mantido separado do backend e possui páginas independentes para as principais áreas da loja.

---

## Infraestrutura

* Docker
* Docker Compose
* PostgreSQL
* Variáveis de ambiente
* Profiles do Spring Boot

---

# 📁 Estrutura do projeto

```text
RITUALE/
│
├── backend/
│   ├── .mvn/
│   │   └── wrapper/
│   │
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── rituale/
│   │   │   │           ├── auth/
│   │   │   │           ├── cart/
│   │   │   │           ├── category/
│   │   │   │           ├── config/
│   │   │   │           ├── favorite/
│   │   │   │           ├── health/
│   │   │   │           ├── order/
│   │   │   │           ├── product/
│   │   │   │           ├── security/
│   │   │   │           └── user/
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── db/
│   │   │       │   └── migration/
│   │   │       ├── application.yml
│   │   │       ├── application-dev.yml
│   │   │       ├── application-test.yml
│   │   │       └── application-prod.yml
│   │   │
│   │   └── test/
│   │
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend/
│   ├── admin/
│   ├── css/
│   ├── js/
│   ├── cadastro.html
│   ├── carrinho.html
│   ├── favoritos.html
│   ├── index.html
│   ├── login.html
│   ├── produto.html
│   └── produtos.html
│
├── backup_rituale_20260902_170916/
│   └── frontend/
│
├── .env.example
├── .gitignore
├── docker-compose.yml
└── README.md
```

---

# 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados principal.

As alterações estruturais do banco são controladas através do **Flyway**, permitindo versionamento das migrações.

As migrações ficam em:

```text
backend/src/main/resources/db/migration/
```

---

# 🐳 Docker

O projeto possui configuração para execução do PostgreSQL utilizando Docker Compose.

Para iniciar o banco:

```bash
docker compose up -d postgres
```

Para verificar os containers:

```bash
docker compose ps
```

Para parar os serviços:

```bash
docker compose down
```

---

# 🔧 Configuração

Antes de executar o projeto, crie o arquivo `.env` baseado no arquivo fornecido:

```bash
cp .env.example .env
```

O projeto utiliza variáveis de ambiente para configurações como:

```env
POSTGRES_DB=rituale
POSTGRES_USER=rituale
POSTGRES_PASSWORD=rituale
POSTGRES_PORT=5432

JWT_SECRET=change-this-secret-in-production-minimum-32-characters
JWT_EXPIRATION_MS=86400000

APP_PORT=8080

WHATSAPP_PHONE=5500000000000
```

> ⚠️ Em ambiente de produção, utilize valores seguros e nunca publique credenciais ou chaves secretas no repositório.

---

# ▶️ Executando o projeto

## 1. Pré-requisitos

Antes de começar, instale:

* Java 21
* Maven
* Docker
* Docker Compose
* Git
* Python 3

---

## 2. Clone o projeto

```bash
git clone https://github.com/JeffersonAraujo-Dev/RITUALE.git
```

Entre na pasta:

```bash
cd RITUALE
```

---

## 3. Configure o ambiente

```bash
cp .env.example .env
```

Revise as configurações do arquivo `.env`.

---

## 4. Inicie o PostgreSQL

```bash
docker compose up -d postgres
```

---

## 5. Execute o backend

Entre na pasta:

```bash
cd backend
```

Execute:

```bash
mvn spring-boot:run
```

A API estará disponível, por padrão, em:

```text
http://localhost:8080
```

---

# ❤️ Health Check

A aplicação possui endpoint de verificação de saúde:

```text
http://localhost:8080/actuator/health
```

Esse endpoint pode ser utilizado para verificar se o backend está funcionando corretamente.

---

# 🌐 Executando o frontend

Abra outro terminal.

Na raiz do projeto:

```bash
cd frontend
```

Execute um servidor HTTP local:

```bash
python3 -m http.server 8000
```

Depois abra:

```text
http://localhost:8000
```

---

# 🧪 Perfis de configuração

O backend possui diferentes configurações do Spring Boot:

```text
application.yml
application-dev.yml
application-test.yml
application-prod.yml
```

Os profiles permitem separar configurações de acordo com o ambiente de execução.

---

# 🏗️ Arquitetura

A aplicação utiliza uma arquitetura dividida em frontend, API e banco de dados:

```text
                 ┌───────────────────┐
                 │      Cliente      │
                 │ Browser / Mobile  │
                 └─────────┬─────────┘
                           │
                           ▼
                 ┌───────────────────┐
                 │     Frontend      │
                 │   HTML/CSS/JS     │
                 └─────────┬─────────┘
                           │
                      HTTP / REST
                           │
                           ▼
                 ┌───────────────────┐
                 │    Spring Boot    │
                 │      REST API     │
                 └─────────┬─────────┘
                           │
             ┌─────────────┼─────────────┐
             │             │             │
             ▼             ▼             ▼
        ┌────────┐   ┌──────────┐   ┌──────────┐
        │  JWT   │   │   JPA    │   │  Flyway  │
        │Security│   │Hibernate │   │Migration │
        └────────┘   └────┬─────┘   └──────────┘
                          │
                          ▼
                   ┌─────────────┐
                   │ PostgreSQL  │
                   └─────────────┘
```

---

# 🔄 Fluxo de compra

O fluxo principal da aplicação é:

```text
                  RITUALE
                     │
                     ▼
               ┌───────────┐
               │ Catálogo  │
               └─────┬─────┘
                     │
                     ▼
             ┌───────────────┐
             │   Produto     │
             └───────┬───────┘
                     │
                     ▼
             ┌───────────────┐
             │    Carrinho   │
             └───────┬───────┘
                     │
                     ▼
             ┌───────────────┐
             │    Pedido     │
             └───────┬───────┘
                     │
                     ▼
             ┌───────────────┐
             │   WhatsApp    │
             └───────────────┘
```

---

# 🎯 Objetivos técnicos

O projeto foi estruturado com foco em:

* Separação de responsabilidades
* Código organizado por domínio
* API REST
* Autenticação segura
* Persistência relacional
* Versionamento do banco de dados
* Configuração por ambiente
* Containerização
* Facilidade de manutenção
* Possibilidade de expansão futura

---

# 🔮 Evolução planejada

Algumas funcionalidades podem ser incorporadas em versões futuras:

* Dashboard administrativo mais completo
* Gerenciamento avançado de estoque
* Histórico detalhado de pedidos
* Perfil completo do usuário
* Melhorias no checkout
* Integração com meios de pagamento
* Melhorias de acessibilidade
* Testes automatizados mais abrangentes
* Evolução do frontend
* Monitoramento e observabilidade
* Deploy automatizado

Esses itens representam **possíveis evoluções**, não funcionalidades que devem ser consideradas implementadas na versão atual.

---

# 📌 Status do projeto

**Em desenvolvimento.**

A base atual já contempla uma arquitetura full-stack com:

```text
Java 21
   +
Spring Boot
   +
Spring Security / JWT
   +
JPA / Hibernate
   +
Flyway
   +
PostgreSQL
   +
HTML / CSS / JavaScript
   +
Docker
```

---

# 👨‍💻 Autor

Desenvolvido por **Jefferson Araujo**.

GitHub:

**[JeffersonAraujo-Dev](https://github.com/JeffersonAraujo-Dev)**

Repositório:

**[RITUALE](https://github.com/JeffersonAraujo-Dev/RITUALE)**

---

# 📄 Licença

Este projeto não possui uma licença open source definida no repositório no momento.

Caso o projeto seja disponibilizado para uso, modificação ou distribuição por terceiros, recomenda-se adicionar um arquivo `LICENSE` apropriado.

---

<p align="center">

### 🌙 RITUALE

**Fragrâncias. Identidade. Experiência.**

</p>
