/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Venda.Telas;

import br.com.Venda.DAL.ModuloConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import br.com.Venda.Model.vendaBLL;
import br.com.Produto.Model.ProdutoBLL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;



public class TelaVenda extends javax.swing.JFrame {

    int flag = 1;
    float total;
    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    public void pesquisaCadastro() {
        conexao.conector();
        String sql = "select nome as Nome, estatus as Status from clientes where nome like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendNome.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                          
            VendTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actionPerformed() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\calc.exe");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public void actionPerformed2() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\notepad.exe");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    private void PesquisaPegaPedido() {
        conexao.conector();
        String sql = "update venda set nome=?, descricao=?, qtdp=?, valor=?, forma_pg=? where id_v=?"; // string para habilitar os set's
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, ResultPesq.getText());
            pst.setString(2, ProdDesc.getText());
            pst.setString(3, ProdPreco.getText());
            pst.setString(4, ProdQtd.getText());
            pst.setString(5, ProdPag.getText());    //metodo para setar mostrar o que vem do banco
            pst.setString(6, ResultId.getText());
            if ((ResultPesq.getText().isEmpty()) || (ProdDesc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int altera = pst.executeUpdate();

                if (altera > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterado com sucesso!");

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void Bloc() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Windows\\system32\\notepad.exe");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = null;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    private void setarCadastro() {
        int setar = VendTable.getSelectedRow();
        //VendCod.setText(VendTable.getModel().getValueAt(setar, ).toString());
        VendNome.setText(VendTable.getModel().getValueAt(setar, 0).toString());
        //a linha a baixo desabilita o butao adicionar
        //   btnSalvar.setEnabled(false);       
    }

    //Metodo para preencher com os dados do banco da tabela "Produto" o Jtable - VendTbale2 na primeira Aba;
    public void pesquisaProdutos() {
        conexao.conector();
        String sql = "select descricao_p as Descrição, qtd as Qtd, preco as Preço, dataq as Data from produtos where descricao_p like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendProduto.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            VendTable2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela Produtos o JTable "VendTable2" na primeira Aba;
    private void setarProduto() {
        int setar = VendTable2.getSelectedRow();
        VendProduto.setText(VendTable2.getModel().getValueAt(setar, 0).toString());
        VendQtd.setText(VendTable2.getModel().getValueAt(setar, 1).toString());
        VendValor.setText(VendTable2.getModel().getValueAt(setar, 2).toString());
        VendData.setText(VendTable2.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher com os dados do banco da tabela "venda" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaVenda(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void pesquisaCarrinho(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            VendTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados do JTable "ResultTable" na segunda Aba;
    private void setarVenda() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdDesc.setText(ResultTable.getModel().getValueAt(setar, 2).toString());
        ProdQtd.setText(ResultTable.getModel().getValueAt(setar, 3).toString());
        ProdPreco.setText(ResultTable.getModel().getValueAt(setar, 4).toString());
        ProdPag.setText(ResultTable.getModel().getValueAt(setar, 5).toString());

    }

    //Metodo para preencher com os dados do banco da tabela "produtos" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaProduto(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void teste() {
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into venda (nome, descricao, qtdp,valor, forma_pg) select nome, descricao, qtdp,valor, forma_pg from carrinho; ";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);

//            if ((VendTable3.getToolTipText().isEmpty())||(VendValorT.getText().isEmpty())) {
//                JOptionPane.showMessageDialog(null, "Esqueceu de preencher o campo 'Nome' ou 'Produto' ou 'Valor Total'!");
//            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso");
                }
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Por favor Adicione a Venda antes finalizar! ");
        }
    }
    // Metodo para limpar a tabela carrino no banco

    private void excluir() {
        String sql = "truncate carrinho;";
        try {
            pst = conexao.conn.prepareStatement(sql);
            int apagado = pst.executeUpdate();
            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");

        }
    }

// Método para visualizar a tabela 3 da primeira aba
    private void VisualiaCarrinho(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            VendTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela venda o JTable "ResultTable" na segunda Aba;
    private void setarReult() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdDesc.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
        ProdQtd.setText(ResultTable.getModel().getValueAt(setar, 2).toString());
        ProdPreco.setText(ResultTable.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher os campos de texto do formulario (primeira aba) com os dados da tabela Carrinho;
    private void avançar() {
        int setar = VendTable3.getSelectedRow();
        VendNome.setText(VendTable3.getModel().getValueAt(setar, 0).toString());
        VendProduto.setText(VendTable3.getModel().getValueAt(setar, 1).toString());
        VendQtd.setText(VendTable3.getModel().getValueAt(setar, 2).toString());
        VendValor.setText(VendTable3.getModel().getValueAt(setar, 3).toString());
    }
//    

    private void LimparEstoque() {
        ResultId.setText(null);
        ResultPesq.setText(null);
        ResultTable.setVisible(false);
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
        ProdPag.setText(null);
        //BtnProdAlterar.setText(null);
    }

    private void limpaTela() {
        // VendNome.setText("");
        VendTable.setVisible(false);
        VendProduto.setText("");
        VendTable2.setVisible(false);
        VendQtd.setText("");
        VendValorT.setText("");                // Metodo para limapr campos;
        VendValor.setText("");
        VendData.setText("");
    }

    private void limpaTela2() {
        VendNome.setText("");
        VendTable.setVisible(false);
        VendProduto.setText("");
        VendTable2.setVisible(false);
        VendQtd.setText("");
        VendValorT.setText("");                // Metodo para limapr campos;
        VendValor.setText("");
        VendData.setText("");
    }

    private void limpaProdutos() {
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
    }

    /**
     * Creates new form TelaVenda
     */
    public TelaVenda() {
        initComponents();

    }
    vendaBLL bll = new vendaBLL();
    ProdutoBLL bl2 = new ProdutoBLL();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        Vendas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        VendTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        VendNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        VendProduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        VendTable2 = new javax.swing.JTable();
        VendQtd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        VendValor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        VendData = new javax.swing.JTextField();
        btnFinal = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        VendValorT = new javax.swing.JTextField();
        CboCont = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        VendTable3 = new javax.swing.JTable();
        VendAdd = new javax.swing.JButton();
        BtnCalc = new javax.swing.JButton();
        BtnBloco = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        LblBemVindo = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        ResultPesq = new javax.swing.JTextField();
        ProdDEL = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ResultId = new javax.swing.JTextField();
        BtnVendido = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ProdDesc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ProdPreco = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ProdQtd = new javax.swing.JTextField();
        BtnAdic = new javax.swing.JButton();
        ResultEstoque = new javax.swing.JButton();
        BtnApagaP = new javax.swing.JButton();
        ResultNovo = new javax.swing.JButton();
        ProdPag = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        BtnProdAlterar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BtnRelCantina = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(176, 200, 199));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VendTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        VendTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(VendTable);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nome");

        VendNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendNome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendNomeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Produto");

        VendProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendProdutoKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Quantidade");

        VendTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        VendTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(VendTable2);

        VendQtd.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        VendQtd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendQtd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                VendQtdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                VendQtdFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Valor por Item:");

        VendValor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        VendValor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendValorMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Data da Compra");

        VendData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendData.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendData.setEnabled(false);

        btnFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Adicionar.1.png"))); // NOI18N
        btnFinal.setText("Adcionar");
        btnFinal.setToolTipText("Adicionar");
        btnFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(255, 255, 255));
        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Branco.png"))); // NOI18N
        btnDel.setToolTipText("Novo");
        btnDel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Valor Total:");

        VendValorT.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        VendValorT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CboCont.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CboCont.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Débito", "Credito", "Dinheiro", "Fiado" }));
        CboCont.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CboCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboContActionPerformed(evt);
            }
        });

