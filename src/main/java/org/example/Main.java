package org.example;

import org.example.model.entity.CursoServicos;

import javax.xml.ws.Endpoint;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/curso-webservice-soap/CursoService?wsdl\n", new CursoServicos());
        System.out.println("Serviço inicializado!");

    }

}