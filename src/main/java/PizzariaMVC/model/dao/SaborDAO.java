/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Sabor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thali
 */
public class SaborDAO {
    private Connection connection;

    public SaborDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public SaborDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void adicionar(Sabor sabor) throws SQLException {
        String sql = "INSERT INTO sabores (nome, tipo_nome) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sabor.getNome());
            ps.setString(2, sabor.getTipo());
            ps.executeUpdate();
        }
    }

    public void remover(String nomeSabor) throws SQLException {
        String sql = "DELETE FROM sabores WHERE nome = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nomeSabor);
            ps.executeUpdate();
        }
    }
    
    public void atualizar(Sabor sabor) throws SQLException {
        String sql = "UPDATE sabores SET nome = ?, tipo_nome = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sabor.getNome());
            ps.setString(2, sabor.getTipo());
            ps.setInt(3, sabor.getId());
            ps.executeUpdate();
        }
    }

    public Sabor buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM sabores WHERE nome = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirSabor(rs);
                }
            }
        }
        return null;
    }
    
    public Sabor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM sabores WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirSabor(rs);
                }
            }
        }
        return null;
    }
    
    public List<Sabor> buscarPorPizzaId(int pizzaId) throws SQLException {
        List<Sabor> sabores = new ArrayList<>();
        String sql = "SELECT s.* FROM sabores s " +
                     "JOIN pizzas_sabores ps ON s.id = ps.sabor_id " +
                     "WHERE ps.pizza_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pizzaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sabores.add(construirSabor(rs));
                }
            }
        }
        return sabores;
    }

    public List<Sabor> listarTodos() throws SQLException {
        List<Sabor> sabores = new ArrayList<>();
        String sql = "SELECT * FROM sabores ORDER BY nome";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                sabores.add(construirSabor(rs));
            }
        }
        return sabores;
    }
    
    private Sabor construirSabor(ResultSet rs) throws SQLException {
        Sabor sabor = new Sabor(rs.getString("nome"), rs.getString("tipo_nome"));
        sabor.setId(rs.getInt("id"));
        return sabor;
    }
}