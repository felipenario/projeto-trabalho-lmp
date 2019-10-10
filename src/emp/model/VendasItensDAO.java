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

    public static int create(Vendas_Itens v) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
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

    public static Vendas_Itens retreave(int pk_item) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM VENDAS_ITENS WHERE PK_ITEM =" + pk_item
                );

        stm.setInt(1, pk_item);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Vendas_Itens v;

        if (rs.next()) {
            v = new Vendas_Itens(
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario"),
                    rs.getInt("pk_item"),
                    rs.getInt("fk_venda"),
                    rs.getInt("fk_produto"));

            return v;

        } else {
            throw new RuntimeException("Os itens dessa venda não existe");
        }

    }

    public static ArrayList<Vendas_Itens> retreaveall(int pk_item) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM VENDA_ITENS WHERE PK_ITEM =" + pk_item);

        ArrayList<Vendas_Itens> vendasitens = new ArrayList<>();

        while (rs.next()) {
            vendasitens.add(new Vendas_Itens(rs.getInt("qtd"), rs.getInt("valor_unitario"), pk_item, rs.getInt("fk_venda"), rs.getInt("fk_produto")));
        }

        return vendasitens;
    }

    public static void delete(Vendas_Itens v) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (v.getPk_item() != 0) {
            conn.createStatement().execute("DELETE FROM VENDAS_ITENS WHERE PK_ITEM =" + v.getPk_item());

        } else {
            throw new RuntimeException("Esse(s) item da venda não existem");
        }

    }

    public static void update(Vendas_Itens v) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE VENDAS_ITENS SET QTD = ?, VALOR_UNITARIO = ?, FK_VENDA = ?, FK_PRODUTO = ? WHERE PK_ITEM = ?");

        stm.setInt(1, v.getQtd());
        stm.setDouble(2, v.getValor_unitario());
        stm.setInt(3, v.getFk_venda());
        stm.setInt(4, v.getFk_produto());
        stm.setInt(5, v.getPk_item());

        stm.execute();

    }

}
