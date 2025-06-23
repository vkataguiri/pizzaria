/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

import PizzariaMVC.model.Cliente;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author amene
 */
public interface IClienteDAO {
    
    void adicionar(Cliente cliente) throws SQLException;
    
    void atualizar(String telefoneAntigo, Cliente dadosNovos) throws SQLException;
    
    void remover(String telefone) throws SQLException;
    
    Cliente buscarPorId(int id) throws SQLException;
    
    Cliente buscarPorTelefone(String telefone) throws SQLException;
    
    List<Cliente> listarTodos() throws SQLException;
}
