# 🌍 OrbitGuard

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![Swagger](https://img.shields.io/badge/Swagger-API_Docs-success?style=for-the-badge&logo=swagger)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

### Plataforma Inteligente de Monitoramento de Focos de Incêndio

Projeto desenvolvido com foco em monitoramento ambiental utilizando integração com APIs externas, análise de dados climáticos e gerenciamento de focos de incêndio.

</div>

---

# Sobre o Projeto

O **OrbitGuard** é uma API desenvolvida em **Java + Spring Boot** capaz de consumir dados de incêndios em tempo real através da NASA FIRMS e cruzar essas informações com dados climáticos da OpenWeather.

A proposta do projeto é oferecer uma solução moderna para:

- Monitoramento de focos de incêndio
- Análise climática das regiões afetadas
- Identificação geográfica de ocorrências
- Organização inteligente dos dados
- Disponibilização via API REST

---

# Tecnologias Utilizadas

| Tecnologia | Função |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot | Estrutura da API |
| Spring Web MVC | Criação dos endpoints REST |
| Spring Data JPA | Persistência de dados |
| PostgreSQL | Banco de dados |
| Swagger/OpenAPI | Documentação da API |
| Maven | Gerenciamento de dependências |
| Lombok | Redução de boilerplate |
| Jackson | Manipulação de JSON |

---

# 📂 Estrutura do Projeto

```bash
src/main/java/br/com/GS/OrbitGuard
│
├── API/                 # Integrações externas
├── configuration/       # Configurações do projeto
├── controller/          # Endpoints REST
├── exception/           # Tratamento de erros
├── model/               # DTOs e entidades
├── repository/          # Repositórios JPA
├── service/             # Regras de negócio
└── OrbitGuardApplication.java
```

---

# Funcionalidades

Consumo de dados da NASA FIRMS  
Consulta de condições climáticas via OpenWeather  
API REST organizada em camadas  
Documentação automática com Swagger  
Tratamento global de exceções  
Estrutura pronta para escalabilidade  

---

# Endpoints Principais

## Listar todos os focos

```http
GET /focos
```

### Exemplo de resposta

```json
[
  {
    "pais": "Brasil",
    "latitude": -10.12,
    "longitude": -54.21,
    "temperatura": 34.2,
    "risco": "ALTO"
  }
]
```

---

## Buscar focos por país

```http
GET /focos/{pais}
```

### Exemplo

```http
GET /focos/Brasil
```

---

# Swagger

A documentação interativa da API pode ser acessada após iniciar o projeto:

```bash
http://localhost:8080/swagger-ui.html
```

ou

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# Como Executar o Projeto

## Pré-requisitos

- Java 21+
- Maven
- PostgreSQL
- IDE Java (IntelliJ recomendado)

---

## Clonar o repositório

```bash
git clone https://github.com/seu-usuario/OrbitGuard.git
```

```bash
cd OrbitGuard
```

---

## Configurar o banco de dados

No arquivo:

```bash
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/orbitguard
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

---

## Executar aplicação

### Via Maven

```bash
./mvnw spring-boot:run
```

### Ou pela IDE

Execute a classe:

```bash
OrbitGuardApplication.java
```

---

# Integrações Utilizadas

## NASA FIRMS

Responsável pelos dados de focos de incêndio via satélite.

## OpenWeather API

Responsável pelos dados meteorológicos utilizados no cruzamento das informações.

---

# Arquitetura Utilizada

O projeto segue arquitetura em camadas:

```text
Controller → Service → Repository → Database
```

Com separação clara de responsabilidades para facilitar:

- manutenção
- escalabilidade
- testes
- organização

---

# Equipe

| Nome | RM |
|---|---|
| Pietro Vitor Pezzente | 557283 |
| Eric Darakjian | 557082 |
| Luciano Henrique Meriato Júnior | 554546 |
| Kauã Soares Guimarães | 559044 |
| Enzo Mikael Sanches | 558887 |

---

# Evidências de execução

## Listagem de Focos
<img width="447" height="1019" alt="image" src="https://github.com/user-attachments/assets/81b5d88b-9f29-4c0a-b5c4-afa22192ad38" />

## Tratamento de Exceções
<img width="564" height="1020" alt="image" src="https://github.com/user-attachments/assets/8208cfa2-0039-4f78-a71d-b4eebf94a118" />

## Busca por País
<img width="487" height="1018" alt="image" src="https://github.com/user-attachments/assets/90ca628d-40ac-4f28-b4a4-0dfece48126a" />

## Persistência no Banco
<img width="1919" height="905" alt="image" src="https://github.com/user-attachments/assets/fd92b719-33e8-4187-b333-0807e14da396" />






---

<div align="center">

### OrbitGuard — Tecnologia aplic
