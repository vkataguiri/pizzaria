/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sme
 */
public class PainelVisualizarPedido extends JPanel {
    
    private JButton btnAtualizarEstado;
    private JComboBox<String> cbEstado;
    private JTable tabelaVisualizarPedido;
    
    public PainelVisualizarPedido() {
        initComponents();
    }
    
    private void initComponents() {
        btnAtualizarEstado = new JButton("Atualizar Estado");
        JScrollPane jScrollPane6 = new JScrollPane();
        tabelaVisualizarPedido = new JTable();
        cbEstado = new JComboBox<>();
        JLabel lblAlterarEstado = new JLabel("Alterar Estado");

        tabelaVisualizarPedido.setModel(new DefaultTableModel(
            new Object [][] {}, new String [] { "ID Pedido", "Cliente", "Estado", "Valor Total" }
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        jScrollPane6.setViewportView(tabelaVisualizarPedido);

        cbEstado.setModel(new DefaultComboBoxModel<>(new String[] { "Aberto", "A Caminho", "Entregue" }));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAlterarEstado)
                    .addComponent(cbEstado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtualizarEstado, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAlterarEstado)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizarEstado, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }
    
    public JButton getBtnAtualizarEstado() { return btnAtualizarEstado; }
    public JComboBox<String> getCbEstado() { return cbEstado; }
    public JTable getTabelaVisualizarPedido() { return tabelaVisualizarPedido; }
}