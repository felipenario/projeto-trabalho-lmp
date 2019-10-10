/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felipenario
 */
public class FuncionariosDAO {

    public static int create(Funcionarios f) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO funcionario(fk_cargo, nome, cpf) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, f.getFk_cargo());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());

        stm.execute();

        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();

        f.setPk_funcionario(pkset.getInt(1));
        //configura a chave primaria gerada no objeto telefone
        /*c.setPk_funcionario(pkset.getInt(1));*/
        //configurou a chave estrangeira

        //c.getEndereco().setFk_funcionario(pkset.getInt(1));
        FuncionariosEnderecosDAO.create(f.getEndereco());

        return pkset.getInt(1);
    }

    public static Funcionarios retreave(int pk_funcionario) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM FUNCIONARIOS WHERE PK_FUNCIONARIO =" + pk_funcionario
                );

        stm.setInt(1, pk_funcionario);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        Funcionarios f;

        if (rs.next()) {
            f = new Funcionarios(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("pk_funcionario"),
                    rs.getInt("fk_cargo"));

            return f;
//              pk_funcionario,
//              rs.getString("nome"),
//              rs.getDate("nascimento"),
//              rs.getString("cpf")

        } else {
            throw new RuntimeException("Funcionarios não existem");
        }

    }

    public static ArrayList<Funcionarios> retreaveall(int pk_funcionario) throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM FUNCIONARIOS WHERE PK_FUNCIONARIO =" + pk_funcionario);

        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

        while (rs.next()) {
            funcionarios.add(new Funcionarios(rs.getString("nome"),
                    rs.getString("cpf"),
                    pk_funcionario,
                    rs.getInt("fk_cargo")));
        }

        return funcionarios;
    }

    public static void delete(Funcionarios f) throws SQLException {

        Connection conn = DBConnection.getConnection();
        if (f.getPk_funcionario() != 0) {
            conn.createStatement().execute("DELETE FROM FUNCIONARIOS WHERE PK_FUNCIONARIO =" + f.getPk_funcionario());

        } else {
            throw new RuntimeException("Funcionarios não existem");
        }

    }

    public static void update(Funcionarios f) throws SQLException {

        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "UPDATE FUNCIONARIOS SET NOME = ?, CPF = ?, FK_CARGO = ? WHERE PK_FUNCIONARIO = ?");

        stm.setString(1, f.getNome());
        stm.setString(2, f.getCpf());
        stm.setInt(3, f.getFk_cargo());
        stm.setInt(4, f.getPk_funcionario());

        stm.execute();

    }

}
