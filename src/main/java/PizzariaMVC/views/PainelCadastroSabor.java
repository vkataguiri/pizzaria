/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thali
 */
public class PainelCadastroSabor extends JPanel {
    
    private JButton cadastrarSaborButton;
    private JButton editarPrecoButton;
    private JButton editarSaborButton;
    private JButton excluirSaborButton;
    private JTable tabelaPrecos;
    private JTable tabelaSabores;
    private JTextField nomeSabor;
    private JComboBox<String> tipoSabor;
    private JTextField pesquisarSabor;
    private JToggleButton btnPesquisarSabor;

    public PainelCadastroSabor() {
        initComponents();
    }

    private void initComponents() {
        JLabel jLabel5 = new JLabel("Nome do sabor");
        JLabel jLabel6 = new JLabel("Tipo do sabor");
        tipoSabor = new JComboBox<>();
        cadastrarSaborButton = new JButton("Cadastrar sabor");
        editarSaborButton = new JButton("Editar");
        excluirSaborButton = new JButton("Excluir");
        pesquisarSabor = new JTextField();
        JScrollPane jScrollPane2 = new JScrollPane();
        tabelaSabores = new JTable();
        JScrollPane jScrollPane3 = new JScrollPane();
        tabelaPrecos = new JTable();
        editarPrecoButton = new JButton("Editar preço");
        JSeparator jSeparator2 = new JSeparator();
        nomeSabor = new JTextField();
        btnPesquisarSabor = new JToggleButton("Pesquisar");

        tipoSabor.setModel(new DefaultComboBoxModel<>(new String[] { "Simples", "Especial", "Premium" }));
        
        tabelaSabores.setModel(new DefaultTableModel(
            new Object [][] {}, new String [] { "Nome", "Tipo" }
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        jScrollPane2.setViewportView(tabelaSabores);

        tabelaPrecos.setModel(new DefaultTableModel(
            new Object [][] {}, new String [] { "Tipo", "Preço" }
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        jScrollPane3.setViewportView(tabelaPrecos);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(nomeSabor)
                    .addComponent(jLabel6)
                    .addComponent(tipoSabor, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cadastrarSaborButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(editarPrecoButton, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesquisarSabor, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarSabor, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(editarSaborButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(excluirSaborButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisarSabor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarSabor))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeSabor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoSabor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cadastrarSaborButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarPrecoButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarSaborButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirSaborButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }
    
    public JButton getCadastrarSaborButton() { return cadastrarSaborButton; }
    public JButton getEditarSaborButton() { return editarSaborButton; }
    public JButton getExcluirSaborButton() { return excluirSaborButton; }
    public JButton getEditarPrecoButton() { return editarPrecoButton; }
    public JToggleButton getBtnPesquisarSabor() { return btnPesquisarSabor; }
    public JTable getTabelaSabores() { return tabelaSabores; }
    public JTable getTabelaPrecos() { return tabelaPrecos; }
    public JComboBox<String> getTipoSaborComboBox() { return tipoSabor; }
    public JTextField getNomeSaborField() { return nomeSabor; }
    public JTextField getPesquisarSaborField() { return pesquisarSabor; }
    public String getNomeSabor() { return nomeSabor.getText(); }
    public String getTipoSaborSelecionado() { return (String) tipoSabor.getSelectedItem(); }
    public void setCadastrarButtonText(String text) { cadastrarSaborButton.setText(text); }
    public void limparCampos() { nomeSabor.setText(""); tipoSabor.setSelectedIndex(0); }
}