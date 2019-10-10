/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Financeiros_Entradas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felipenario
 */
public class FinanceirosEntradasDAO {
    
    public static int create(Financeiros_Entradas f) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO financeiros_entradas(fk_venda, data_emissao, data_vencimento, data_baixa, valor, tipo_documento) VALUES (?, ?, ?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
              
        stm.setInt(1, f.getFk_venda());
        stm.setDate(2, f.getData_emissao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        f.setPk_entrada(pkset.getInt(1));
        //configura a chave primaria gerada no objeto telefone
        /*c.setPk_cliente(pkset.getInt(1));*/
        //configurou a chave estrangeira
     
        //c.getEndereco().setFk_cliente(pkset.getInt(1));
   
    
        return pkset.getInt(1);
    }
    
    public static Financeiros_Entradas retreave(int pk_entrada) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM FINANCEIROS_ENTRADAS WHERE PK_ENTRADA =" + pk_entrada
                );
        
        stm.setInt(1, pk_entrada);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        
        Financeiros_Entradas f;
        
        if (rs.next()){
            f = new Financeiros_Entradas(
                    rs.getDate("data_emissao"), 
                    rs.getDate("data_vencimento"),  
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"),
                    pk_entrada,
                    rs.getInt("fk_venda")
                    );
           
            return f;
//              pk_cliente,
//              rs.getString("nome"),
//              rs.getDate("nascimento"),
//              rs.getString("cpf")
                        
        } else{
            throw new RuntimeException("Entrada não existe");                  
        }
      
    }
    
    public static ArrayList<Financeiros_Entradas> retreaveall(int pk_entrada) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM FINANCEIROS_ENTRADAS WHERE PK_ENTRADA ="+pk_entrada);
        
        ArrayList<Financeiros_Entradas> entradas = new ArrayList<>();
        
        while(rs.next()){
            entradas.add(new Financeiros_Entradas(rs.getDate("data_emissao"), 
                    rs.getDate("data_vencimento"),  
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"),
                    pk_entrada,
                    rs.getInt("fk_venda")) );
        }

       return entradas;
    }
    
    public static void delete(Financeiros_Entradas f) throws SQLException{
        
         Connection conn = DBConnection.getConnection();
         if(f.getPk_entrada() != 0){
             conn.createStatement().execute("DELETE FROM FINANCEIROS_ENTRADAS WHERE PK_ENTRADA =" + f.getPk_entrada());
             
             
         }else {
            throw new RuntimeException("Entrada não existe");
         }
        
        
    }
    
        public static void update (Financeiros_Entradas f) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "UPDATE FINANCEIROS_ENTRADAS SET FK_VENDA = ?, DATA_EMISSAO = ?, DATA_VENCIMENTO = ?, DATA_BAIXA = ?, VALOR = ?, TIPO_DOCUMENTO = ? WHERE PK_ENTRADA = ?");
        
        stm.setInt(1, f.getFk_venda());
        stm.setDate(2, f.getData_emissao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        stm.setInt(7, f.getPk_entrada());
        
        stm.execute();
       
    }
    
}
