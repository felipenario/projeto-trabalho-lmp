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
public class Compras {
    
    private int numero;
    private Date datas;
    private ArrayList<Compras_Itens> comprasitens;
            
    private int pk_compra;
    private int fk_fornecedor;

    public Compras(int numero, Date datas, int pk_compra, int fk_fornecedor) {
        this.numero = numero;
        this.datas = datas;
        this.pk_compra = pk_compra;
        this.fk_fornecedor = fk_fornecedor;
    }

    public Compras(int numero, Date datas, ArrayList<Compras_Itens> comprasitens, int pk_compra, int fk_fornecedor) {
        this.numero = numero;
        this.datas = datas;
        this.comprasitens = comprasitens;
        this.pk_compra = pk_compra;
        this.fk_fornecedor = fk_fornecedor;
    }

    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDatas() {
        return datas;
    }

    public void setDatas(Date datas) {
        this.datas = datas;
    }

    public int getPk_compra() {
        return pk_compra;
    }

    public void setPk_compra(int pk_compra) {
        this.pk_compra = pk_compra;
    }

    public int getFk_fornecedor() {
        return fk_fornecedor;
    }

    public void setFk_fornecedor(int fk_fornecedor) {
        this.fk_fornecedor = fk_fornecedor;
    }

    public ArrayList<Compras_Itens> getComprasitens() {
        return comprasitens;
    }

    public void setComprasitens(ArrayList<Compras_Itens> comprasitens) {
        this.comprasitens = comprasitens;
    }

    @Override
    public String toString() {
        return "Compras{" + "numero=" + numero + ", datas=" + datas + ", comprasitens=" + comprasitens + ", pk_compra=" + pk_compra + ", fk_fornecedor=" + fk_fornecedor + '}';
    }
    
    
}
