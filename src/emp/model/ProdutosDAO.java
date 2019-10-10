/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe Nário
 */
public class ProdutosDAO {

    public static int create(Produtos p) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO vendas(nome, estoque_minimo, qtd_estoque) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoque_minimo());
        stm.setInt(3, p.getQtd_estoque());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        //int pk = pkset.getInt(1);
        p.setPk_produto(pkset.getInt(1));

        return pkset.getInt(1);
    }

    public static Produtos retreave(int pk_produto) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM PRODUTOS WHERE PK_PRODUTO =" + pk_produto
                );

        stm.setInt(1, pk_produto);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Produtos p;

        if (rs.next()) {
            p = new Produtos(
                    rs.getString("nome"),
                    rs.getInt("estoque_minimo"),
                    rs.getInt("qtd_estoque"),
                    rs.getInt("pk_produto"));

            return p;

        } else {
            throw new RuntimeException("Produtos não existe");
        }

    }

    public static ArrayList<Produtos> retreaveall(int pk_produto) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM PRODUTOS WHERE PK_PRODUTO =" + pk_produto);

        ArrayList<Produtos> produtos = new ArrayList<>();

        while (rs.next()) {
            produtos.add(new Produtos(rs.getString("nome"), rs.getInt("estoque_minimo"), rs.getInt("qtd_estoque"), pk_produto));
        }

        return produtos;
    }

    public static void delete(Produtos p) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (p.getPk_produto() != 0) {
            conn.createStatement().execute("DELETE FROM PRODUTOS WHERE PK_PRODUTO =" + p.getPk_produto());

        } else {
            throw new RuntimeException("Produto não existe");
        }

    }

    public static void update(Produtos p) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE PRODUTOS SET NOME = ?, ESTOQUE_MINIMO = ?, QTD_ESTOQUE = ? WHERE PK_PRODUTO = ?");

        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoque_minimo());
        stm.setInt(3, p.getQtd_estoque());
        stm.setInt(4, p.getPk_produto());

        stm.execute();

    }

}
