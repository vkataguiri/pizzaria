/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.views;

import PizzariaMVC.controller.ClienteController;
import PizzariaMVC.controller.PedidoController;
import PizzariaMVC.controller.SaborController;
import PizzariaMVC.model.Cliente;
import PizzariaMVC.model.Pedido;
import PizzariaMVC.model.Pizza;
import PizzariaMVC.model.Sabor;
import PizzariaMVC.model.TipoSabor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinic
 */
public class TelaPrincipal extends JFrame {

    private final ClienteController clienteController;
    private final SaborController saborController;
    private final PedidoController pedidoController;

    private final PainelMenuPrincipal painelMenuPrincipal;
    private final PainelCadastroCliente painelCadastroCliente;
    private final PainelCadastroSabor painelCadastroSabor;
    private final PainelRealizarPedido painelRealizarPedido;
    private final PainelVisualizarPedido painelVisualizarPedido;

    private Pedido pedidoEmAndamento;
    private String telefoneClienteEmEdicao;
    private Sabor saborEmEdicao;
    private boolean modoInsercaoPorDimensao = true;
    private int indicePizzaEmEdicao = -1;

    public TelaPrincipal(ClienteController clienteController, SaborController saborController, PedidoController pedidoController) {
        this.clienteController = clienteController;
        this.saborController = saborController;
        this.pedidoController = pedidoController;

        this.painelMenuPrincipal = new PainelMenuPrincipal();
        this.painelCadastroCliente = new PainelCadastroCliente();
        this.painelCadastroSabor = new PainelCadastroSabor();
        this.painelRealizarPedido = new PainelRealizarPedido();
        this.painelVisualizarPedido = new PainelVisualizarPedido();
        
        initComponents();
        conectarListeners();
        carregarDadosIniciais();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Poli Pizza - Arquitetura MVC");
        JTabbedPane jTabbedPane1 = new JTabbedPane();
        jTabbedPane1.addTab("Menu Principal", painelMenuPrincipal);
        jTabbedPane1.addTab("Cadastrar Cliente", painelCadastroCliente);
        jTabbedPane1.addTab("Cadastrar Sabor", painelCadastroSabor);
        jTabbedPane1.addTab("Realizar Pedido", painelRealizarPedido);
        jTabbedPane1.addTab("Visualizar Pedidos", painelVisualizarPedido);
        getContentPane().add(jTabbedPane1);
        pack();
        setSize(850, 750);
        setLocationRelativeTo(null);
    }
    
    private void conectarListeners() {

        painelCadastroCliente.getBtnSalvar().addActionListener(e -> salvarOuAtualizarCliente());
        painelCadastroCliente.getBtnEditar().addActionListener(e -> prepararEdicaoCliente());
        painelCadastroCliente.getBtnExcluir().addActionListener(e -> excluirCliente());
        painelCadastroCliente.getBtnPesquisar().addActionListener(e -> pesquisarCliente());
        
  
        painelCadastroSabor.getCadastrarSaborButton().addActionListener(e -> salvarOuAtualizarSabor());
        painelCadastroSabor.getEditarSaborButton().addActionListener(e -> prepararEdicaoSabor());
        painelCadastroSabor.getExcluirSaborButton().addActionListener(e -> excluirSabor());
        painelCadastroSabor.getEditarPrecoButton().addActionListener(e -> editarPreco());
        painelCadastroSabor.getBtnPesquisarSabor().addActionListener(e -> pesquisarSabor());

        
        painelRealizarPedido.getComboBoxTelCliente().addActionListener(e -> iniciarPedido());
        painelRealizarPedido.getBtnAddPizza().addActionListener(e -> adicionarOuAtualizarPizza());
        painelRealizarPedido.getBtnEditarPizza().addActionListener(e -> prepararEdicaoPizza());
        painelRealizarPedido.getBtnRemovePizza().addActionListener(e -> removerPizzaDoPedido());
        painelRealizarPedido.getBtnFimPedido().addActionListener(e -> finalizarPedido());
        painelRealizarPedido.getBtnCalcular().addActionListener(e -> preCalcularPrecoPizza());
        painelRealizarPedido.getBtnDimensoes().addActionListener(e -> setModoInsercao(true));
        painelRealizarPedido.getBtnArea().addActionListener(e -> setModoInsercao(false));
        painelRealizarPedido.getComboBoxFormaPizza().addActionListener(e -> atualizarLabelsDeDimensao());
        
        painelVisualizarPedido.getBtnAtualizarEstado().addActionListener(e -> atualizarEstadoPedido());
    }


