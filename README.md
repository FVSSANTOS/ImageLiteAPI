
#    Image Lite - (Repositório de Imagens)

O sistema Image Lite é uma aplicação web para cadastrar imagens, criando um espaço para guardar suas imagens preferidas.


## Principais Características

- __Upload de imagens__: Realize o upload de suas imagens favoritas.
- __Donwload de imagens__: Realize o download das imagens de sua preferência.
- __Cadastro de Usuário__: Crie uma conta de usuário para ter acesso as funcionalidades da aplicação.

## 🖥️ Tecnologias Usadas 

- __Java__: Linguagem de programação para realizar a implementação do backend.
- __Spring Boot__: Framework Java utilizado para criar a API que mapea as entidades no banco de dados, configura as rotas de acesso e gerencia a autenticação.
- __Next JS__: Framework React que realiza a renderização dos elementos visuias do sistema.
- __Docker__: Gerenciador de containers usado para criar uma instância do PostgresSQL e do pgAdmin.



## Documentação da API

#### Retorna todas as imagens ou as que atendem os filtros

```http
  GET /v1/images
```

| Parâmetro  | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `extension, query` | `String, String` | **Opcional**. Podem ser passados a extenção da imagem, nome ou tag para realizar o filtro |

#### Retorna uma imagem

```http
  GET /v1/images/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `String` | **Obrigatório**. O ID da imagem que você quer |

#### Salva uma imagem

```http
  POST /v1/images
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `file, name, tags`      | `MultipartFile, String, List<String>` | **Obrigatório**. As informações da imagem que você está salvando |



## Deploy Backend

Para fazer o deploy do projeto backend rode

```bash
  🐋 docker compose up --build -d
  ☕ javac ImageliteapiApplication.java
```
Com isso o backend vai estar rodando.