        VendTable3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendTable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VendTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VendTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VendTable3);
        if (VendTable3.getColumnModel().getColumnCount() > 0) {
            VendTable3.getColumnModel().getColumn(0).setResizable(false);
            VendTable3.getColumnModel().getColumn(1).setResizable(false);
            VendTable3.getColumnModel().getColumn(2).setResizable(false);
            VendTable3.getColumnModel().getColumn(3).setResizable(false);
        }

        VendAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        VendAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Finalizar1.jpg"))); // NOI18N
        VendAdd.setToolTipText("Finalizar Venda");
        VendAdd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VendAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendAddMouseClicked(evt);
            }
        });
        VendAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendAddActionPerformed(evt);
            }
        });

        BtnCalc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Calculadora.png"))); // NOI18N
        BtnCalc.setToolTipText("Calculadora");
        BtnCalc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcActionPerformed(evt);
            }
        });

        BtnBloco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnBloco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/paragraph-clipart-lines-represent-text.png"))); // NOI18N
        BtnBloco.setToolTipText("Bloco de Notas");
        BtnBloco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnBloco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBlocoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/SibreC.jpg"))); // NOI18N

        LblBemVindo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LblBemVindo.setText("Bem Vindo");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Seja Bem Vindo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(111, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(VendProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(28, 28, 28)
                                .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(VendAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(CboCont, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel9)))
                                        .addGap(32, 32, 32)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(BtnCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(BtnBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(247, 247, 247)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LblBemVindo)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(LblBemVindo)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VendProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CboCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnCalc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(VendAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(286, 286, 286))
        );

        Vendas.addTab("Vendas", jPanel1);

        jPanel2.setBackground(new java.awt.Color(184, 163, 163));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(960, 700));
        jPanel2.setPreferredSize(new java.awt.Dimension(964, 700));

        ResultTable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ResultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ResultTable);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Pesquisa");

        ResultPesq.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ResultPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultPesqActionPerformed(evt);
            }
        });
        ResultPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResultPesqKeyReleased(evt);
            }
        });

        ProdDEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Cubiertos.png"))); // NOI18N
        ProdDEL.setText("Apaga Venda");
        ProdDEL.setToolTipText("Apaga Venda");
        ProdDEL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProdDEL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProdDEL.setEnabled(false);
        ProdDEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdDELActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Id");

        ResultId.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        BtnVendido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnVendido.setText("Vendidos");
        BtnVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVendidoActionPerformed(evt);
            }
        });
        BtnVendido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnVendidoKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Descrição:");

        ProdDesc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Preço por unidade:");

        ProdPreco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Quantidade Todal");

        ProdQtd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        BtnAdic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Adicionar.1.png"))); // NOI18N
        BtnAdic.setText("Adicionar Produto");
        BtnAdic.setToolTipText("Adiciona Produto");
        BtnAdic.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAdic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnAdic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicActionPerformed(evt);
            }
        });

        ResultEstoque.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResultEstoque.setText("Estoque");
        ResultEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultEstoqueActionPerformed(evt);
            }
        });

        BtnApagaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/images444.png"))); // NOI18N
        BtnApagaP.setText("Apaga Produto");
        BtnApagaP.setToolTipText("Apga produto");
        BtnApagaP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnApagaP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnApagaP.setEnabled(false);
        BtnApagaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnApagaPActionPerformed(evt);
            }
        });

        ResultNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ResultNovo.setText("Novo");
        ResultNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultNovoActionPerformed(evt);
            }
        });

        ProdPag.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Forma de Pagamento");

        BtnProdAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnProdAlterar.setText("Altera Vendido");
        BtnProdAlterar.setToolTipText("Alterar vendidos");
        BtnProdAlterar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnProdAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnProdAlterar.setEnabled(false);
        BtnProdAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProdAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BtnVendido)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(ResultEstoque)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ProdPreco))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(ProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ProdPag)))
                            .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResultNovo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnProdAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnVendido)
                    .addComponent(ResultEstoque)
                    .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResultNovo))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnProdAlterar))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(330, Short.MAX_VALUE))
        );

        Vendas.addTab("Resultados", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 152, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnRelCantina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/Relatório.png"))); // NOI18N
        BtnRelCantina.setText("Relatório");
        BtnRelCantina.setToolTipText("Impressão de Relatório");
        BtnRelCantina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnRelCantina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRelCantinaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Vani", 0, 36)); // NOI18N
        jLabel1.setText("Impressão de Relatorios da Cantina");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Imagens/down-arrow.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(BtnRelCantina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(121, 121, 121)
                .addComponent(BtnRelCantina, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        Vendas.addTab("Relatório da Cantina", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vendas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vendas, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ResultNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultNovoActionPerformed
        ProdDesc.setEnabled(true);
        ProdPreco.setEnabled(true);
        ProdQtd.setEnabled(true);
        ResultEstoque.setEnabled(true);
        ProdDEL.setEnabled(false);
        BtnAdic.setEnabled(true);
        BtnVendido.setEnabled(true);
        BtnApagaP.setEnabled(false);
        LimparEstoque();
    }//GEN-LAST:event_ResultNovoActionPerformed

    private void BtnApagaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnApagaPActionPerformed
        int id_pro = Integer.parseInt(ResultId.getText());
        String descrica = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText(); // preço por unidade da aba Result "valor Item".

        bl2.ExcluirProduto(id_pro, descrica, preco, preco);
        ResultTable.setVisible(false);
    }//GEN-LAST:event_BtnApagaPActionPerformed

    private void ResultEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultEstoqueActionPerformed
        pesquisaVenda("select id_pro as Id, descricao_p as Descrição, qtd as Quantidade, preco as Preço, dataq as Dati from produtos where descricao_p like '%" + ResultPesq.getText() + "%'order by dataq desc");
        ProdDesc.setEnabled(false);
        ProdPreco.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdDEL.setEnabled(false);
        BtnApagaP.setEnabled(true);
        ResultTable.setVisible(true);
        ProdPag.setEnabled(false);
        BtnProdAlterar.setEnabled(false);
        BtnAdic.setEnabled(false);
        flag = 2;
    }//GEN-LAST:event_ResultEstoqueActionPerformed
    //select * from produtos where descricao_p like '%" + ResultPesq.getText() + "%'
    private void BtnAdicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicActionPerformed
        String descricao = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText();
        bl2.salvaProduto(descricao, qtd, preco);
        LimparEstoque();
    }//GEN-LAST:event_BtnAdicActionPerformed

    private void BtnVendidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnVendidoKeyReleased

    }//GEN-LAST:event_BtnVendidoKeyReleased

    private void BtnVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVendidoActionPerformed
        pesquisaProduto("select id_v as Id, nome as Nome, descricao as Descrição, qtdp as Quantidade, valor as Valor, forma_pg as Forma_Pg, dataq as Dati from venda where nome like '%" + ResultPesq.getText() + "%'order by dataq desc");
        ProdDEL.setEnabled(true);
        BtnAdic.setEnabled(false);
        BtnApagaP.setEnabled(false);
        ProdDesc.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdPreco.setEnabled(false);
        ResultTable.setVisible(true);
        ProdPag.setEnabled(true);
        BtnProdAlterar.setEnabled(true);
        BtnAdic.setEnabled(true);
        flag = 1;
    }//GEN-LAST:event_BtnVendidoActionPerformed
    //select id_v, nome, descricao, qtdp, valor, forma_pg, dataq from venda where nome like '%" + ResultPesq.getText() + "%'"
    private void ProdDELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdDELActionPerformed
        int id = Integer.parseInt(ResultId.getText());
        String nome = this.VendNome.getText();
        String descrica = this.VendProduto.getText();
        String qtd = this.VendQtd.getText();
        String total = this.VendValorT.getText();
        String cboCont = this.CboCont.getSelectedItem().toString();
        bll.ExcluirVenda(id, nome, descrica, qtd, total, cboCont);
        LimparEstoque();
        //ResultVend.setEnabled(false);
    }//GEN-LAST:event_ProdDELActionPerformed

    private void ResultPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResultPesqKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultPesqKeyReleased

    private void ResultPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultPesqActionPerformed

    private void ResultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTableMouseClicked
        if (flag == 2) {
            setarReult();
        } else {
            setarVenda();
        }
        ProdDesc.setEnabled(true);
        ProdQtd.setEnabled(true);
        ProdPreco.setEnabled(true);
    }//GEN-LAST:event_ResultTableMouseClicked

    private void VendAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendAddActionPerformed
        teste();
        excluir();
        VendNome.setText("");
        CboCont.setToolTipText("");
        VendTable3.setVisible(false);

    }//GEN-LAST:event_VendAddActionPerformed

    private void VendAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VendAddMouseClicked

    private void VendTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VendTable3MouseClicked

    private void CboContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboContActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        limpaTela2();
        excluir();
        VendTable3.setVisible(false);
    }//GEN-LAST:event_btnDelActionPerformed

