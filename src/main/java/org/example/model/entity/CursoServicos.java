package org.example.model.entity;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CursoServicos {

    private List<Curso> cursos = new ArrayList<>();

    @WebMethod
    public Curso[] obterTodosCursos() {
        return cursos.toArray(new Curso[cursos.size()]);
    }

    @WebMethod
    public Curso[] obterCursosPorDuracao(@WebParam(name = "duracaoMinima") int duracaoMinima) {
        return cursos.stream()
                .filter(curso -> curso.getDuracao() >= duracaoMinima)
                .toArray(Curso[]::new);
    }

    @WebMethod
    public String adicionarCurso(@WebParam(name = "usuario") String usuario,
                                 @WebParam(name = "senha") String senha,
                                 @WebParam(name = "curso") Curso novoCurso) {
        // Verificar se os parâmetros 'usuario', 'senha', e 'novoCurso' não são nulos
        if (usuario == null || usuario.trim().isEmpty()) {
            return "Usuário não pode ser nulo ou vazio!";
        }
        if (senha == null || senha.trim().isEmpty()) {
            return "Senha não pode ser nula ou vazia!";
        }
        if (novoCurso == null) {
            return "Curso não pode ser nulo!";
        }

        // Verificar se atributos obrigatórios de 'novoCurso' não são nulos
        if (novoCurso.getCodigo() == null || novoCurso.getCodigo().trim().isEmpty()) {
            return "O código do curso não pode ser nulo ou vazio!";
        }
        if (novoCurso.getNome() == null || novoCurso.getNome().trim().isEmpty()) {
            return "O nome do curso não pode ser nulo ou vazio!";
        }

        // Verificar a autenticação
        if (autenticar(usuario, senha)) {
            cursos.add(novoCurso);
            return "Curso adicionado com sucesso!";
        } else {
            return "Autenticação falhou!";
        }
    }

    private boolean autenticar(String usuario, String senha) {
        // Implementar lógica de autenticação
        return "admin".equals(usuario) && "senha123".equals(senha);
    }
}
