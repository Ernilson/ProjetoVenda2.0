package br.com.Venda.DAL;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author t69779848134
 */
public class ModuloC {
    public static Connection conector() {
        java.sql.Connection conexao = null;

//a linha abaixo invoca o driver
        String driver = "com.mysql.jdbc.Driver";

// Armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3303/sibre10";
        String user = "root";
        String password = "";

// Estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void fecharConexao() {
        java.sql.Connection conexao = null;
        try {
            conexao.close();
        } catch (Exception e) {
        }
    }
}
