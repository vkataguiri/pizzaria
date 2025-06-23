/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author amene
 */
public class PainelCadastroCliente extends JPanel {
    
    private JButton btnEditarCliente;
    private JButton btnExcluirCliente;
    private JButton btnPesquisarCliente;
    private JButton btnSalvarCliente;
    private JTable tabelaCadastroCliente;
    private JTextField textNomeCliente;
    private JTextField textPesquisarCliente;
    private JTextField textSobrenomeCliente;
    private JTextField textTelefoneCliente;

    public PainelCadastroCliente() {
        initComponents();
    }
    
    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        tabelaCadastroCliente = new JTable();
        textNomeCliente = new JTextField();
        textTelefoneCliente = new JTextField();
        textPesquisarCliente = new JTextField();
        JLabel jLabel1 = new JLabel("Nome");
        JLabel jLabel2 = new JLabel("Sobrenome");
        JLabel jLabel3 = new JLabel("Telefone");
        btnSalvarCliente = new JButton("Salvar");
        btnPesquisarCliente = new JButton("Pesquisar");
        btnEditarCliente = new JButton("Editar");
        btnExcluirCliente = new JButton("Excluir");
        textSobrenomeCliente = new JTextField();

        tabelaCadastroCliente.setModel(new DefaultTableModel(
            new Object [][] {}, new String [] { "Nome", "Sobrenome", "Telefone" }
        ) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        jScrollPane1.setViewportView(tabelaCadastroCliente);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(textNomeCliente)
                    .addComponent(textSobrenomeCliente)
                    .addComponent(textTelefoneCliente)
                    .addComponent(btnSalvarCliente, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textPesquisarCliente, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCliente, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditarCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluirCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textPesquisarCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCliente))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSobrenomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTelefoneCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnSalvarCliente, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarCliente, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluirCliente, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
    
    public JButton getBtnSalvar() { return btnSalvarCliente; }
    public JButton getBtnPesquisar() { return btnPesquisarCliente; }
    public JButton getBtnEditar() { return btnEditarCliente; }
    public JButton getBtnExcluir() { return btnExcluirCliente; }
    public JTable getTabelaCadastroCliente() { return tabelaCadastroCliente; }
    public String getNome() { return textNomeCliente.getText(); }
    public String getSobrenome() { return textSobrenomeCliente.getText(); }
    public String getTelefone() { return textTelefoneCliente.getText(); }
    public String getPesquisaText() { return textPesquisarCliente.getText(); }

    public void setNome(String nome) { textNomeCliente.setText(nome); }
    public void setSobrenome(String sobrenome) { textSobrenomeCliente.setText(sobrenome); }
    public void setTelefone(String telefone) { textTelefoneCliente.setText(telefone); }
    
    public void limparCampos() {
        textNomeCliente.setText("");
        textSobrenomeCliente.setText("");
        textTelefoneCliente.setText("");
        textPesquisarCliente.setText("");
    }
}