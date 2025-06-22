/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.*; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class FormaDAO {
    private final Connection connection;

    public FormaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public FormaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionar(Forma forma, int pizzaId) throws SQLException {
        String sql = "INSERT INTO formas (tamanho, tipo_forma, pizza_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, forma.getTamanho());
            
            if (forma instanceof Circulo) {
                ps.setString(2, "CIRCULO");
            } else if (forma instanceof Quadrado) {
                ps.setString(2, "QUADRADO");
            } else if (forma instanceof Triangulo) {
                ps.setString(2, "TRIANGULO");
            } else {
                throw new IllegalArgumentException("Tipo de forma desconhecido.");
            }
            
            ps.setInt(3, pizzaId);
            ps.executeUpdate();
        }
    }

    public Forma buscarPorPizzaId(int pizzaId) throws SQLException {
        String sql = "SELECT * FROM formas WHERE pizza_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pizzaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipoForma = rs.getString("tipo_forma");
                    double tamanho = rs.getDouble("tamanho");

                    switch (tipoForma) {
                        case "CIRCULO":
                            return new Circulo(tamanho);
                        case "QUADRADO":
                            return new Quadrado(tamanho);
                        case "TRIANGULO":
                            return new Triangulo(tamanho);
                        default:
                            throw new SQLException("Tipo de forma desconhecido no banco de dados: " + tipoForma);
                    }
                }
            }
        }
        return null;
    }
}