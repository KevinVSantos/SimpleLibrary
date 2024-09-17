# SimpleLibrary
Cadastro para uma biblioteca simples.

## Arquitetura de Solução e Arquitetura Técnica
A solução possui uma aplicação java que se conecta a um basnco de dados relacional
para consumo de três tabelas que repensentam uma biblioteca.
Visto que os dados dos livros são pouco mutáveis, a aplicação conta com cache em seus end-points,
melhorando o tempo de resposta das chamadas.

Tecnologias utilizadas:
* Java
* Spring boot
* JPA
* H2 Database
* Spring Cache


##  Explicação sobre o Case Desenvolvido (Plano de Implementação)
O projeto conta com três entidades principais:
* Gênero, sendo representaddo pela classe `Genre`;
* Subgênero, sendo representado pela classe `SubGenre`;
* Livro, sendo representado pela classe `Book`;

Cada uma dessas entidades foi validada utilizando o `Bean Validation` do Spring Boot.
Dessa forma, existem várias regras que devem ser seguidas para garantir a inclusão correta dos dados.
Para consultar essas regras, navegue até o diretório `src/main/java/br/com/KevinVSantos/SimpleLibrary/domain/entity`
e explore as respectivas entidades.

O descumprimento de qualquer regra resultará em um erro que será tratado pela classe `CustomExceptionHandler`.
Além disso, essa classe também gerencia exceções inesperadas e erros no envio de parâmetros para os end-points.

### Base de dados
O projeto utiliza o H2, um banco de dados em memória. Esse banco foi escolhido pela facilidade de
implementação, tornando o desenvolvimento do protótipo mais rápido e prático.

Os dados utilizado para popular a base de dados foram obtidos através da seguinte URL: https://www.kaggle.com/datasets/chhavidhankhar11/amazon-books-dataset/

Com os dados em mão, algumas tratativas foram realizadas com intúito preservar características
estabelecidas na idealização do projeto. Tais caracteristicas serão citadas futuramente.
É possível encontrar uma versão em csv dos dados obtivos e brevemente refinados no caminho:
`src/main/resources/Amazon_Books_Scraping.rar`

Para verificar os dados incluidos inicialmente na base, observe o arquivo: `src/main/resources/data.sql`
Sempre que o projeto for reiniciado, os dados irão retornar aos valores inseridos inicialmente.
Se desejar manter as alterações nos registros, deverá ir até o arquivo `src/main/resources/application.properties` e 
modificar as seguintes propriedades:
* `spring.sql.init.mode=never`
* `spring.jpa.hibernate.ddl-auto=update`

### Fluxo
Além dos end-points de consultas de livros, todas as entitades possuem um CRUD básico.
A implementação desse crud genérico pode ser observada no arquivo: `src/main/java/br/com/KevinVSantos/SimpleLibrary/controller/AbstractController.java`

Dito isso, observe os fluxos de criação que podem ser utilizados:

#### Genre
Para iniciar, realize o cadastro de um gênero utilizando o end-point `/genre` com o método `POST`. Certifique-se de seguir
as regras definidas para essa entidade. Um exemplo de payload é o seguinte:
```
{
    "title":"Teste",
    "url":"https://www.github.com/"
}
```
Com o registro criado, é possível consultá-lo através do end-point `/genre` ou `/genre/{title}`
utlizando o método `GET`.

Também é possível realizar a alteração do registro criado pelo end-point `/genre` utilizando
o método `PUT` ou deletá-lo pelo end-point `/genre/{title}` utilizando o método `DELETE`.

#### SubGenre

Com um gênero já cadastrado, é possível criar um subgênero associado a esse gênero principal utilizando
o end-point `/subgenre` com o método `POST`. O processo de criação, edição e deleção de subgênero é semelhante ao cadastro de gênero.
Um exemplo de payload para a criação de um subgênero é o seguinte:
```
{
    "title":"Teste",
    "mainGenreTitle": "Teste",
    "url":"https://www.github.com/"
}
```
Com o registro criado, é possível consultá-lo através do end-point `/subgenre` ou `/subgenre/{title}`
utlizando o método `GET`.

Para deletar uma conta, utilize o end-point `/subgenre/{title}` com o método `DELETE`.

#### Book

###### Transferência de Saldo entre Contas
Com gênero e subgênero criado, é possível realizar a criação de um livro. Para isso, 
utilize o end-point `/books` com o método `POST`. Um exemplo de payload é o seguinte:
```
{
    "title":"Livro 2",
    "author":"Lari",
    "mainGenreTitle":"Teste",
    "subGenreTitle":"Teste",
    "type":"Type Teste",
    "price":"10",
    "rate":"7.2222",
    "url":"https://www.google.com/"
}
```

###### Consulta de livros
Com o registro criado, é possível consultá-lo através do end-point `/books` ou `/books/{title}`
utlizando o método `GET`.
Além disso, existem os seguintes end-points de consulta utilizando o metodo `GET`:  
* `/books/genre/{title}`  - Consulta por Gênero
* `/books/subgenre/{title}` - Consulta por Subgênero
* `/books/author/{title}` - Consulta por Autor

Ademais, para deletar um livro, utilize o end-point `/books/{title}` com o método `DELETE`.

### Cache
A aplicação do cache foi feita a utilizando o proprio String Cache. A simplicidade de configuração aliada
a baixa complexidade do projeto não exigia que nenhum cache mais robusto fosse aplicado.

Dado a simplicidade, todos os end-points foram cacheados. O cache foi implementado principalmente no arquivo: `src/main/java/br/com/KevinVSantos/SimpleLibrary/controller/AbstractController.java`
Como esse arquivo é genérico, foi necessário criar uma configuração `CacheResolver` para gerenciar corretamente
as keys de armazenamento.

## Melhorias e Considerações Finais
Como é possível imaginar, a primeira coisa que gostaria de alterar no projeto é a ausência de testes unitários. 
Aplicando o design pattern TDD isso seria resolvido,
visto que a criação dos testes seria a primeira etapa para o desenvolvimento. Contudo, pela agilidade
necessária e a proposta simples do projeto, os testes ficaram de fora do escopo inicial.

Uma melhoria que importante seria aplicação de paginação nos end-points de listas. Dado que a massa
de dados utilizada é grande, uma paginação facilitaria a utilização para os usuários.

Ademais, seria possivel implementar um end-point de visualização recente, e para isso bastaria salvar em uma outra
tabela o ID dos livros sempre que o end-point `/books/{title}` fosse chamado. Selecionar
essa tabela com a ordenação de dados decrescente nos resultaria em uma lista dos últimos livros vistos.

Por fim, acredito que em um cenário real, seria importante validar os end-points de deleção
visando evitar a exclusão de vários livros de uma só vez. Atualmente, ao deletar um gênero
todos os subgêneros e livros associados a ele serão deletados. Em uma implantação prática esse
comportamento seria muito perigoso e deveria ser evitado.