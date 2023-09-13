# Bootcamp IBM 2023

Este repositório é dedicado ao Bootcamp IBM 2023, que ocorreu entre os dias 31 de julho a 4 de agosto. Durante este bootcamp, os participantes tiveram a oportunidade de aprender sobre as seguintes tecnologias e tópicos:

### Spring Boot
Durante o Bootcamp, os participantes tiveram a oportunidade de mergulhar no desenvolvimento de aplicativos Java com o framework Spring Boot.
Os tópicos abordados incluíram:
- Configuração inicial de um projeto Spring Boot.
- Criação de uma API REST completa, incluindo as operações CRUD (Criar, Ler, Atualizar e Excluir).
- Implementação de endpoints para manipulação de recursos.
- Criação de testes unitários e de integração.

### Angular
Nós aprendemos a como construir uma interface de usuário interativa em Angular e como integrá-la com a API Spring Boot. Os principais tópicos abordados incluíram:

- Configuração inicial de um projeto Angular.
- Criação de componentes.
- Implementação de serviços Angular para consumir a API RESTful criada com Spring Boot.
- Criação de formulários para interagir com a API, permitindo operações CRUD completas.
- Manipulação de respostas da API para exibir dados atualizados na interface do usuário em tempo real.

## Mini Projeto: To Do List
Como parte do bootcamp, os participantes tiveram a chance de trabalhar em um mini projeto, que consistia na criação de uma aplicação de lista de tarefas (To Do List). Esta aplicação foi desenvolvida utilizando as tecnologias e conceitos aprendidos durante o bootcamp.

### Tecnologias Utilizadas

![Angular](https://img.shields.io/badge/angular-0D1117.svg?style=for-the-badge&logo=angular&logoColor=DD0031&labelColor=0D1117)
![TypeScript](https://img.shields.io/badge/typescript-0D1117.svg?style=for-the-badge&logo=typescript&logoColor=23007ACC&labelColor=0D1117)
![TailwindCSS](https://img.shields.io/badge/tailwindcss-0D1117.svg?style=for-the-badge&logo=tailwind-css&logoColor=2338B2AC&labelColor=0D1117)
![Spring](https://img.shields.io/badge/spring-0D1117.svg?style=for-the-badge&logo=spring&logoColor=236DB33F&labelColor=0D1117)
![Java](https://img.shields.io/badge/Java-0D1117?logo=openjdk&logoColor=ED8B00&style=for-the-badge)


<details>

<summary> <b> Endpoints da API </b> </summary>

### Criar um ToDo:
**Método**: POST </br>
**Endpoint**: /to-do

**Corpo da solicitação (JSON):**
```bash
{
	"title":"Supermercado",
	"description":  "Comprar: arroz, feijão, macarrão",
	"dateEnd":  "2023-08-06"
}
```

**Resposta (JSON):**
```bash
{
	"id":  1,
	"title":  "Supermercado",
	"description":  "Comprar: arroz, feijão, macarrão",
	"dateEnd":  "2023-08-06",
	"completed":  false
}
```

### Obter todos os ToDos:
**Método**: GET </br>
**Endpoint**: /to-do/all

**Resposta (JSON):**
```bash
[
	{
		"id":  1,
		"title":  "Supermercado",
		"description":  "Comprar: arroz, feijão, macarrão",
		"dateEnd":  "2023-08-06",
		"completed":  false
	},
	{
		"id":  2,
		"title":  "Ler um Livro",
		"description":  "Ler um capítulo do livro O Hobbit",
		"dateEnd":  "2023-08-07",
		"completed":  false
	},
	...
]
```

### Atualizar um ToDo:
**Método**: PUT </br>
**Endpoint**: /to-do/{id}/update

**Corpo da solicitação (JSON):**
```bash
{
    "title":  "Ler um Livro",
    "description":  "Ler um capítulo do livro 1984",
    "dateEnd":  "2023-08-07",
    "completed":  false
}
```

**Resposta (JSON):**
```bash
{
    "id": 2,
    "title": "Ler um Livro",
    "description": "Ler um capítulo do livro 1984",
    "dateEnd": "2023-08-07",
    "completed": false
}
```

### Apagar um ToDo:
**Método**: DELETE </br>
**Endpoint**: /to-do/{id}/delete

</details>


<details>

<summary> <b> Screenshots </b> </summary>

### Tela Inicial:

![image 1](https://github.com/Major2571/BootcampIBM/assets/99849455/18f896bd-dd93-4d1b-8767-b62fd927fe62)

### Menu de ações para cada ToDo:
![Group 3](https://github.com/Major2571/BootcampIBM/assets/99849455/1d913a7b-30f0-4608-964d-6f91e86af6f5)

### Edição do item selecionado:
![Group 4](https://github.com/Major2571/BootcampIBM/assets/99849455/6c034386-35e7-443a-8ccc-209f84d571d7)

### Marcar como concluído:
![Group 1](https://github.com/Major2571/BootcampIBM/assets/99849455/70264c6a-8d2b-41e2-abb0-821676f08813)


</details>


## Projeto Final: Casa de Temporada
O projeto final do Bootcamp IBM 2023 consistiu na criação de uma API REST e uma aplicação Angular para gerenciar reservas em uma Casa de Temporada. Você pode encontrar mais informações aqui:

<div>
  <a href="https://github.com/Major2571/CasaDeTemporadaIBM"><img height="130" src="https://denvercoder1-github-readme-stats.vercel.app/api/pin/?username=Major2571&repo=CasaDeTemporadaIBM&show_icons=true&count_private=true&hide_border=true&title_color=FD6767&icon_color=9A1A27&text_color=c9d1d9&bg_color=0d1117" alt="github-readme-streak-stats"></a>
</div>

Este projeto final é um exemplo prático do que os participantes aprenderam durante o Bootcamp IBM 2023, abrangendo tanto o desenvolvimento de uma API com Spring Boot quanto a criação de uma aplicação de frontend interativa com Angular.