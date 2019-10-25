/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.controller;

import java.sql.Date;

/**
 *
 * @author Felipe Nário
 */
public class Financeiros_Saidas {

    private Date data_emissao;
    private Date data_vencimento;
    private Date data_baixa;
    private double valor;
    private String tipo_documento;

    private int pk_financeiro;
    private int fk_compra;

    public Financeiros_Saidas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
    }


    public Financeiros_Saidas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento, int pk_financeiro, int fk_compra) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
        this.pk_financeiro = pk_financeiro;
        this.fk_compra = fk_compra;
    }

    public Financeiros_Saidas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento, int fk_compra) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
        this.fk_compra = fk_compra;
    }
    
    

    public Date getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Date getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(Date data_baixa) {
        this.data_baixa = data_baixa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getPk_financeiro() {
        return pk_financeiro;
    }

    public void setPk_financeiro(int pk_financeiro) {
        this.pk_financeiro = pk_financeiro;
    }

    public int getFk_compra() {
        return fk_compra;
    }

    public void setFk_compra(int fk_compra) {
        this.fk_compra = fk_compra;
    }

    @Override
    public String toString() {
        return "Financeiros_Saidas{" + "data_emissao=" + data_emissao + ", data_vencimento=" + data_vencimento + ", data_baixa=" + data_baixa + ", valor=" + valor + ", tipo_documento=" + tipo_documento + ", pk_financeiro=" + pk_financeiro + ", fk_compra=" + fk_compra + '}';
    }

}
