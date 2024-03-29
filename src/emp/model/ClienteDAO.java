/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe Nário
 */
public class ClienteDAO {

    public static int create(Cliente c) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO clientes(nome, cpf) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        c.setPk_cliente(pkset.getInt(1));
        //configura a chave primaria gerada no objeto telefone
        /*c.setPk_cliente(pkset.getInt(1));*/
        //configurou a chave estrangeira

        c.getEndereco().setFk_cliente(pkset.getInt(1));
        ClientesEnderecosDAO.create(c.getEndereco());

        return pkset.getInt(1);
    }

    public static Cliente retreave(int pk_cliente) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM CLIENTES WHERE PK_CLIENTE =" + pk_cliente
                );

        stm.setInt(1, pk_cliente);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Cliente c;

        if (rs.next()) {
            c = new Cliente(
                    rs.getInt("pk_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"));

            return c;
//              pk_cliente,
//              rs.getString("nome"),
//              rs.getDate("nascimento"),
//              rs.getString("cpf")

        } else {
            throw new RuntimeException("Cliente não existe");
        }

    }

    public static ArrayList<Cliente> retreaveall() throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM CLIENTES ");

        ArrayList<Cliente> clientes = new ArrayList<>();

        while (rs.next()) {
            clientes.add(new Cliente(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf")));
        }

        return clientes;
    }

    public static void delete(Cliente c) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (c.getPk_cliente() != 0) {
            conn.createStatement().execute("DELETE FROM CLIENTES WHERE PK_CLIENTE =" + c.getPk_cliente());

        } else {
            throw new RuntimeException("Cliente não existe");
        }

    }

    public static void update(Cliente c) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE CLIENTES SET NOME = ?, NASCIMENTO = ?, CPF = ? WHERE PK_CLIENTE = ?");

        stm.setString(1, c.getNome());
        stm.setString(3, c.getCpf());
        stm.setInt(4, c.getPk_cliente());

        stm.execute();

    }

}
