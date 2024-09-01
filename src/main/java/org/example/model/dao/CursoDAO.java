package org.example.model.dao;

import org.example.model.entity.Curso;
import org.example.model.entity.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo curso
    public void adicionarCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (codigo, nome, duracao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getCodigo());
            stmt.setString(2, curso.getNome());
            stmt.setInt(3, curso.getDuracao());
            stmt.executeUpdate();
        }
        adicionarProfessoresAoCurso(curso);
    }

    // Método para associar professores ao curso
    private void adicionarProfessoresAoCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso_professor (curso_codigo, professor_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Professor professor : curso.getProfessores()) {
                stmt.setString(1, curso.getCodigo());
                stmt.setInt(2, professor.getId());
                stmt.executeUpdate();
            }
        }
    }

    // Método para obter um curso pelo código
    public Curso obterCursoPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM curso WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso(rs.getString("codigo"), rs.getString("nome"), rs.getInt("duracao"), null);
                    curso.setProfessores(obterProfessoresDoCurso(curso.getCodigo()));
                    return curso;
                }
            }
        }
        return null;
    }

    // Método para obter a lista de professores de um curso
    private List<Professor> obterProfessoresDoCurso(String cursoCodigo) throws SQLException {
        String sql = "SELECT p.* FROM professor p JOIN curso_professor cp ON p.id = cp.professor_id WHERE cp.curso_codigo = ?";
        List<Professor> professores = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cursoCodigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    professores.add(new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("departamento")));
                }
            }
        }
        return professores;
    }

    // Método para listar todos os cursos
    public List<Curso> listarTodosCursos() throws SQLException {
        String sql = "SELECT * FROM curso";
        List<Curso> cursos = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso(rs.getString("codigo"), rs.getString("nome"), rs.getInt("duracao"), null);
                curso.setProfessores(obterProfessoresDoCurso(curso.getCodigo()));
                cursos.add(curso);
            }
        }
        return cursos;
    }

    // Método para atualizar um curso
    public void atualizarCurso(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, duracao = ? WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getDuracao());
            stmt.setString(3, curso.getCodigo());
            stmt.executeUpdate();
        }
        atualizarProfessoresDoCurso(curso);
    }

    // Método para atualizar a lista de professores de um curso
    private void atualizarProfessoresDoCurso(Curso curso) throws SQLException {
        String sqlDelete = "DELETE FROM curso_professor WHERE curso_codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlDelete)) {
            stmt.setString(1, curso.getCodigo());
            stmt.executeUpdate();
        }
        adicionarProfessoresAoCurso(curso);
    }

    // Método para deletar um curso
    public void deletarCurso(String codigo) throws SQLException {
        String sql = "DELETE FROM curso WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }
}
