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

    public static int create(Vendas v) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
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
        v.setFk_cliente(pkset.getInt(1));
        v.setFk_vendedor(pkset.getInt(1));
        v.setPk_venda(pkset.getInt(1));
       

        for (Vendas_Itens t : v.getVendasItens()) {

            t.setFk_venda(v.getPk_venda());
            VendasItensDAO.create(t);

        }

        return pkset.getInt(1);
    }

    public static Vendas retreave(int pk_venda) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM VENDAS WHERE PK_VENDA =" + pk_venda
                );

        stm.setInt(1, pk_venda);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Vendas v;

        if (rs.next()) {
            v = new Vendas(
                    rs.getInt("numero"),
                    rs.getDate("data"),
                    rs.getInt("pk_venda"),
                    rs.getInt("fk_cliente"),
                    rs.getInt("fk_vendedor"));

            v.setVendasItens(VendasItensDAO.retreaveall());

            return v;

        } else {
            throw new RuntimeException("Venda não existe");
        }

    }

    public static ArrayList<Vendas> retreaveall() throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM VENDAS ");

        ArrayList<Vendas> vendas = new ArrayList<>();

        while (rs.next()) {
            vendas.add(new Vendas(rs.getInt("numero"),
                    rs.getDate("data"),
                    rs.getInt("pk_venda"),
                    rs.getInt("fk_cliente"),
                    rs.getInt("fk_vendedor")));
        }

        return vendas;
    }

    public static void delete(Vendas v) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (v.getPk_venda() != 0) {
            conn.createStatement().execute("DELETE FROM VENDAS WHERE PK_VENDA =" + v.getPk_venda());

        } else {
            throw new RuntimeException("Venda não existe");
        }

    }

    public static void update(Vendas v) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE VENDAS SET NUMERO = ?, DATA = ?, FK_CLIENTE = ?, FK_VENDEDOR = ? WHERE PK_VENDA = ?");

        stm.setInt(1, v.getNumero());
        stm.setDate(2, v.getData());
        stm.setInt(3, v.getFk_cliente());
        stm.setInt(4, v.getFk_vendedor());
        stm.setInt(5, v.getPk_venda());

        stm.execute();

    }
}
