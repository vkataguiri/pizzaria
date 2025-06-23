/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Forma;
import java.sql.SQLException;
/**
 *
 * @author amene
 */
public interface IFormaDAO {
    
    void adicionar(Forma forma, int pizzaId) throws SQLException;
    
    Forma buscarPorPizzaId(int pizzaId) throws SQLException;
}