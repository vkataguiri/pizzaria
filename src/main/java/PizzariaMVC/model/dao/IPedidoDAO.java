/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Pedido;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author amene
 */
public interface IPedidoDAO {
    
    void salvar(Pedido pedido) throws SQLException;
    
    void atualizarEstado(int pedidoId, String novoEstado) throws SQLException;
    
    Pedido buscarPorId(int id) throws SQLException;
    
    List<Pedido> listarTodos() throws SQLException;
    
    List<Pedido> listarPorClienteId(int clienteId) throws SQLException;
}
