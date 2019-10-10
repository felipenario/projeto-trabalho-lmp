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
    private Funcionarios_Enderecos endereco;

    private int pk_funcionario;
    private int fk_cargo;

    public Funcionarios(String nome, String cpf) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
    }

    public Funcionarios(String nome, String cpf, int pk_funcionario, int fk_cargo) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.pk_funcionario = pk_funcionario;
        this.fk_cargo = fk_cargo;
    }

    public Funcionarios(String nome, String cpf, int pk_funcionario, int fk_cargo, Funcionarios_Enderecos endereco) {
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.pk_funcionario = pk_funcionario;
        this.fk_cargo = fk_cargo;
        this.endereco = endereco;
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

    public Funcionarios_Enderecos getEndereco() {
        return endereco;
    }

    public void setEndereco(Funcionarios_Enderecos endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Funcionarios{" + "nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", pk_funcionario=" + pk_funcionario + ", fk_cargo=" + fk_cargo + '}';
    }

}
