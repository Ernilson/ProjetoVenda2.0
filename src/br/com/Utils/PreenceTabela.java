/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import java.util.ArrayList;
import br.com.Venda.DAL.ModuloConexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author T69779848134
 */
//public class PreenceTabela {
//
//    ModuloConexao conexao = new ModuloConexao();
//
//    public void preencheTabela() throws SQLException {
//        ArrayList dados = new ArrayList();
//
//        String[] Colunas = new String[]{"Nome", "descricao", "qtd_venda", "Valor_venda"};
//        conexao.conector();
//        conexao.pst.execute("select * from venda");
//        try {
//            conexao.rs.first();
//            do{
//                dados.add(new Object[]{conexao.rs.getString("nome"),conexao.rs.getString("descricao"),conexao.rs.getString("qtd_venda"),conexao.rs.getString("Valor_venda"))
//            }while(conexao.rs.next());
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Erro");
//        }
//        ModeloTabela modelo = ModeloTabela(dados, Colunas);
//        VendTable.getColumnModel().getColumn(0).setPreferredWidth(250);
//        VendTable.getColumnModel().getColumn(0).setResizable(250);
//           
//    }
//    
//}

