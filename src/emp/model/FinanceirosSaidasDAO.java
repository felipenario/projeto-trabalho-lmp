/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Financeiros_Saidas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felipenario
 */
public class FinanceirosSaidasDAO {
    
    public static int create(Financeiros_Saidas f) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO financeiros_saidas(fk_compra, data_emissao, data_vencimento, data_baixa, valor, tipo_documento) VALUES (?, ?, ?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
              
        stm.setInt(1, f.getFk_compra());
        stm.setDate(2, f.getData_emissao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        f.setPk_financeiro(pkset.getInt(1));
        //configura a chave primaria gerada no objeto telefone
        /*c.setPk_cliente(pkset.getInt(1));*/
        //configurou a chave estrangeira
     
        //c.getEndereco().setFk_cliente(pkset.getInt(1));
   
    
        return pkset.getInt(1);
    }
    
    public static Financeiros_Saidas retreave(int pk_financeiro) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM FINANCEIROS_SAIDAS WHERE PK_FINANCEIRO =" + pk_financeiro
                );
        
        stm.setInt(1, pk_financeiro);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        
        Financeiros_Saidas f;
        
        if (rs.next()){
            f = new Financeiros_Saidas(
                    rs.getDate("data_emissao"), 
                    rs.getDate("data_vencimento"),  
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"),
                    pk_financeiro,
                    rs.getInt("fk_compra")
                    );
           
            return f;
//              pk_cliente,
//              rs.getString("nome"),
//              rs.getDate("nascimento"),
//              rs.getString("cpf")
                        
        } else{
            throw new RuntimeException("Saida não existe");                  
        }
      
    }
    
    public static ArrayList<Financeiros_Saidas> retreaveall(int pk_financeiro) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM FINANCEIROS_SAIDAS WHERE PK_FINANCEIRO ="+pk_financeiro);
        
        ArrayList<Financeiros_Saidas> saidas = new ArrayList<>();
        
        while(rs.next()){
            saidas.add(new Financeiros_Saidas(rs.getDate("data_emissao"), 
                    rs.getDate("data_vencimento"),  
                    rs.getDate("data_baixa"),
                    rs.getDouble("valor"),
                    rs.getString("tipo_documento"),
                    pk_financeiro,
                    rs.getInt("fk_compra")) );
        }

       return saidas;
    }
    
    public static void delete(Financeiros_Saidas f) throws SQLException{
        
         Connection conn = DBConnection.getConnection();
         if(f.getPk_financeiro() != 0){
             conn.createStatement().execute("DELETE FROM FINANCEIROS_SAIDAS WHERE PK_FINANCEIRO =" + f.getPk_financeiro());
             
             
         }else {
            throw new RuntimeException("Saida não existe");
         }
        
        
    }
    
        public static void update (Financeiros_Saidas f) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "UPDATE FINANCEIROS_SAIDAS SET FK_COMPRA = ?, DATA_EMISSAO = ?, DATA_VENCIMENTO = ?, DATA_BAIXA = ?, VALOR = ?, TIPO_DOCUMENTO = ? WHERE PK_FINANCEIRO = ?");
        
        stm.setInt(1, f.getFk_compra());
        stm.setDate(2, f.getData_emissao());
        stm.setDate(3, f.getData_vencimento());
        stm.setDate(4, f.getData_baixa());
        stm.setDouble(5, f.getValor());
        stm.setString(6, f.getTipo_documento());
        stm.setInt(7, f.getPk_financeiro());
        
        stm.execute();
       
    }
    
}
