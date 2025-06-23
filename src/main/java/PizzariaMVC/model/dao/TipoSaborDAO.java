/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.TipoSabor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thali
 */
public class TipoSaborDAO implements ITipoSaborDAO {
    private final Connection connection;

    public TipoSaborDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void atualizarPreco(String nomeTipo, double novoPreco) throws SQLException {
        String sql = "UPDATE tipos_sabor SET preco = ? WHERE nome = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, novoPreco);
            ps.setString(2, nomeTipo);
            ps.executeUpdate();
        }
    }
    
    @Override
    public TipoSabor buscarPorNome(String nomeTipo) throws SQLException {
        String sql = "SELECT * FROM tipos_sabor WHERE nome = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nomeTipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TipoSabor(rs.getString("nome"), rs.getDouble("preco"));
                }
            }
        }
        return null;
    }

    @Override
    public List<TipoSabor> listarTodos() throws SQLException {
        List<TipoSabor> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipos_sabor";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                tipos.add(new TipoSabor(rs.getString("nome"), rs.getDouble("preco")));
            }
        }
        return tipos;
    }
}