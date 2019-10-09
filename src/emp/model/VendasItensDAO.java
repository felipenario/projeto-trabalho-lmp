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
 * @author Felipe Nário
 */
public class VendasItensDAO {
    
    public static int create(Vendas_Itens v) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO Vendas_Itens(fk_venda, fk_produto, qtd, valor_unitario) VALUES (?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
              
        stm.setInt(1, v.getFk_venda());
        stm.setInt(2, v.getFk_produto());
        stm.setInt(3, v.getQtd());
        stm.setDouble(4, v.getValor_unitario());
     
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        //int pk = pkset.getInt(1);
        
        v.setPk_item(pkset.getInt(1));
       
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
        
        
        Vendas b;
        
        if (rs.next()){
            b = new Vendas(
                    rs.getInt("numero"),
                    rs.getDate("data"),
                    rs.getInt("pk_venda"));
            
            return b;

                        
        } else{
            throw new RuntimeException("Cliente não existe");                  
        }
      
    }
    
    public static ArrayList<Venda> retreaveall(int pk_Vendas) throws SQLException{
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
