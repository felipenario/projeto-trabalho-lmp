/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Fornecedores_Enderecos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Felipe Nário
 */
public class FornecedorEnderecosDAO {

    public static int create(Fornecedores_Enderecos f) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO Fornecedores_Enderecos(fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, f.getFk_fornecedor());
        stm.setString(2, f.getLogradouro());
        stm.setString(3, f.getBairro());
        stm.setString(4, f.getCidade());
        stm.setString(5, f.getEstado());
        stm.setString(6, f.getPais());
        stm.setString(7, f.getCep());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        //configura a chave primaria gerada no objeto telefone
        f.setPk_endereco(pkset.getInt(1));

        return pkset.getInt(1);
    }

    public static Fornecedores_Enderecos retreave(int pk_endereco) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM FORNECEDORES_ENDERECOS WHERE PK_ENDERECO=?"
                );

        stm.setInt(1, pk_endereco);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        if (rs.next()) {
            return new Fornecedores_Enderecos(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor")
            );
        } else {
            throw new RuntimeException("Endereço não existe");
        }
    }

    public static Fornecedores_Enderecos retreaveall(int fk_fornecedor) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM FORNECEDORES_ENDERECOS WHERE FK_FORNECEDOR=" + fk_fornecedor);
        Fornecedores_Enderecos f = null;
        if (rs.next()) {
            f = new Fornecedores_Enderecos(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor")
            );
        }
        return f;
    }

    public static void delete(Fornecedores_Enderecos f) throws SQLException {
        Connection conn = DBConnection.getConnection();

        if (f.getPk_endereco() != 0) {
            conn.createStatement().
                    execute("DELETE FROM FORNECEDORES_ENDERECOS WHERE PK_ENDERECO=" + f.getPk_endereco());
        } else {
            throw new RuntimeException("Endereco nao existe no BD");
        }

    }

    public static void update(Fornecedores_Enderecos f) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE ENDERECO SET LOGRADOURO=?, BAIRRO=?, CIDADE=?, ESTADO=?,PAIS = ?, CEP = ?, PK_ENDERECO = ? WHERE FK_FORNECEDOR = ?");

        //configura as interrogações pela ordem
        stm.setString(1, f.getLogradouro());
        stm.setString(2, f.getBairro());
        stm.setString(3, f.getCidade());
        stm.setString(4, f.getEstado());
        stm.setString(5, f.getPais());
        stm.setString(6, f.getCep());
        stm.setInt(7, f.getPk_endereco());
        stm.setInt(8, f.getFk_fornecedor());

        stm.execute();
    }

}
