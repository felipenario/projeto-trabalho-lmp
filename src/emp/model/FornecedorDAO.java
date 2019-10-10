/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaom
 */
public class FornecedorDAO {
    public static int create(Fornecedor f) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO fornecedores(nome, cpf) VALUES (?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
              
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCpf());
        
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        int pk = pkset.getInt(1);
        
        f.setPk_fornecedor(pkset.getInt(1));
    
        return pkset.getInt(1);
    }
    
    public static Fornecedor retreave(int pk_Fornecedor) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM FORNECEDORES WHERE PK_FORNECEDOR =" + pk_Fornecedor
                );
        
        stm.setInt(1, pk_Fornecedor);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        
        Fornecedor f;
        
        if (rs.next()){
            f = new Fornecedor(
                    
                    rs.getString("nome"), 
                    rs.getString("cpf"),
                    rs.getInt("pk_fornecedor"));
           
            return f;

                        
        } else{
            throw new RuntimeException("Fornecedor não existe");                  
        }
      
    }
    
    public static ArrayList<Fornecedor> retreaveall(int pk_Fornecedor) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM FORNECEDORES WHERE PK_FORNECEDOR ="+pk_Fornecedor);
        
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        while(rs.next()){
            fornecedores.add(new Fornecedor(rs.getString("nome"), 
                    rs.getString("cpf"),
                    rs.getInt("pk_fornecedor")) );
        }

       return fornecedores;
    }
    
    public static void delete(Fornecedor f) throws SQLException{
        
         Connection conn = DBConnection.getConnection();
         if(f.getPk_fornecedor() != 0){
             conn.createStatement().execute("DELETE FROM FORNECEDORES WHERE PK_FORNECEDOR =" + f.getPk_fornecedor());
             
             
         }else {
            throw new RuntimeException("Fornecedor não existe");
         }
        
        
    }
    
        public static void update (Fornecedor f) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "UPDATE FORNECEDORES SET NOME = ?, CPF = ? WHERE PK_FORNECEDOR = ?");
        
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCpf());
        stm.setInt(3, f.getPk_fornecedor());
        
        stm.execute();
        
    }
}