    private void salvarOuAtualizarCliente() {
        String nome = painelCadastroCliente.getNome();
        String sobrenome = painelCadastroCliente.getSobrenome();
        String telefone = painelCadastroCliente.getTelefone();
        try {
            if (telefoneClienteEmEdicao == null) {
                clienteController.salvarCliente(nome, sobrenome, telefone);
                JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
            } else {
                clienteController.atualizarCliente(telefoneClienteEmEdicao, nome, sobrenome, telefone);
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            }
            limparModoEdicaoCliente();
            atualizarTabelaClientes();
            popularComboBoxes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepararEdicaoCliente() {
        int selectedRow = painelCadastroCliente.getTabelaCadastroCliente().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        telefoneClienteEmEdicao = (String) painelCadastroCliente.getTabelaCadastroCliente().getValueAt(selectedRow, 2);
        String nome = (String) painelCadastroCliente.getTabelaCadastroCliente().getValueAt(selectedRow, 0);
        String sobrenome = (String) painelCadastroCliente.getTabelaCadastroCliente().getValueAt(selectedRow, 1);
        
        painelCadastroCliente.setNome(nome);
        painelCadastroCliente.setSobrenome(sobrenome);
        painelCadastroCliente.setTelefone(telefoneClienteEmEdicao);
        
        painelCadastroCliente.getBtnSalvar().setText("Atualizar");
    }

    private void limparModoEdicaoCliente() {
        telefoneClienteEmEdicao = null;
        painelCadastroCliente.limparCampos();
        painelCadastroCliente.getBtnSalvar().setText("Salvar");
    }

    private void excluirCliente() {
        int selectedRow = painelCadastroCliente.getTabelaCadastroCliente().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String telefone = (String) painelCadastroCliente.getTabelaCadastroCliente().getValueAt(selectedRow, 2);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o cliente com telefone " + telefone + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                clienteController.removerCliente(telefone);
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
                atualizarTabelaClientes();
                popularComboBoxes();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pesquisarCliente() {
        String termo = painelCadastroCliente.getPesquisaText().toLowerCase();
        JTable tabela = painelCadastroCliente.getTabelaCadastroCliente();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        tabela.clearSelection();
        if (termo.isEmpty()) return;

        List<Integer> linhasEncontradas = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().toLowerCase().contains(termo) ||
                model.getValueAt(i, 1).toString().toLowerCase().contains(termo) ||
                model.getValueAt(i, 2).toString().toLowerCase().contains(termo)) {
                linhasEncontradas.add(i);
            }
        }
        
        if(linhasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado.", "Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (int linha : linhasEncontradas) {
                tabela.addRowSelectionInterval(linha, linha);
            }
        }
    }

    private void salvarOuAtualizarSabor() {
        String nome = painelCadastroSabor.getNomeSabor();
        String tipo = painelCadastroSabor.getTipoSaborSelecionado();
        try {
            if (saborEmEdicao == null) {
                saborController.salvarSabor(nome, tipo);
                JOptionPane.showMessageDialog(this, "Sabor salvo com sucesso!");
            } else {
                saborEmEdicao.setNome(nome);
                saborEmEdicao.setTipo(tipo);
                saborController.atualizarSabor(saborEmEdicao);
                JOptionPane.showMessageDialog(this, "Sabor atualizado com sucesso!");
            }
            limparModoEdicaoSabor();
            atualizarTabelaSabores();
            popularComboBoxes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepararEdicaoSabor() {
        int selectedRow = painelCadastroSabor.getTabelaSabores().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um sabor na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nomeSabor = (String) painelCadastroSabor.getTabelaSabores().getValueAt(selectedRow, 0);
        try {
            saborEmEdicao = saborController.buscarPorNome(nomeSabor);
            if (saborEmEdicao != null) {
                painelCadastroSabor.getNomeSaborField().setText(saborEmEdicao.getNome());
                painelCadastroSabor.getTipoSaborComboBox().setSelectedItem(saborEmEdicao.getTipo());
                painelCadastroSabor.setCadastrarButtonText("Atualizar Sabor");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados do sabor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparModoEdicaoSabor() {
        saborEmEdicao = null;
        painelCadastroSabor.limparCampos();
        painelCadastroSabor.setCadastrarButtonText("Cadastrar sabor");
    }

    private void excluirSabor() {
        int selectedRow = painelCadastroSabor.getTabelaSabores().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um sabor na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nomeSabor = (String) painelCadastroSabor.getTabelaSabores().getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o sabor '" + nomeSabor + "'?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                saborController.removerSabor(nomeSabor);
                JOptionPane.showMessageDialog(this, "Sabor removido com sucesso!");
                atualizarTabelaSabores();
                popularComboBoxes();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover sabor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editarPreco() {
        int selectedRow = painelCadastroSabor.getTabelaPrecos().getSelectedRow();
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um tipo de preço na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tipo = (String) painelCadastroSabor.getTabelaPrecos().getValueAt(selectedRow, 0);
        String novoPrecoStr = JOptionPane.showInputDialog(this, "Digite o novo preço para o tipo '" + tipo + "':");
        
        if(novoPrecoStr != null && !novoPrecoStr.trim().isEmpty()){
            try {
                double novoPreco = Double.parseDouble(novoPrecoStr);
                saborController.atualizarPrecoTipo(tipo, novoPreco);
                JOptionPane.showMessageDialog(this, "Preço atualizado com sucesso!");
                atualizarTabelaPrecos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, digite um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar preço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pesquisarSabor() {
        String termo = painelCadastroSabor.getPesquisarSaborField().getText().toLowerCase();
        JTable tabela = painelCadastroSabor.getTabelaSabores();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        tabela.clearSelection();
        if (termo.isEmpty()) return;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().toLowerCase().contains(termo)) {
                tabela.addRowSelectionInterval(i, i);
            }
        }
        if (tabela.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum sabor encontrado.", "Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void iniciarPedido() {
        String telefone = (String) painelRealizarPedido.getComboBoxTelCliente().getSelectedItem();
        if (telefone == null || telefone.trim().isEmpty()) {
            pedidoEmAndamento = null;
            atualizarTabelaPizzasDoPedido(); 
            return;
        }
        try {
            Cliente cliente = clienteController.buscarClientePorTelefone(telefone);
            if (cliente != null) {
                pedidoEmAndamento = pedidoController.iniciarNovoPedido(cliente);
                atualizarTabelaPizzasDoPedido();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void adicionarOuAtualizarPizza() {
        if(pedidoEmAndamento == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para iniciar um pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String forma = painelRealizarPedido.getFormaSelecionada();
            double tamanho = Double.parseDouble(painelRealizarPedido.getTamanhoPizzaText());
            
            List<String> nomesSabores = new ArrayList<>();
            String sabor1 = (String) painelRealizarPedido.getComboBoxSabor1().getSelectedItem();
            if (sabor1 != null && !sabor1.trim().isEmpty()) nomesSabores.add(sabor1);
            
            String sabor2 = (String) painelRealizarPedido.getComboBoxSabor2Op().getSelectedItem();
            if (sabor2 != null && !sabor2.trim().isEmpty() && !sabor2.equals(sabor1)) {
                 nomesSabores.add(sabor2);
            }
            
            Pizza pizzaProcessada = pedidoController.criarPizza(forma, tamanho, nomesSabores, modoInsercaoPorDimensao);

            if (indicePizzaEmEdicao == -1) {
                pedidoEmAndamento.adicionarPizza(pizzaProcessada);
            } else {
                pedidoEmAndamento.getPizzas().set(indicePizzaEmEdicao, pizzaProcessada);
            }
            
            limparModoEdicaoPizza();
            atualizarTabelaPizzasDoPedido();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void prepararEdicaoPizza() {
        int selectedRow = painelRealizarPedido.getTabelaPizzasPedido().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma pizza na lista para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        indicePizzaEmEdicao = selectedRow;
        Pizza pizzaParaEditar = pedidoEmAndamento.getPizzas().get(selectedRow);

        painelRealizarPedido.getComboBoxFormaPizza().setSelectedItem(pizzaParaEditar.getForma().getClass().getSimpleName());
        painelRealizarPedido.getTextFieldDimArea().setText(String.valueOf(pizzaParaEditar.getForma().getTamanho()));
        
        List<Sabor> sabores = pizzaParaEditar.getSabores();
        painelRealizarPedido.getComboBoxSabor1().setSelectedItem(sabores.get(0).getNome());
        painelRealizarPedido.getComboBoxSabor2Op().setSelectedItem(sabores.size() > 1 ? sabores.get(1).getNome() : "");

        painelRealizarPedido.getBtnAddPizza().setText("Atualizar Pizza");
    }

    private void limparModoEdicaoPizza() {
        indicePizzaEmEdicao = -1;
        painelRealizarPedido.getBtnAddPizza().setText("Adicionar Pizza");
    }

    private void removerPizzaDoPedido() {
        int selectedRow = painelRealizarPedido.getTabelaPizzasPedido().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma pizza na lista para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (pedidoEmAndamento != null) {
            pedidoEmAndamento.getPizzas().remove(selectedRow);
            atualizarTabelaPizzasDoPedido();
        }
    }

    private void finalizarPedido() {
        if(pedidoEmAndamento == null || pedidoEmAndamento.getPizzas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos uma pizza ao pedido para finalizá-lo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            pedidoController.finalizarPedido(pedidoEmAndamento);
            JOptionPane.showMessageDialog(this, "Pedido finalizado e salvo com sucesso!");
            
            pedidoEmAndamento = null;
            painelRealizarPedido.limparCampos();
            atualizarTabelaPizzasDoPedido();
            atualizarTabelaPedidos();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void atualizarEstadoPedido() {
        int selectedRow = painelVisualizarPedido.getTabelaVisualizarPedido().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int pedidoId = (int) painelVisualizarPedido.getTabelaVisualizarPedido().getValueAt(selectedRow, 0);
        String novoEstado = (String) painelVisualizarPedido.getCbEstado().getSelectedItem();
        try {
            pedidoController.alterarEstadoPedido(pedidoId, novoEstado);
            JOptionPane.showMessageDialog(this, "Estado do pedido atualizado com sucesso!");
            atualizarTabelaPedidos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar estado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

   private void preCalcularPrecoPizza() {
    try {
        String forma = painelRealizarPedido.getFormaSelecionada();
        double tamanho = Double.parseDouble(painelRealizarPedido.getTamanhoPizzaText());
        
        List<String> nomesSabores = new ArrayList<>();
        String sabor1 = (String) painelRealizarPedido.getComboBoxSabor1().getSelectedItem();
        if (sabor1 != null && !sabor1.trim().isEmpty()) {
            nomesSabores.add(sabor1);
        }
        
        String sabor2 = (String) painelRealizarPedido.getComboBoxSabor2Op().getSelectedItem();
        if (sabor2 != null && !sabor2.trim().isEmpty() && !sabor2.equals(sabor1)) {
            nomesSabores.add(sabor2);
        }

        Pizza pizzaTemporaria = pedidoController.criarPizza(forma, tamanho, nomesSabores, modoInsercaoPorDimensao);
        double preco = pedidoController.calcularPrecoDaPizza(pizzaTemporaria);
        
        // A MUDANÇA ESTÁ AQUI:
        painelRealizarPedido.getLabelPrecoTotal().setText(String.format("R$ %.2f", preco));

    } catch (Exception e) {
        // Limpa o label em caso de erro para não mostrar um valor antigo
        painelRealizarPedido.getLabelPrecoTotal().setText("R$ --,--");
        JOptionPane.showMessageDialog(this, "Erro ao pré-calcular: Preencha os campos corretamente.\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private void setModoInsercao(boolean porDimensao) {
        modoInsercaoPorDimensao = porDimensao;
        atualizarLabelsDeDimensao();
    }

    private void atualizarLabelsDeDimensao() {
        String forma = painelRealizarPedido.getFormaSelecionada();
        if (forma == null) return;
        String min = "0", max = "0", label = "Dimensão:";

        if (modoInsercaoPorDimensao) {
            label = forma.equals("Circulo") ? "Raio:" : "Lado:";
            switch (forma) {
                case "Circulo", "Quadrado" -> { min = "10"; max = "40"; }
                case "Triangulo" -> { min = "20"; max = "60"; }
            }
        } else {
            label = "Área:";
            switch (forma) {
                case "Circulo" -> { min = "300"; max = "2000"; }
                case "Quadrado" -> { min = "100"; max = "1600"; }
                case "Triangulo" -> { min = "173"; max = "900"; }
            }
        }
        painelRealizarPedido.getLabelDimArea().setText(label);
        painelRealizarPedido.getValMinDimAreaLabel().setText(min);
        painelRealizarPedido.getValMaxDimAreaLabel().setText(max);
    }

    private void carregarDadosIniciais() {
        atualizarTabelaClientes();
        atualizarTabelaSabores();
        atualizarTabelaPrecos();
        atualizarTabelaPedidos();
        popularComboBoxes();
        atualizarLabelsDeDimensao();
    }

    private void popularComboBoxes(){
        try {
      
            JComboBox<String> comboCliente = painelRealizarPedido.getComboBoxTelCliente();
            Object selectedCliente = comboCliente.getSelectedItem();
            comboCliente.removeAllItems();
            comboCliente.addItem(""); 
            for(Cliente c : clienteController.listarTodosClientes()) {
                comboCliente.addItem(c.getTelefone());
            }
            comboCliente.setSelectedItem(selectedCliente);
            
          
            JComboBox<String> comboSabor1 = painelRealizarPedido.getComboBoxSabor1();
            JComboBox<String> comboSabor2 = painelRealizarPedido.getComboBoxSabor2Op();
            Object selSabor1 = comboSabor1.getSelectedItem();
            Object selSabor2 = comboSabor2.getSelectedItem();
            comboSabor1.removeAllItems();
            comboSabor2.removeAllItems();
            comboSabor1.addItem("");
            comboSabor2.addItem("");
            for(Sabor s : saborController.listarTodosSabores()) {
                comboSabor1.addItem(s.getNome());
                comboSabor2.addItem(s.getNome());
            }
            comboSabor1.setSelectedItem(selSabor1);
            comboSabor2.setSelectedItem(selSabor2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar listas suspensas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaClientes() {
        DefaultTableModel model = (DefaultTableModel) painelCadastroCliente.getTabelaCadastroCliente().getModel();
        model.setRowCount(0);
        try {
            for (Cliente c : clienteController.listarTodosClientes()) {
                model.addRow(new Object[]{c.getNome(), c.getSobrenome(), c.getTelefone()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void atualizarTabelaSabores() {
        DefaultTableModel model = (DefaultTableModel) painelCadastroSabor.getTabelaSabores().getModel();
        model.setRowCount(0);
        try {
            for(Sabor s : saborController.listarTodosSabores()){
                model.addRow(new Object[]{s.getNome(), s.getTipo()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar sabores: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaPrecos() {
        DefaultTableModel model = (DefaultTableModel) painelCadastroSabor.getTabelaPrecos().getModel();
        model.setRowCount(0);
        try {
            for(TipoSabor t : saborController.listarTodosTipos()) {
                model.addRow(new Object[]{t.getNome(), t.getPreco()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar preços: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void atualizarTabelaPizzasDoPedido() {
        DefaultTableModel model = (DefaultTableModel) painelRealizarPedido.getTabelaPizzasPedido().getModel();
        model.setRowCount(0);
        if (pedidoEmAndamento == null) {
             painelRealizarPedido.getLabelPrecoTotal().setText("R$ 0,00");
            return;
        }
        
        try {
            pedidoController.recalcularValorTotal(pedidoEmAndamento);
            for (Pizza pizza : pedidoEmAndamento.getPizzas()) {
                String s1 = pizza.getSabores().get(0).getNome();
                String s2 = pizza.getSabores().size() > 1 ? pizza.getSabores().get(1).getNome() : "";
                double preco = pedidoController.calcularPrecoDaPizza(pizza);
                model.addRow(new Object[]{
                    pizza.getForma().getClass().getSimpleName(), s1, s2,
                    String.format("%.2f", pizza.calcularArea()), String.format("R$ %.2f", preco)
                });
            }
            painelRealizarPedido.getLabelPrecoTotal().setText(String.format("R$ %.2f", pedidoEmAndamento.getValorTotal()));
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Erro ao calcular preço: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void atualizarTabelaPedidos() {
        DefaultTableModel model = (DefaultTableModel) painelVisualizarPedido.getTabelaVisualizarPedido().getModel();
        model.setRowCount(0);
        
        try {
            for (Pedido p : pedidoController.listarTodosPedidos()) {
                model.addRow(new Object[]{
                    p.getId(), p.getCliente().getNome() + " " + p.getCliente().getSobrenome(),
                    p.getEstado(), String.format("R$ %.2f", p.getValorTotal())
                });
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Erro ao carregar pedidos: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
}