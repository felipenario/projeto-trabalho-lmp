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
public class Financeiros_Entradas {

    private Date data_emissao;
    private Date data_vencimento;
    private Date data_baixa;
    private double valor;
    private String tipo_documento;

    private int pk_entrada;
    private int fk_venda;

    public Financeiros_Entradas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
    }
    
    public Financeiros_Entradas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento, int pk_entrada, int fk_venda) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
        this.pk_entrada = pk_entrada;
        this.fk_venda = fk_venda;
    }

    public Financeiros_Entradas(Date data_emissao, Date data_vencimento, Date data_baixa, double valor, String tipo_documento, int fk_venda) {
        this.data_emissao = data_emissao;
        this.data_vencimento = data_vencimento;
        this.data_baixa = data_baixa;
        this.valor = valor;
        this.tipo_documento = tipo_documento;
        this.fk_venda = fk_venda;
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

    public int getPk_entrada() {
        return pk_entrada;
    }

    public void setPk_entrada(int pk_entrada) {
        this.pk_entrada = pk_entrada;
    }

    public int getFk_venda() {
        return fk_venda;
    }

    public void setFk_venda(int fk_venda) {
        this.fk_venda = fk_venda;
    }

    @Override
    public String toString() {
        return "Financeiros_Entradas{" + "data_emissao=" + data_emissao + ", data_vencimento=" + data_vencimento + ", data_baixa=" + data_baixa + ", valor=" + valor + ", tipo_documento=" + tipo_documento + ", pk_entrada=" + pk_entrada + ", fk_venda=" + fk_venda + '}';
    }

}
