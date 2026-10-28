# LabControl

Sistema de controle de equipamentos de laboratório desenvolvido com Java e Spring Boot, com foco em cadastro, consulta, atualização e exclusão de itens patrimoniais. O projeto foi estruturado para ser simples de executar, fácil de entender e adequado para apresentação em portfólio, avaliação técnica ou demonstração de conhecimento em desenvolvimento back-end.

## Visão geral

O LabControl permite registrar equipamentos do laboratório, incluindo nome, número de patrimônio, categoria e quantidade disponível. O sistema valida dados de entrada, evita duplicidade de patrimônio e oferece uma interface simples em HTML/JavaScript para interação direta com a API REST.

## Funcionalidades

- Cadastro de equipamentos
- Listagem de todos os itens cadastrados
- Busca por identificador único
- Atualização de dados de equipamento
- Exclusão de registros
- Validação de campos obrigatórios
- Prevenção de patrimônio duplicado
- API REST pronta para integração
- Front-end leve em página estática
- Suporte ao MySQL via Docker ou instalação local

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL
- Maven
- Docker Compose
- HTML / JavaScript

## Arquitetura do projeto

```text
labcontrol/
├─ src/
│  ├─ main/
│  │  ├─ java/com/senac/labcontrol/
│  │  │  ├─ controller/
│  │  │  ├─ model/
│  │  │  ├─ repository/
│  │  │  ├─ service/
│  │  │  ├─ exception/
│  │  │  └─ LabcontrolApplication.java
│  │  └─ resources/
│  │     ├─ static/
│  │     ├─ application.properties
│  │     └─ application-local.properties.example
│  └─ test/
├─ docker-compose.yml
├─ schema.sql
├─ pom.xml
├─ LEIA-ME.txt
├─ RESPOSTAS.txt
└─ README.md
```

## Estrutura por camada

- Controller: expõe os endpoints REST da aplicação
- Service: contém a lógica de negócio
- Repository: acesso aos dados com JPA / Spring Data
- Model: entidade `Equipamento`
- Exception: tratamento de erros personalizados
- Resources/static: interface web simples para consumir a API

## Requisitos

Antes de iniciar, certifique-se de ter instalado:

- JDK 17 ou superior
- Maven
- Docker (opcional, se usar MySQL em container)
- MySQL (opcional, se usar instalação local)

## Como executar

### Opção 1: com Docker (mais simples)

Na pasta do projeto, execute:

```bash
docker compose up -d
```

Isso inicia um container MySQL pronto para uso com as configurações padrão do projeto.

### Opção 2: com MySQL instalado localmente

1. Crie o banco de dados `labcontrol`.
2. Execute o script SQL disponível em `schema.sql` ou deixe o Spring criar as tabelas automaticamente.
3. Ajuste as credenciais, se necessário, em:

```bash
src/main/resources/application-local.properties
```

ou configure as variáveis de ambiente:

```bash
export DB_URL=jdbc:mysql://localhost:3306/labcontrol?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
export DB_USER=root
export DB_PASSWORD=root
```

## Rodando a aplicação

A partir da pasta do projeto:

```bash
mvn spring-boot:run
```

A aplicação fica disponível em:

- Front-end: http://localhost:8080/
- API: http://localhost:8080/equipamentos

## Endpoints da API

### POST /equipamentos
Cria um novo equipamento.

### GET /equipamentos
Lista todos os equipamentos.

### GET /equipamentos/{id}
Busca um equipamento por ID.

### PUT /equipamentos/{id}
Atualiza os dados de um equipamento existente.

### DELETE /equipamentos/{id}
Remove um equipamento.

## Modelo de dados

A entidade `Equipamento` contém:

- `id`
- `nome`
- `numeroPatrimonio`
- `categoria`
- `quantidade`

Validações aplicadas:

- nome obrigatório, com tamanho mínimo de 3 e máximo de 100 caracteres
- número de patrimônio obrigatório e único
- categoria obrigatória
- quantidade obrigatória e maior ou igual a zero

## Tratamento de erros

O projeto implementa exceções personalizadas para lidar com cenários como:

- equipamento não encontrado
- patrimônio duplicado
- erros de validação

Isso torna a API mais previsível e apropriada para uso em aplicações reais ou demonstrações técnicas.

## Observações

Este projeto foi pensado para ser executado de forma simples e direta, mantendo boa organização de código e uso de padrões de desenvolvimento do Spring Boot. Sua estrutura é adequada para quem deseja demonstrar conhecimentos em:

- Java
- Spring Boot
- REST API
- JPA/Hibernate
- persistência em banco relacional
- organização de projetos
- desenvolvimento orientado a solução de problemas

## Autor

Projeto desenvolvido como parte de avaliação acadêmica / demonstração prática de desenvolvimento web com Java.

## Licença

Este projeto não informa uma licença específica no repositório. Caso queira reutilizar, verifique a política do autor antes de usar em produção ou distribuir publicamente.

## Próximos passos sugeridos

- implementar autenticação e autorização
- criar paginação e filtros na listagem
- adicionar testes unitários e de integração
- melhorar a interface com framework frontend moderno
- publicar em ambiente de produção com banco externo

---

Se quiser, também posso criar uma versão desta README em formato mais "portfólio" com foco em candidatura a emprego, incluindo umResumo executivo, stack tecnológica, desafios e resultados alcançados.
