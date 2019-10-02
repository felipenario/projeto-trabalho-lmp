/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.controller;

/**
 *
 * @author Felipe Nário
 */
public class Fornecedores {
    
    private String nome;
    private Cpf cpf;
    
    private int pk_fornecedor;

    public Fornecedores(String nome, Cpf cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Fornecedores(String nome, Cpf cpf, int pk_fornecedor) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_fornecedor = pk_fornecedor;
    }
    
    
    
}
