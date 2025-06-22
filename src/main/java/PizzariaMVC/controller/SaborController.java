/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.controller;

import PizzariaMVC.model.Sabor;
import PizzariaMVC.model.TipoSabor;
import PizzariaMVC.model.dao.SaborDAO;
import PizzariaMVC.model.dao.TipoSaborDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vinic
 */
public class SaborController {
    
    private final SaborDAO saborDAO;
    private final TipoSaborDAO tipoSaborDAO;

    public SaborController(SaborDAO saborDAO, TipoSaborDAO tipoSaborDAO) {
        this.saborDAO = saborDAO;
        this.tipoSaborDAO = tipoSaborDAO;
    }

    public void salvarSabor(String nome, String tipo) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome do sabor não pode ser vazio.");
        }
        if (saborDAO.buscarPorNome(nome) != null) {
            throw new Exception("Sabor com este nome já existe.");
        }
        
        Sabor novoSabor = new Sabor(nome, tipo);
        saborDAO.adicionar(novoSabor);
    }
    
    public void atualizarSabor(Sabor saborParaAtualizar) throws Exception {
        if (saborParaAtualizar.getNome() == null || saborParaAtualizar.getNome().trim().isEmpty()) {
            throw new Exception("O nome do sabor não pode ser vazio.");
        }

        Sabor saborExistente = saborDAO.buscarPorNome(saborParaAtualizar.getNome());
        if (saborExistente != null && saborExistente.getId() != saborParaAtualizar.getId()) {
            throw new Exception("Já existe outro sabor cadastrado com este nome.");
        }

        saborDAO.atualizar(saborParaAtualizar);
    }
    
    public void atualizarPrecoTipo(String nomeTipo, double novoPreco) throws Exception {
        if (novoPreco <= 0) {
            throw new Exception("Preço deve ser um valor positivo.");
        }
        tipoSaborDAO.atualizarPreco(nomeTipo, novoPreco);
    }
    
    public void removerSabor(String nomeSabor) throws Exception {
        saborDAO.remover(nomeSabor);
    }

    public Sabor buscarPorNome(String nome) throws SQLException {
        return saborDAO.buscarPorNome(nome);
    }

    public List<Sabor> listarTodosSabores() throws SQLException {
        return saborDAO.listarTodos();
    }
    
    public List<TipoSabor> listarTodosTipos() throws SQLException {
        return tipoSaborDAO.listarTodos();
    }
}