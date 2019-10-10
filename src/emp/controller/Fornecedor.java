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
public class Fornecedor {

    private String nome;
    private Cpf cpf;

    private int pk_fornecedor;

    public Fornecedor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
    }

    public Fornecedor(String nome, String cpf, int pk_fornecedor) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.pk_fornecedor = pk_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf.getNum();
    }

    public void setCpf(String numero) {
        this.cpf = new Cpf(numero);
    }

    public int getPk_fornecedor() {
        return pk_fornecedor;
    }

    public void setPk_fornecedor(int pk_fornecedor) {
        this.pk_fornecedor = pk_fornecedor;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "nome=" + nome + ", cpf=" + cpf + ", pk_fornecedor=" + pk_fornecedor + '}';
    }

}
