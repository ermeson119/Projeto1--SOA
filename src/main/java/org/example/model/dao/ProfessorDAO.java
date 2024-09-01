package org.example.model.dao;

import org.example.model.entity.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarProfessor(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor (id, nome, departamento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, professor.getId());
            stmt.setString(2, professor.getNome());
            stmt.setString(3, professor.getDepartamento());
            stmt.executeUpdate();
        }
    }

    public Professor obterProfessorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM professor WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("departamento"));
                }
            }
        }
        return null;
    }

    private List<Professor> obterProfessoresDoCurso(String cursoCodigo) throws SQLException {
        String sql = "SELECT p.* FROM professor p JOIN curso_professor cp ON p.id = cp.professor_id WHERE cp.curso_codigo = ?";
        List<Professor> professores = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cursoCodigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Professor professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("departamento"));
                    professores.add(professor);
                    // Log para depuração
                    System.out.println("Professor encontrado: " + professor.getNome());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("Total de professores: " + professores.size());
        return professores;
    }


    public void atualizarProfessor(Professor professor) throws SQLException {
        String sql = "UPDATE professor SET nome = ?, departamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getDepartamento());
            stmt.setInt(3, professor.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarProfessor(int id) throws SQLException {
        String sql = "DELETE FROM professor WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
