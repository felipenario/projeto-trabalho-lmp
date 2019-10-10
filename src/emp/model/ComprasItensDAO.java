/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

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
public class ComprasItensDAO {

    public static int create(Compras_Itens c) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO Compras_Itens(fk_compra, fk_produto, qtd, valor_unitario) VALUES (?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, c.getFk_compra());
        stm.setInt(2, c.getFk_produto());
        stm.setInt(3, c.getQtd());
        stm.setDouble(4, c.getValor_unitario());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        //int pk = pkset.getInt(1);
        c.setPk_item(pkset.getInt(1));

        return pkset.getInt(1);
    }

    public static Compras_Itens retreave(int pk_item) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM COMPRAS_ITENS WHERE PK_ITEM =" + pk_item
                );

        stm.setInt(1, pk_item);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Compras_Itens c;

        if (rs.next()) {
            c = new Compras_Itens(
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario"),
                    rs.getInt("pk_item"),
                    rs.getInt("fk_compra"),
                    rs.getInt("fk_produto"));

            return c;

        } else {
            throw new RuntimeException("Os itens dessa compra não existe");
        }

    }

    public static ArrayList<Compras_Itens> retreaveall(int pk_item) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM COMPRAS_ITENS WHERE PK_ITEM =" + pk_item);

        ArrayList<Compras_Itens> comprasitens = new ArrayList<>();

        while (rs.next()) {
            comprasitens.add(new Compras_Itens(rs.getInt("qtd"), rs.getInt("valor_unitario"), pk_item, rs.getInt("fk_compra"), rs.getInt("fk_produto")));
        }

        return comprasitens;
    }

    public static void delete(Compras_Itens c) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (c.getPk_item() != 0) {
            conn.createStatement().execute("DELETE FROM COMPRAS_ITENS WHERE PK_ITEM =" + c.getPk_item());

        } else {
            throw new RuntimeException("Esse(s) item da compra não existem");
        }

    }

    public static void update(Compras_Itens c) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE COMPRAS_ITENS SET QTD = ?, VALOR_UNITARIO = ?, FK_COMPRA = ?, FK_PRODUTO = ? WHERE PK_ITEM = ?");

        stm.setInt(1, c.getQtd());
        stm.setDouble(2, c.getValor_unitario());
        stm.setInt(3, c.getFk_compra());
        stm.setInt(4, c.getFk_produto());
        stm.setInt(5, c.getPk_item());

        stm.execute();

    }
}