//int total = Integer.parseInt(VendValorT.getText());
    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        String nome = this.VendNome.getText();
        String descrica = this.VendProduto.getText();
        String qtd = VendQtd.getText();
        String total = this.VendValorT.getText();
        String cboCont = this.CboCont.getSelectedItem().toString();
        bll.Salvar_venda(nome, descrica, qtd, total, cboCont);
        VisualiaCarrinho("select nome as Nome, descricao as Descrição, valor as Valor, qtdp as Qtd, forma_pg as Forma_pg from carrinho; ");
        limpaTela();
    }//GEN-LAST:event_btnFinalActionPerformed

    private void VendValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendValorMouseClicked

    }//GEN-LAST:event_VendValorMouseClicked

    private void VendQtdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendQtdFocusGained
        float valorTotal;
        valorTotal = Float.parseFloat(VendValor.getText()) * Integer.parseInt(VendQtd.getText());
        VendValorT.setText(String.valueOf(valorTotal));

    }//GEN-LAST:event_VendQtdFocusGained

    private void VendTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable2MouseClicked
        setarProduto();
        VendQtd.setText("1");
        VendTable2.setVisible(false);
        VendTable3.setVisible(true);
    }//GEN-LAST:event_VendTable2MouseClicked

    private void VendProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendProdutoKeyReleased
        pesquisaProdutos();
        VendTable2.setVisible(true);
    }//GEN-LAST:event_VendProdutoKeyReleased

    private void VendNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendNomeKeyReleased
        pesquisaCadastro();
        VendTable.setVisible(true);
    }//GEN-LAST:event_VendNomeKeyReleased

    private void VendTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTableMouseClicked
        setarCadastro();
        VendTable.setVisible(false);
    }//GEN-LAST:event_VendTableMouseClicked

    private void BtnRelCantinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRelCantinaActionPerformed
        // Essa função é para gerar relatório        
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desse deste relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            conexao.conector();
            //imprimindo o relatório com o framework JasperReports
            try {
                conexao.executaSQL("select * from venda order by dataq desc;");
               JRResultSetDataSource relatResult = new JRResultSetDataSource(conexao.rs);
                JasperPrint print = JasperFillManager.fillReport("C:\\Report\\New folder\\MyReports\\Cosinha1.jasper", new HashMap(), relatResult);
                JasperViewer jv = new JasperViewer(print, false); //Cria instancia para impressão
                jv.setVisible(true); //chama o relatório para visualização  
                jv.toFront(); // set o formuloari a frente da aplicação// src/Cantina1.jasper
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }//GEN-LAST:event_BtnRelCantinaActionPerformed

    private void BtnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcActionPerformed
        actionPerformed();
    }//GEN-LAST:event_BtnCalcActionPerformed

    private void BtnBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBlocoActionPerformed
        actionPerformed2();
    }//GEN-LAST:event_BtnBlocoActionPerformed

    private void BtnProdAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProdAlterarActionPerformed
        PesquisaPegaPedido();
        LimparEstoque();
    }//GEN-LAST:event_BtnProdAlterarActionPerformed

    private void VendQtdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendQtdFocusLost
        float valorTotal;
        valorTotal = Float.parseFloat(VendValor.getText()) * Integer.parseInt(VendQtd.getText());
        VendValorT.setText(String.valueOf(valorTotal));
    }//GEN-LAST:event_VendQtdFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdic;
    public static javax.swing.JButton BtnApagaP;
    private javax.swing.JButton BtnBloco;
    private javax.swing.JButton BtnCalc;
    public static javax.swing.JButton BtnProdAlterar;
    private javax.swing.JButton BtnRelCantina;
    private javax.swing.JButton BtnVendido;
    private javax.swing.JComboBox<String> CboCont;
    public static javax.swing.JLabel LblBemVindo;
    public static javax.swing.JButton ProdDEL;
    private javax.swing.JTextField ProdDesc;
    private javax.swing.JTextField ProdPag;
    private javax.swing.JTextField ProdPreco;
    private javax.swing.JTextField ProdQtd;
    private javax.swing.JButton ResultEstoque;
    private javax.swing.JTextField ResultId;
    private javax.swing.JButton ResultNovo;
    private javax.swing.JTextField ResultPesq;
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton VendAdd;
    private javax.swing.JTextField VendData;
    private javax.swing.JTextField VendNome;
    private javax.swing.JTextField VendProduto;
    private javax.swing.JTextField VendQtd;
    private javax.swing.JTable VendTable;
    private javax.swing.JTable VendTable2;
    private javax.swing.JTable VendTable3;
    private javax.swing.JTextField VendValor;
    private javax.swing.JTextField VendValorT;
    private javax.swing.JTabbedPane Vendas;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
