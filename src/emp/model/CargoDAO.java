/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaom
 */
public class CargoDAO {

    public static int create(Cargo a) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO cargos(nome, descricao) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1, a.getNome());
        stm.setString(2, a.getDescricao());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        int pk = pkset.getInt(1);

        return pkset.getInt(1);
    }

    public static Cargo retreave(int pk_cargo) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM CARGOS WHERE PK_CARGO =" + pk_cargo
                );

        stm.setInt(1, pk_cargo);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Cargo a;

        if (rs.next()) {
            a = new Cargo(
                    rs.getInt("pk_cargo"),
                    rs.getString("nome"),
                    rs.getString("descricao"));

            return a;

        } else {
            throw new RuntimeException("Cliente não existe");
        }

    }

    public static ArrayList<Cargo> retreaveall(int pk_cargo) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM CARGOS WHERE PK_CARGO =" + pk_cargo);

        ArrayList<Cargo> cargo = new ArrayList<>();

        while (rs.next()) {
            cargo.add(new Cargo(pk_cargo, rs.getString("nome"), rs.getString("descricao")));
        }

        return cargo;
    }

    public static void delete(Cargo a) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (a.getPk_cargo() != 0) {
            conn.createStatement().execute("DELETE FROM CARGOS WHERE PK_CARGO =" + a.getPk_cargo());

        } else {
            throw new RuntimeException("Cliente não existe");
        }

    }

    public static void update(Cargo a) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE CARGOS SET NOME = ?, DESCRICAO = ? WHERE PK_CARGO = ?");

        stm.setString(1, a.getNome());
        stm.setString(2, a.getDescricao());
        stm.setInt(3, a.getPk_cargo());

        stm.execute();

    }
}
