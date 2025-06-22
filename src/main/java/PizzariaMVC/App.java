/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC;

import PizzariaMVC.controller.ClienteController;
import PizzariaMVC.controller.PedidoController;
import PizzariaMVC.controller.SaborController;
import PizzariaMVC.model.dao.ClienteDAO;
import PizzariaMVC.model.dao.PedidoDAO;
import PizzariaMVC.model.dao.SaborDAO;
import PizzariaMVC.model.dao.TipoSaborDAO;
import PizzariaMVC.views.TelaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author amene
 */
public class App {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Falha ao carregar o Look and Feel Nimbus: " + ex);
        }

        SwingUtilities.invokeLater(() -> {
            try {
            
                ClienteDAO clienteDAO = new ClienteDAO();
                SaborDAO saborDAO = new SaborDAO();
                TipoSaborDAO tipoSaborDAO = new TipoSaborDAO();
                PedidoDAO pedidoDAO = new PedidoDAO();

                ClienteController clienteController = new ClienteController(clienteDAO, pedidoDAO);
                SaborController saborController = new SaborController(saborDAO, tipoSaborDAO);
                PedidoController pedidoController = new PedidoController(pedidoDAO, saborDAO, tipoSaborDAO);
                
                TelaPrincipal tela = new TelaPrincipal(clienteController, saborController, pedidoController);
                
                tela.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erro crítico ao iniciar a aplicação: " + e.getMessage(), 
                    "Erro Fatal", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}