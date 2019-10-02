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
public class Produtos {
    
    private String nome;
    private int estoque_minimo;
    private int qtd_estoque;
    
    private int pk_produto;

    public Produtos(String nome, int estoque_minimo, int qtd_estoque) {
        this.nome = nome;
        this.estoque_minimo = estoque_minimo;
        this.qtd_estoque = qtd_estoque;
    }

    public Produtos(String nome, int estoque_minimo, int qtd_estoque, int pk_produto) {
        this.nome = nome;
        this.estoque_minimo = estoque_minimo;
        this.qtd_estoque = qtd_estoque;
        this.pk_produto = pk_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public int getPk_produto() {
        return pk_produto;
    }

    public void setPk_produto(int pk_produto) {
        this.pk_produto = pk_produto;
    }

    @Override
    public String toString() {
        return "Produtos{" + "nome=" + nome + ", estoque_minimo=" + estoque_minimo + ", qtd_estoque=" + qtd_estoque + ", pk_produto=" + pk_produto + '}';
    }
    
    
    
}
