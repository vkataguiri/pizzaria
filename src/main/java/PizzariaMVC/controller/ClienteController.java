/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.controller;

import PizzariaMVC.model.Cliente;
import PizzariaMVC.model.Pedido;
import PizzariaMVC.model.dao.ClienteDAO;
import PizzariaMVC.model.dao.PedidoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amene
 */
public class ClienteController {
    private final ClienteDAO clienteDAO;
    private final PedidoDAO pedidoDAO;

    public ClienteController(ClienteDAO clienteDAO, PedidoDAO pedidoDAO) {
        this.clienteDAO = clienteDAO;
        this.pedidoDAO = pedidoDAO;
    }

    public void salvarCliente(String nome, String sobrenome, String telefone) throws Exception {
        if (nome == null || nome.trim().isEmpty() || sobrenome == null || sobrenome.trim().isEmpty()) {
            throw new Exception("Nome e Sobrenome não podem ser vazios.");
        }
        validarTelefone(telefone);
        if (clienteDAO.buscarPorTelefone(telefone) != null) {
            throw new Exception("Já existe um cliente cadastrado com este telefone.");
        }
        Cliente novoCliente = new Cliente(nome, sobrenome, telefone);
        clienteDAO.adicionar(novoCliente);
    }

    public void atualizarCliente(String telefoneAntigo, String novoNome, String novoSobrenome, String novoTelefone) throws Exception {
        if (novoNome == null || novoNome.trim().isEmpty() || novoSobrenome == null || novoSobrenome.trim().isEmpty()) {
            throw new Exception("Nome e Sobrenome não podem ser vazios.");
        }
        validarTelefone(novoTelefone);
        if (clienteDAO.buscarPorTelefone(telefoneAntigo) == null) {
            throw new Exception("Cliente a ser atualizado não foi encontrado.");
        }
        if (!telefoneAntigo.equals(novoTelefone) && clienteDAO.buscarPorTelefone(novoTelefone) != null) {
            throw new Exception("O novo telefone já está em uso por outro cliente.");
        }
        Cliente dadosNovos = new Cliente(novoNome, novoSobrenome, novoTelefone);
        clienteDAO.atualizar(telefoneAntigo, dadosNovos);
    }

    public void removerCliente(String telefone) throws Exception {
        Cliente cliente = clienteDAO.buscarPorTelefone(telefone);
        if (cliente == null) {
            throw new Exception("Cliente não encontrado para remoção.");
        }
        List<Pedido> pedidosDoCliente = pedidoDAO.listarPorClienteId(cliente.getId());
        if (pedidosDoCliente != null && !pedidosDoCliente.isEmpty()) {
             throw new Exception("Não é possível remover um cliente que possui pedidos associados.");
        }
        clienteDAO.remover(telefone);
    }
    
    private void validarTelefone(String telefone) throws Exception {
        if (telefone == null || !telefone.matches("\\d{11}")) {
            throw new Exception("Telefone inválido! Deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public List<Cliente> listarTodosClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }
    
    public Cliente buscarClientePorTelefone(String telefone) throws SQLException {
        return clienteDAO.buscarPorTelefone(telefone);
    }
}