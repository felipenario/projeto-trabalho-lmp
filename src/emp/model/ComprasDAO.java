/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Compras;
import emp.controller.Compras_Itens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felipenario
 */
public class ComprasDAO {

    public static int create(Compras c) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO compras(fk_fornecedor, numero, datas) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, c.getFk_fornecedor());
        stm.setInt(2, c.getNumero());
        stm.setDate(3, c.getDatas());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        //int pk = pkset.getInt(1);
        c.setFk_fornecedor(pkset.getInt(1));
        c.setPk_compra(pkset.getInt(1));

        for (Compras_Itens t : c.getComprasitens()) {

            t.setFk_compra(c.getPk_compra());
            ComprasItensDAO.create(t);

        }

        return pkset.getInt(1);
    }

    public static Compras retreave(int pk_compra) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM COMPRAS WHERE PK_COMPRA =" + pk_compra
                );

        stm.setInt(1, pk_compra);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Compras c;

        if (rs.next()) {
            c = new Compras(
                    rs.getInt("numero"),
                    rs.getDate("datas"),
                    rs.getInt("pk_compra"),
                    rs.getInt("fk_fornecedor"));

            c.setComprasitens(ComprasItensDAO.retreaveall());

            return c;

        } else {
            throw new RuntimeException("Compra não existe");
        }

    }

    public static ArrayList<Compras> retreaveall() throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM COMPRAS ");

        ArrayList<Compras> compras = new ArrayList<>();

        while (rs.next()) {
            compras.add(new Compras(rs.getInt("numero"),
                    rs.getDate("datas"),
                    rs.getInt("pk_compra"),
                    rs.getInt("fk_fornecedor")));
        }

        return compras;
    }

    public static void delete(Compras c) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (c.getPk_compra() != 0) {
            conn.createStatement().execute("DELETE FROM COMPRAS WHERE PK_COMPRA =" + c.getPk_compra());

        } else {
            throw new RuntimeException("Compra não existe");
        }

    }

    public static void update(Compras c) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE COMPRAS SET NUMERO = ?, DATAS = ?, FK_FORNECEDOR = ? WHERE PK_COMPRA = ?");

        stm.setInt(1, c.getNumero());
        stm.setDate(2, c.getDatas());
        stm.setInt(3, c.getFk_fornecedor());
        stm.setInt(4, c.getPk_compra());

        stm.execute();

    }

}
