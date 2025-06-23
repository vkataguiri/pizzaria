/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Sabor;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author amene
 */

public interface ISaborDAO {
    
    void adicionar(Sabor sabor) throws SQLException;
    
    void atualizar(Sabor sabor) throws SQLException;
    
    void remover(String nomeSabor) throws SQLException;
    
    Sabor buscarPorId(int id) throws SQLException;
    
    Sabor buscarPorNome(String nome) throws SQLException;
    
    List<Sabor> buscarPorPizzaId(int pizzaId) throws SQLException;
    
    List<Sabor> listarTodos() throws SQLException;
}
