/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model.dao;

/**
 *
 * @author amene
 */
public class DAOFactory {

    public IClienteDAO getClienteDAO() {
        return new ClienteDAO();
    }

    public ISaborDAO getSaborDAO() {
        return new SaborDAO();
    }
    
    public ITipoSaborDAO getTipoSaborDAO() {
        return new TipoSaborDAO();
    }

    public IPedidoDAO getPedidoDAO() {
        return new PedidoDAO();
    }
    
    public IFormaDAO getFormaDAO() {
        return new FormaDAO();
    }
}
