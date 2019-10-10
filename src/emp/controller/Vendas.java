/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.controller;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Felipe Nário
 */
public class Vendas {

    private int numero;
    private Date data;
    private ArrayList<Vendas_Itens> vendasItens;

    private int pk_venda;
    private int fk_cliente;
    private int fk_vendedor;

    public Vendas(int numero, Date data, ArrayList<Vendas_Itens> vendasItens, int pk_venda, int fk_cliente, int fk_vendedor) {
        this.numero = numero;
        this.data = data;
        this.vendasItens = vendasItens;
        this.pk_venda = pk_venda;
        this.fk_cliente = fk_cliente;
        this.fk_vendedor = fk_vendedor;
    }

    public Vendas(int numero, Date data) {
        this.numero = numero;
        this.data = data;
    }

    public Vendas(int numero, Date data, int pk_venda, int fk_cliente, int fk_vendedor) {
        this.numero = numero;
        this.data = data;
        this.pk_venda = pk_venda;
        this.fk_cliente = fk_cliente;
        this.fk_vendedor = fk_vendedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPk_venda() {
        return pk_venda;
    }

    public void setPk_venda(int pk_venda) {
        this.pk_venda = pk_venda;
    }

    public int getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public int getFk_vendedor() {
        return fk_vendedor;
    }

    public void setFk_vendedor(int fk_vendedor) {
        this.fk_vendedor = fk_vendedor;
    }

    public ArrayList<Vendas_Itens> getVendasItens() {
        return vendasItens;
    }

    public void setVendasItens(ArrayList<Vendas_Itens> vendasItens) {
        this.vendasItens = vendasItens;
    }

    @Override
    public String toString() {
        return "Vendas{" + "numero=" + numero + ", data=" + data + ", vendasItens=" + vendasItens + ", pk_venda=" + pk_venda + ", fk_cliente=" + fk_cliente + ", fk_vendedor=" + fk_vendedor + '}';
    }

}
