# Projeto de Web Services com SOAP: CURSO e PROFESSOR

Este projeto foi desenvolvido com base nos capítulos 1 a 3 do livro **"SOA Aplicado - Integrando com Web Services e Além"** de Alexandre Saudate, e nas exposições de conteúdo em sala de aula sobre Web Services com SOAP. O objetivo é criar um web service SOAP que implemente funcionalidades de exibição, inserção e autenticação de objetos de um sistema de informação com o tema **CURSO e PROFESSOR**.

## Requisitos do Projeto

Os estudantes deverão seguir as seguintes etapas para o desenvolvimento do projeto:

1. **Escolha do Tema**: 
   O tema escolhido é **CURSO e PROFESSOR**, que simula a relação entre cursos oferecidos e os professores responsáveis.

2. **Análise de Negócio**:
   A análise do tema identifica as classes **Curso** e **Professor**, que se relacionam em um padrão de agregação 1 para n, onde um curso pode ter um ou mais professores associados. 

   - **Classe Curso**:
     - `codigo`: String
     - `nome`: String
     - `duracao`: int
     - `professores`: List<Professor>

   - **Classe Professor**:
     - `id`: int
     - `nome`: String
     - `departamento`: String

3. **Implementação das Classes**:
   Implementar as classes **Curso** e **Professor** em Java, seguindo os padrões apresentados em sala de aula e no livro.

4. **Serviços Implementados**:
   Modificar o código para implementar um web service usando SOAP com as seguintes funcionalidades:

   - **`obterTodosCursos`**: 
     Exibe todos os cursos cadastrados, incluindo os dados dos professores associados.

   - **`obterCursosPorDuracao`**: 
     Exibe um subconjunto de cursos com base na duração especificada como parâmetro.

   - **`adicionarCurso`**: 
     Permite a inserção de novos cursos e professores, com autenticação de usuário e senha.

   - **`autenticar(String usuario, String senha)`**:
     Serviço de autenticação que valida o acesso para operações sensíveis como a adição de cursos.

5. **Testes**:
   O web service desenvolvido será testado utilizando a ferramenta SOAPUI.

## Regras do Trabalho

- As classes **Livro** e **Autor** não podem ser utilizadas no trabalho.
- Trabalho preferencialmente em duplas; grupos com mais de 2 estudantes não serão aceitos.
- O tema escolhido deve ser divulgado no grupo do WhatsApp. Temas iguais serão considerados como cópia e a nota será dividida entre as duplas.
- Valor: 100 pontos.

## Extras

Os seguintes itens extras poderão agregar até 100% na nota final:

- Implementar o serviço para mais de uma classe, valendo 25% adicional.
- Implementar herança nas classes, valendo mais 25%.
- Implementar um relacionamento n para n, quebrando-o em dois relacionamentos 1 para n (como em bancos de dados), valendo mais 50%.

## Exemplos e Referências

Utilize os exemplos disponibilizados em nossa conta do GitHub para referência:

- [Exemplo 1: Web Service de Livraria](link_do_exemplo_1)
- [Exemplo 2: Web Service de Biblioteca](link_do_exemplo_2)

Certifique-se de seguir os padrões e orientações descritos nos capítulos 1 a 3 do livro **"SOA Aplicado - Integrando com Web Services e Além"** para garantir a conformidade do trabalho.

## Autores

- Nome do Estudante 1
- Nome do Estudante 2

---

**Nota**: A execução correta de todas as etapas descritas garantirá a totalidade da pontuação. Leia atentamente as instruções para evitar erros que possam impactar na nota final.
