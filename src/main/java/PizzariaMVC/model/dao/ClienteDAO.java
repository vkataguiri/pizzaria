/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amene
 */
public class ClienteDAO implements IClienteDAO {
    private final Connection connection;

    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adicionar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, sobrenome, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getTelefone());
            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(String telefoneAntigo, Cliente dadosNovos) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, sobrenome = ?, telefone = ? WHERE telefone = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dadosNovos.getNome());
            ps.setString(2, dadosNovos.getSobrenome());
            ps.setString(3, dadosNovos.getTelefone());
            ps.setString(4, telefoneAntigo);
            ps.executeUpdate();
        }
    }
    
    @Override
    public void remover(String telefone) throws SQLException {
        String sql = "DELETE FROM clientes WHERE telefone = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, telefone);
            ps.executeUpdate();
        }
    }

    @Override
    public Cliente buscarPorTelefone(String telefone) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE telefone = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, telefone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirCliente(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirCliente(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY nome";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientes.add(construirCliente(rs));
            }
        }
        return clientes;
    }
    
    private Cliente construirCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(
            rs.getString("nome"),
            rs.getString("sobrenome"),
            rs.getString("telefone")
        );
        cliente.setId(rs.getInt("id")); 
        return cliente;
    }
}