/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

import emp.controller.Cliente;
import emp.controller.Clientes_Enderecos;
import emp.controller.Endereco;
import emp.controller.Telefone;
import emp.controller.Vendas;
import emp.controller.Vendas_Itens;
import emp.model.ClienteDAO;
import emp.model.TelefoneDAO;
import emp.model.VendaDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author L
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
       /*  ArrayList<Telefone> t1;
        try {
            t1 = TelefoneDAO.retreaveall(3);
         System.out.println(t1);            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            */
        
        
        Clientes_Enderecos e = new Clientes_Enderecos("aaaaa", "bbbbb", "aaa", "go", "bbbbb", "bbbbb");
        
        
        Cliente c = new Cliente("Jukera", "60837094151", e);
        
        ArrayList<Vendas_Itens> itens = new ArrayList<>();
        itens.add(new Vendas_Itens(1, 2.500));
        
        Vendas v = new Vendas(12, new Date(System.currentTimeMillis()), itens);
        
        //Date.valueOf("2019-10-10")
        
        
       /* ArrayList<Telefone> tels = new ArrayList<>();
        tels.add(new Telefone(64, "99999-8888"));
        tels.add(new Telefone(64, "99999-7777"));
        tels.add(new Telefone(64, "99999-5555"));
        tels.add(new Telefone(64, "99999-4444"));
        tels.add(new Telefone(64, "99999-3333"));
        
        c.setTelefones(tels);
        */
       /* try {
            ClienteDAO.create(c);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        try {
            VendaDAO.create(v);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
 }