package emp.controller;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author L
 */
public class Cliente {
    private int pk_cliente;
    private String nome;
    private Cpf cpf;


    public Cliente(int pk_cliente, String nome, String cpf) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
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
    
     

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome +  "cpf=" + cpf + '}';
    }
    
    public void print(){
        System.out.println(this);
        
        this.cpf.print();
    }

    public int setPk_cliente() {
        return pk_cliente;
        
    }
    
    
    public void setPk_cliente(int pk_cliente) {
        this.pk_cliente = pk_cliente;
    }
    
    public int getPk_cliente() {
        return pk_cliente;
    }

}
