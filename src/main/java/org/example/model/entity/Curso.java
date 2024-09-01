package org.example.model.entity;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@WebService
@XmlRootElement(name = "curso")
public class Curso {
    private String codigo;
    private String nome;
    private int duracao;
    private List<Professor> professores;

    public Curso() {
        this.professores = new ArrayList<>();
    }

    public Curso(String codigo, String nome, int duracao, List<Professor> professores) {
        this.codigo = codigo;
        this.nome = nome;
        this.duracao = duracao;
        this.professores = professores != null ? professores : new ArrayList<>();
    }

    public void adicionarProfessor(Professor professor) {
        this.professores.add(professor);
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlElementWrapper(name = "professores")
    @XmlElement(name = "professor")
    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
