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
public class PainelRealizarPedido extends JPanel {

    private JButton btnAddPizza;
    private JCheckBox btnArea;
    private JButton btnCalcular;
    private JCheckBox btnDimensoes;
    private JButton btnFimPedido;
    private ButtonGroup btnGrupo;
    private JButton btnRemovePizza;
    private JButton btnEditarPizza;
    private JComboBox<String> comboBoxFormaPizza;
    private JComboBox<String> comboBoxSabor1;
    private JComboBox<String> comboBoxSabor2Op;
    private JComboBox<String> comboBoxTelCliente;
    private JLabel labelDimArea;
    private JLabel labelPrecoTotal;
    private JLabel maxDimArea;
    private JLabel minDimArea;
    private JTable tabelaPizzasPedido;
    private JTextField textFieldDimArea;
    private JLabel valMaxDimArea;
    private JLabel valMinDimArea;

    public PainelRealizarPedido() {
        initComponents();
    }

    private void initComponents() {
        btnGrupo = new ButtonGroup();
        comboBoxTelCliente = new JComboBox<>();
        comboBoxFormaPizza = new JComboBox<>();
        comboBoxSabor1 = new JComboBox<>();
        JLabel jLabel7 = new JLabel("Telefone do Cliente");
        JLabel jLabel8 = new JLabel("Forma da Pizza");
        comboBoxSabor2Op = new JComboBox<>();
        JLabel jLabel9 = new JLabel("Sabor 1");
        JLabel jLabel10 = new JLabel("Sabor 2 (Opcional)");
        JLabel jLabel11 = new JLabel("Tamanho:");
        btnDimensoes = new JCheckBox("Dimensões");
        btnArea = new JCheckBox("Area Total");
        JScrollPane jScrollPane4 = new JScrollPane();
        tabelaPizzasPedido = new JTable();
        textFieldDimArea = new JTextField();
        labelDimArea = new JLabel("Dimensão/Area:");
        btnCalcular = new JButton("Calcular");
        btnAddPizza = new JButton("Adicionar Pizza");
        btnRemovePizza = new JButton("Remover Pizza");
        btnEditarPizza = new JButton("Editar Pizza Selecionada");
        JSeparator jSeparator3 = new JSeparator();
        JSeparator jSeparator4 = new JSeparator();
        minDimArea = new JLabel("Min:");
        maxDimArea = new JLabel("Max:");
        valMinDimArea = new JLabel("0");
        valMaxDimArea = new JLabel("0");
        labelPrecoTotal = new JLabel("R$ 0,00");
        btnFimPedido = new JButton("Finalizar Pedido");

        comboBoxFormaPizza.setModel(new DefaultComboBoxModel<>(new String[] { "Circulo", "Quadrado", "Triangulo" }));
        btnGrupo.add(btnDimensoes);
        btnGrupo.add(btnArea);
        btnDimensoes.setSelected(true);

        tabelaPizzasPedido.setModel(new DefaultTableModel(
            new Object [][] {}, new String [] { "Forma", "Sabor 1", "Sabor 2", "Área", "Preço Total" }
        ){
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        jScrollPane4.setViewportView(tabelaPizzasPedido);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(comboBoxTelCliente, 0, 240, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(comboBoxFormaPizza, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(comboBoxSabor1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(comboBoxSabor2Op, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDimensoes, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnArea, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDimArea)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(minDimArea)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valMinDimArea, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(maxDimArea)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valMaxDimArea, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPrecoTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3)
                    .addComponent(btnAddPizza, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemovePizza, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarPizza, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(btnFimPedido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldDimArea, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalcular, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTelCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxFormaPizza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxSabor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxSabor2Op, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDimensoes)
                            .addComponent(btnArea))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDimArea)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldDimArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCalcular))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(minDimArea)
                            .addComponent(valMinDimArea)
                            .addComponent(maxDimArea)
                            .addComponent(valMaxDimArea)
                            .addComponent(labelPrecoTotal))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPizza, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemovePizza, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarPizza, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFimPedido, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }

    public JButton getBtnAddPizza() { return btnAddPizza; }
    public JButton getBtnRemovePizza() { return btnRemovePizza; }
    public JButton getBtnEditarPizza() { return btnEditarPizza; }
    public JButton getBtnFimPedido() { return btnFimPedido; }
    public JButton getBtnCalcular() { return btnCalcular; }
    public JCheckBox getBtnArea() { return btnArea; }
    public JCheckBox getBtnDimensoes() { return btnDimensoes; }
    public JComboBox<String> getComboBoxTelCliente() { return comboBoxTelCliente; }
    public JComboBox<String> getComboBoxFormaPizza() { return comboBoxFormaPizza; }
    public JComboBox<String> getComboBoxSabor1() { return comboBoxSabor1; }
    public JComboBox<String> getComboBoxSabor2Op() { return comboBoxSabor2Op; }
    public JTable getTabelaPizzasPedido() { return tabelaPizzasPedido; }
    public JLabel getLabelDimArea() { return labelDimArea; }
    public JLabel getLabelPrecoTotal() { return labelPrecoTotal; }
    public JLabel getValMinDimAreaLabel() { return valMinDimArea; }
    public JLabel getValMaxDimAreaLabel() { return valMaxDimArea; }
    
    public JTextField getTextFieldDimArea() {
        return textFieldDimArea;
    }

    public String getTamanhoPizzaText() {
        return textFieldDimArea.getText();
    }
    
    public String getFormaSelecionada() {
        return (String) comboBoxFormaPizza.getSelectedItem();
    }
    
    public void limparCampos() {
        comboBoxTelCliente.setSelectedIndex(0);
        comboBoxFormaPizza.setSelectedIndex(0);
        comboBoxSabor1.setSelectedItem("");
        comboBoxSabor2Op.setSelectedItem("");
        textFieldDimArea.setText("");
        labelPrecoTotal.setText("R$ 0,00");
        ((DefaultTableModel) tabelaPizzasPedido.getModel()).setRowCount(0);
    }
}