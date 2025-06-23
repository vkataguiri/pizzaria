/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Pizza;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author amene
 */
public interface IPizzaDAO {

    Pizza salvar(Pizza pizza, int pedidoId) throws SQLException;
    
    List<Pizza> buscarPorPedidoId(int pedidoId) throws SQLException;
    
    void removerPorPedidoId(int pedidoId) throws SQLException;
}