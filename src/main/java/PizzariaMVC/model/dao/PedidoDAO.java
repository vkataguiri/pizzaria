/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Cliente;
import PizzariaMVC.model.Pedido;
import PizzariaMVC.model.Pizza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amene
 */
public class PedidoDAO {
    
    private final Connection connection;
    private final ClienteDAO clienteDAO;
    private final PizzaDAO pizzaDAO;

    public PedidoDAO() {
        this.connection = ConnectionFactory.getConnection();
      
        this.clienteDAO = new ClienteDAO(); 
        
        this.pizzaDAO = new PizzaDAO(this.connection);
    }

    public void salvar(Pedido pedido) throws SQLException {
        if (pedido.getId() == 0) {
            inserirNovo(pedido);
        } else {
            atualizarExistente(pedido);
        }
    }

    private void inserirNovo(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id, valor_total, estado) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getCliente().getId());
            ps.setDouble(2, pedido.getValorTotal());
            ps.setString(3, pedido.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int pedidoId = rs.getInt(1);
                    pedido.setId(pedidoId);
                    for (Pizza pizza : pedido.getPizzas()) {
                        pizzaDAO.salvar(pizza, pedidoId);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Erro ao salvar o novo pedido. A transação foi desfeita.", e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void atualizarExistente(Pedido pedido) throws SQLException {
        String sqlUpdatePedido = "UPDATE pedidos SET valor_total = ?, estado = ? WHERE id = ?";
        connection.setAutoCommit(false);
        try {
            try (PreparedStatement ps = connection.prepareStatement(sqlUpdatePedido)) {
                ps.setDouble(1, pedido.getValorTotal());
                ps.setString(2, pedido.getEstado());
                ps.setInt(3, pedido.getId());
                ps.executeUpdate();
            }
            pizzaDAO.removerPorPedidoId(pedido.getId());
            for (Pizza pizza : pedido.getPizzas()) {
                pizzaDAO.salvar(pizza, pedido.getId());
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Erro ao atualizar o pedido. A transação foi desfeita.", e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void atualizarEstado(int pedidoId, String novoEstado) throws SQLException {
        String sql = "UPDATE pedidos SET estado = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, novoEstado);
            ps.setInt(2, pedidoId);
            ps.executeUpdate();
        }
    }

    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirPedido(rs);
                }
            }
        }
        return null;
    }

    public List<Pedido> listarTodos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos ORDER BY data_pedido DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                pedidos.add(construirPedido(rs));
            }
        }
        return pedidos;
    }

    public List<Pedido> listarPorClienteId(int clienteId) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE cliente_id = ? ORDER BY data_pedido DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(construirPedido(rs));
                }
            }
        }
        return pedidos;
    }
    
    private Pedido construirPedido(ResultSet rs) throws SQLException {
        int clienteId = rs.getInt("cliente_id");
        int pedidoId = rs.getInt("id");
        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        
        Pedido pedido = new Pedido(cliente);
        pedido.setId(pedidoId);
        pedido.setValorTotal(rs.getDouble("valor_total"));
        pedido.setEstado(rs.getString("estado"));
        pedido.setDataPedido(rs.getTimestamp("data_pedido"));
        
        List<Pizza> pizzas = pizzaDAO.buscarPorPedidoId(pedidoId);
        pedido.setPizzas(pizzas);
        
        return pedido;
    }
}