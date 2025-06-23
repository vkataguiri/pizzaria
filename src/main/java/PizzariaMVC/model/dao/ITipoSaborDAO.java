/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.TipoSabor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thali
 */
public interface ITipoSaborDAO {
    
    void atualizarPreco(String nomeTipo, double novoPreco) throws SQLException;
    
    TipoSabor buscarPorNome(String nomeTipo) throws SQLException;
    
    List<TipoSabor> listarTodos() throws SQLException;
}