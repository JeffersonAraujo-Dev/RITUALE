# Rituale

Rituale é uma loja de fragrâncias com foco em catálogo premium, autenticação, favoritos, carrinho e fechamento via WhatsApp. O projeto usa Java 21 + Spring Boot no backend e HTML/CSS/JS no frontend estático para permitir uma entrega funcional rápida e clara.

## Stack

- Backend: Java 21, Spring Boot 3, Spring Security, JWT, JPA, Flyway, PostgreSQL
- Frontend: HTML, CSS e JavaScript
- Infra local: Docker Compose
- Checkout: WhatsApp como canal de fechamento do pedido

## Features implementadas

- Autenticação e registro com JWT
- Usuários e perfis
- Catálogo público de produtos
- CRUD administrativo de produtos
- Estoque e controle de itens
- Favoritos por usuário
- Carrinho por usuário
- Pedidos e mensagem formatada para WhatsApp
- Health check e configuração por ambiente

## Estrutura do projeto

```text
rituale/
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/rituale/
│   │   │   │   ├── auth/
│   │   │   │   ├── cart/
│   │   │   │   ├── category/
│   │   │   │   ├── config/
│   │   │   │   ├── favorite/
│   │   │   │   ├── order/
│   │   │   │   ├── product/
│   │   │   │   ├── security/
│   │   │   │   └── user/
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── application-dev.yml
│   │   │       ├── application-prod.yml
│   │   │       ├── application-test.yml
│   │   │       └── db/migration/
│   │   └── test/
│   └── target/
├── frontend/
│   ├── index.html
│   ├── produtos.html
│   ├── produto.html
│   ├── carrinho.html
│   ├── login.html
│   ├── cadastro.html
│   ├── favoritos.html
│   ├── css/
│   └── js/
├── docker-compose.yml
├── .env.example
├── .gitignore
├── README.md
└── .git
```

## Requisitos

- Java 21
- Maven
- Docker + Docker Compose
- Git

## Executar localmente

### 1) Configurar variáveis de ambiente

```bash
cp .env.example .env
```

### 2) Subir PostgreSQL

```bash
docker compose up -d postgres
```

### 3) Rodar backend

```bash
cd backend
export JAVA_HOME=/caminho/para/o/jdk-21
mvn spring-boot:run
```

A API fica em:

- http://localhost:8080
- health check: http://localhost:8080/actuator/health

### 4) Rodar frontend

```bash
cd frontend
python3 -m http.server 8000
```

A interface fica em:

- http://localhost:8000

## Perfis

- `default`: configuração local padrão
- `dev`: ambiente de desenvolvimento
- `test`: testes automatizados
- `prod`: produção

## Variáveis de ambiente

Arquivo [.env.example](.env.example):

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

## Como publicar no GitHub publicamente

### 1) Verificar o estado do Git

```bash
git status
git branch
```

### 2) Criar o commit inicial

```bash
git add .
git commit -m "feat: initial public release"
```

### 3) Criar o repositório no GitHub como público

No GitHub:

- clique em New repository
- escolha um nome, por exemplo: `rituale`
- marque `Public`
- não inicialize com README, .gitignore ou licença, porque o projeto já tem isso

### 4) Conectar o repositório local ao remoto

```bash
git remote add origin git@github.com:SEU_USUARIO/rituale.git
# ou HTTPS
# git remote add origin https://github.com/SEU_USUARIO/rituale.git

git branch -M main
git push -u origin main
```

## Hospedagem gratuita do backend

As opções mais simples e práticas são:

### Option 1 — Render

- Boa experiência para Spring Boot
- Deploy simples via GitHub
- Backend gratuito para projetos pequenos
- Recomendado para quem quer algo rápido e estável

### Option 2 — Railway

- Muito fácil de conectar com GitHub
- Suporte rápido para Java/Spring Boot
- Bom para projetos pequenos e protótipos

### Option 3 — Fly.io

- Bom custo/benefício
- Mais técnico e um pouco mais complexo de configurar

## Recomendação prática

Para um projeto como este, eu recomendo:

1. Publicar no GitHub como público
2. Conectar o repo ao Render ou Railway
3. Definir variáveis de ambiente no painel do provedor
4. Usar PostgreSQL gerenciado do próprio provedor ou Docker em ambiente muito simples

## Próximos passos sugeridos

- integrar painel administrativo completo
- voltar ao frontend com React/TypeScript
- melhorar o checkout com dados do cliente no pedido
- adicionar dashboard de vendas e estoque
- criar fluxo de perfil do usuário

## Observação

Este projeto foi pensado para funcionar como loja premium sem pagamento online na primeira versão, usando WhatsApp como canal final de atendimento e fechamento do pedido.
