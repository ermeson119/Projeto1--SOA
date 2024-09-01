package org.example.model.entity;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService
public class Professor {
    private int id;
    private String nome;
    private String departamento;

    public Professor() {
    }

    // Construtor
    public Professor(int id, String nome, String departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    @XmlElement
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
