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
public class Compras_Itens {

    private int qtd;
    private double valor_unitario;

    private int pk_item;
    private int fk_compra;
    private int fk_produto;

    public Compras_Itens(int qtd, double valor_unitario, int pk_item) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.pk_item = pk_item;
    }

    public Compras_Itens(int qtd, double valor_unitario, int pk_item, int fk_compra) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.pk_item = pk_item;
        this.fk_compra = fk_compra;
    }

    public Compras_Itens(int qtd, double valor_unitario, int pk_item, int fk_compra, int fk_produto) {
        this.qtd = qtd;
        this.valor_unitario = valor_unitario;
        this.pk_item = pk_item;
        this.fk_compra = fk_compra;
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

    public int getFk_compra() {
        return fk_compra;
    }

    public void setFk_compra(int fk_compra) {
        this.fk_compra = fk_compra;
    }

    public int getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(int fk_produto) {
        this.fk_produto = fk_produto;
    }

    @Override
    public String toString() {
        return "Compras_Itens{" + "qtd=" + qtd + ", valor_unitario=" + valor_unitario + ", pk_item=" + pk_item + ", fk_compra=" + fk_compra + ", fk_produto=" + fk_produto + '}';
    }

}
