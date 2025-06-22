/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Forma;
import PizzariaMVC.model.Pizza;
import PizzariaMVC.model.Sabor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sme
 */
public class PizzaDAO {
    private final Connection connection;
    private final FormaDAO formaDAO;
    private final SaborDAO saborDAO;

    public PizzaDAO(Connection connection) {
        this.connection = connection;
        this.formaDAO = new FormaDAO(this.connection);
        this.saborDAO = new SaborDAO(this.connection);
    }

    
    public Pizza salvar(Pizza pizza, int pedidoId) throws SQLException {
        String sqlPizza = "INSERT INTO pizzas (pedido_id) VALUES (?)";
        try (PreparedStatement psPizza = connection.prepareStatement(sqlPizza, Statement.RETURN_GENERATED_KEYS)) {
            psPizza.setInt(1, pedidoId);
            psPizza.executeUpdate();

            try (ResultSet rs = psPizza.getGeneratedKeys()) {
                if (rs.next()) {
                    pizza.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar a pizza, nenhum ID obtido.");
                }
            }
        }
        if (pizza.getForma() != null) {
            formaDAO.adicionar(pizza.getForma(), pizza.getId());
        }
        String sqlSabores = "INSERT INTO pizzas_sabores (pizza_id, sabor_id) VALUES (?, ?)";
        try (PreparedStatement psSabores = connection.prepareStatement(sqlSabores)) {
            for (Sabor sabor : pizza.getSabores()) {
                psSabores.setInt(1, pizza.getId());
                psSabores.setInt(2, sabor.getId());
                psSabores.addBatch();
            }
            psSabores.executeBatch();
        }
        return pizza;
    }

    public List<Pizza> buscarPorPedidoId(int pedidoId) throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();
        String sql = "SELECT id FROM pizzas WHERE pedido_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pedidoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pizzaId = rs.getInt("id");
                    Forma forma = formaDAO.buscarPorPizzaId(pizzaId);
                    List<Sabor> sabores = saborDAO.buscarPorPizzaId(pizzaId);
                    Pizza pizza = new Pizza(forma, sabores);
                    pizza.setId(pizzaId);
                    pizzas.add(pizza);
                }
            }
        }
        return pizzas;
    }
    
    public void removerPorPedidoId(int pedidoId) throws SQLException {
        String sql = "DELETE FROM pizzas WHERE pedido_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pedidoId);
            ps.executeUpdate();
        }
    }
}