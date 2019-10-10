/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Clientes_Enderecos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Felipe Nário
 */
public class ClientesEnderecosDAO {

    public static int create(Clientes_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO Clientes_Enderecos(fk_cliente, logradouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, e.getFk_cliente());
        stm.setString(2, e.getLogradouro());
        stm.setString(3, e.getBairro());
        stm.setString(4, e.getCidade());
        stm.setString(5, e.getEstado());
        stm.setString(6, e.getPais());
        stm.setString(7, e.getCep());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        //configura a chave primaria gerada no objeto telefone
        e.setPk_endereco(pkset.getInt(1));

        return pkset.getInt(1);
    }

    public static Clientes_Enderecos retreave(int pk_endereco) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM CLIENTES_ENDERECOS WHERE PK_ENDERECO=?"
                );

        stm.setInt(1, pk_endereco);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        if (rs.next()) {
            return new Clientes_Enderecos(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_cliente")
            );
        } else {
            throw new RuntimeException("Endereço não existe");
        }
    }

    public static Clientes_Enderecos retreaveall(int fk_cliente) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM CLIENTES_ENDERECOS WHERE FK_CLIENTE=" + fk_cliente);
        Clientes_Enderecos e = null;
        if (rs.next()) {
            e = new Clientes_Enderecos(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_cliente")
            );
        }
        return e;
    }

    public static void delete(Clientes_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        if (e.getPk_endereco() != 0) {
            conn.createStatement().
                    execute("DELETE FROM CLIENTES_ENDERECOS WHERE PK_ENDERECO=" + e.getPk_endereco());
        } else {
            throw new RuntimeException("Endereco nao existe no BD");
        }

    }

    public static void update(Clientes_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE ENDERECO SET LOGRADOURO=?, BAIRRO=?, CIDADE=?, ESTADO=?,PAIS = ?, CEP = ?, PK_ENDERECO = ? WHERE FK_CLIENTE = ?");

        //configura as interrogações pela ordem
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getBairro());
        stm.setString(3, e.getCidade());
        stm.setString(4, e.getEstado());
        stm.setString(5, e.getPais());
        stm.setString(6, e.getCep());
        stm.setInt(7, e.getPk_endereco());
        stm.setInt(8, e.getFk_cliente());

        stm.execute();
    }

}
