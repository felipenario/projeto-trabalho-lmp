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
public class Vendas_Itens {

    private int qtd;
    private double valor_unitario;

    private int pk_item;
    private int fk_venda;
    private int fk_produto;

    public Vendas_Itens(int qtd, double valor_unitario) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
    }

    public Vendas_Itens(int qtd, double valor_unitario, int pk_item, int fk_venda) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.pk_item = pk_item;
        this.fk_venda = fk_venda;
    }

    public Vendas_Itens(int qtd, double valor_unitario, int pk_item, int fk_venda, int fk_produto) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.pk_item = pk_item;
        this.fk_venda = fk_venda;
        this.fk_produto = fk_produto;
    }

    public Vendas_Itens(int qtd, double valor_unitario, int fk_produto) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.fk_produto = fk_produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getPk_item() {
        return pk_item;
    }

    public void setPk_item(int pk_item) {
        this.pk_item = pk_item;
    }

    public int getFk_venda() {
        return fk_venda;
    }

    public void setFk_venda(int fk_venda) {
        this.fk_venda = fk_venda;
    }

    public int getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(int fk_produto) {
        this.fk_produto = fk_produto;
    }

    @Override
    public String toString() {
        return "Vendas_Itens{" + "qtd=" + qtd + ", valor_unitario=" + valor_unitario + ", pk_item=" + pk_item + ", fk_venda=" + fk_venda + ", fk_produto=" + fk_produto + '}';
    }

}
