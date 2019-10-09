/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Vendas;
import emp.controller.Vendas_Itens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaom
 */
public class VendaDAO {
      public static int create(Vendas v) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO vendas(fk_cliente, fk_vendedor, numero, data) VALUES (?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
              
        stm.setInt(1, v.getFk_cliente());
        stm.setInt(2, v.getFk_vendedor());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, v.getData());
     
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        //int pk = pkset.getInt(1);
        
        v.setPk_venda(pkset.getInt(1));
       
        
         for(Vendas_Itens t:v.getVendasItens()){
            
            t.setFk_venda(v.getPk_venda());
            VendasItensDAO.create(t);
            
        }
    
        return pkset.getInt(1);
    }
    
    public static Vendas retreave(int pk_venda) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM VENDAS WHERE PK_VENDA =" + pk_venda
                );
        
        stm.setInt(1, pk_venda);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        
        Vendas v;
        
        if (rs.next()){
            v = new Vendas(
                    rs.getInt("numero"),
                    rs.getDate("data"),
                    rs.getInt("pk_venda"));
            
            v.setVendasItens(VendasItensDAO.retreaveall(v.getPk_venda()));
            
            
            return v;

                        
        } else{
            throw new RuntimeException("Venda não existe");                  
        }
      
    }
    
    public static ArrayList<Vendas> retreaveall(int pk_Vendas) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM VENDA WHERE PK_VENDA ="+pk_Vendas);
        
        ArrayList<Venda> vendas = new ArrayList<>();
        
        while(rs.next()){
            vendas.add(new Venda(pk_Vendas, rs.getInt("numero"), rs.getDate("date")) );
        }

       return vendas;
    }
    
    public static void delete(Venda b) throws SQLException{
        
         Connection conn = DBConnection.getConnection();
         if(b.getPk_Venda() != 0){
             conn.createStatement().execute("DELETE FROM VENDAS WHERE PK_VENDA =" + b.getPk_Venda());
             
             
         }else {
            throw new RuntimeException("Cliente não existe");
         }
        
        
    }
    
        public static void update (Venda b) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "UPDATE VENDAS SET NUMERO = ?, DATA = ? WHERE PK_VENDA = ?");
        
        stm.setInt(1, b.getNumero());
        stm.setDate(2, b.getData());
        stm.setInt(3, b.getPk_Venda());
        
        stm.execute();
        
    }
}


