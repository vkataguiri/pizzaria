/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.views;

import javax.swing.*;

/**
 *
 * @author vinic
 */
public class PainelMenuPrincipal extends JPanel {

    public PainelMenuPrincipal() {
        initComponents();
    }

    private void initComponents() {
        JLabel titulo = new JLabel();
        JScrollPane jScrollPane5 = new JScrollPane();
        JTextArea grr = new JTextArea();
        JScrollPane jScrollPane7 = new JScrollPane();
        JTextArea desc = new JTextArea();
        JLabel jLabel4 = new JLabel();

        titulo.setFont(new java.awt.Font("Eras Medium ITC", 1, 48));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setText("Poli Pizza");

        grr.setEditable(false);
        grr.setColumns(20);
        grr.setRows(5);
        grr.setText("Guilherme Arthur Nunes Menegari - GRR20231010\nLeonardo Chicora Neto - GRR20231025\nThalita Santos - GRR20231007\nVinícius Kataguiri Perrot e Silva - GRR20235364");
        jScrollPane5.setViewportView(grr);

        desc.setEditable(false);
        desc.setColumns(20);
        desc.setLineWrap(true);
        desc.setRows(5);
        desc.setText("Trabalho apresentado à disciplina de Linguagem de Programação Orientada a Objetos II, Setor de Educação Profissional e Tecnológica,  Universidade \nFederal do Paraná, como projeto  final da disciplina.\n\nProf. Dr. Rafael Romualdo Wandresen");
        jScrollPane7.setViewportView(desc);

        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/logoPizza.png")));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane5, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
}