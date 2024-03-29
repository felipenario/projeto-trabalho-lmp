/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Funcionarios_Enderecos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author felipenario
 */
public class FuncionariosEnderecosDAO {

    public static int create(Funcionarios_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "INSERT INTO Funcionarios_Enderecos(fk_funcionario, logadouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, e.getFk_funcionario());
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

    public static Funcionarios_Enderecos retreave(int pk_endereco) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm
                = conn.prepareStatement(
                        "SELECT * FROM FUNCIONARIOS_ENDERECOS WHERE PK_ENDERECO=?"
                );

        stm.setInt(1, pk_endereco);

        stm.executeQuery();

        ResultSet rs = stm.getResultSet();

        if (rs.next()) {
            return new Funcionarios_Enderecos(
                    rs.getString("logadouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_funcionario")
            );
        } else {
            throw new RuntimeException("Endereço não existe");
        }
    }

    public static Funcionarios_Enderecos retreaveall() throws SQLException {
        Connection conn = DBConnection.getConnection();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM FUNCIONARIOS_ENDERECOS ");
        Funcionarios_Enderecos e = null;
        if (rs.next()) {
            e = new Funcionarios_Enderecos(
                    rs.getString("logadouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_funcionario")
            );
        }
        return e;
    }

    public static void delete(Funcionarios_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        if (e.getPk_endereco() != 0) {
            conn.createStatement().
                    execute("DELETE FROM FUNCIONARIOS_ENDERECOS WHERE PK_ENDERECO=" + e.getPk_endereco());
        } else {
            throw new RuntimeException("Endereco nao existe no BD");
        }

    }

    public static void update(Funcionarios_Enderecos e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE ENDERECO SET LOGADOURO=?, BAIRRO=?, CIDADE=?, ESTADO=?,PAIS = ?, CEP = ?, PK_ENDERECO = ? WHERE FK_FUNCIONARIO = ?");

        //configura as interrogações pela ordem
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getBairro());
        stm.setString(3, e.getCidade());
        stm.setString(4, e.getEstado());
        stm.setString(5, e.getPais());
        stm.setString(6, e.getCep());
        stm.setInt(7, e.getPk_endereco());
        stm.setInt(8, e.getFk_funcionario());

        stm.execute();
    }

}
