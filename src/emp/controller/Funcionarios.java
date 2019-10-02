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
public class Funcionarios {
    
    private String nome;
    private Cpf cpf;
    
    private int pk_funcionario;
    private int fk_cargo;

    public Funcionarios(String nome, Cpf cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Funcionarios(String nome, Cpf cpf, int pk_funcionario) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_funcionario = pk_funcionario;
    }

    public Funcionarios(String nome, Cpf cpf, int pk_funcionario, int fk_cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_funcionario = pk_funcionario;
        this.fk_cargo = fk_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
    }

    public int getFk_cargo() {
        return fk_cargo;
    }

    public void setFk_cargo(int fk_cargo) {
        this.fk_cargo = fk_cargo;
    }

    @Override
    public String toString() {
        return "Funcionarios{" + "nome=" + nome + ", cpf=" + cpf + ", pk_funcionario=" + pk_funcionario + ", fk_cargo=" + fk_cargo + '}';
    }    
    
    
}
