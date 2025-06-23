/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.controller;

import PizzariaMVC.model.*;
import PizzariaMVC.model.dao.IPedidoDAO;
import PizzariaMVC.model.dao.ISaborDAO;
import PizzariaMVC.model.dao.ITipoSaborDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sme
 */
public class PedidoController {

    private final IPedidoDAO pedidoDAO;
    private final ISaborDAO saborDAO;
    private final ITipoSaborDAO tipoSaborDAO;

    public PedidoController(IPedidoDAO pedidoDAO, ISaborDAO saborDAO, ITipoSaborDAO tipoSaborDAO) {
        this.pedidoDAO = pedidoDAO;
        this.saborDAO = saborDAO;
        this.tipoSaborDAO = tipoSaborDAO;
    }

    public Pedido iniciarNovoPedido(Cliente cliente) {
        return new Pedido(cliente);
    }
    
    public Pizza criarPizza(String tipoForma, double tamanho, List<String> nomesSabores, boolean porDimensao) throws Exception {
        if (nomesSabores == null || nomesSabores.isEmpty()) {
            throw new Exception("Uma pizza deve ter pelo menos um sabor.");
        }
        if (nomesSabores.size() > 2) {
            throw new Exception("Uma pizza não pode ter mais de dois sabores.");
        }
        
        Forma formaDaPizza = criarForma(tipoForma, tamanho, porDimensao);
        
        List<Sabor> sabores = new ArrayList<>();
        for(String nome : nomesSabores) {
            Sabor s = saborDAO.buscarPorNome(nome);
            if (s == null) {
                throw new Exception("Sabor '" + nome + "' não encontrado no banco de dados.");
            }
            sabores.add(s);
        }
        
        return new Pizza(formaDaPizza, sabores);
    }

    public void adicionarPizzaAoPedido(Pedido pedido, String tipoForma, double tamanho, List<String> nomesSabores, boolean porDimensao) throws Exception {
        Pizza novaPizza = criarPizza(tipoForma, tamanho, nomesSabores, porDimensao);
        pedido.adicionarPizza(novaPizza);
        recalcularValorTotal(pedido);
    }
    
    public void finalizarPedido(Pedido pedido) throws Exception {
        if (pedido.getPizzas().isEmpty()) {
            throw new Exception("Não é possível finalizar um pedido sem pizzas.");
        }
        recalcularValorTotal(pedido);
        pedidoDAO.salvar(pedido);
    }

    public void alterarEstadoPedido(int pedidoId, String novoEstado) throws Exception {
        if (!novoEstado.equals("Aberto") && !novoEstado.equals("A Caminho") && !novoEstado.equals("Entregue")) {
            throw new Exception("Estado inválido!");
        }
        pedidoDAO.atualizarEstado(pedidoId, novoEstado);
    }

    public List<Pedido> listarTodosPedidos() throws SQLException {
        return pedidoDAO.listarTodos();
    }

    public void recalcularValorTotal(Pedido pedido) throws Exception {
        double total = 0;
        for (Pizza pizza : pedido.getPizzas()) {
            total += calcularPrecoDaPizza(pizza);
        }
        pedido.setValorTotal(total);
    }

    public double calcularPrecoDaPizza(Pizza pizza) throws Exception {
        double area = pizza.calcularArea();
        if (area <= 0) return 0;
        
        List<Sabor> sabores = pizza.getSabores();
        double precoFinal = 0;
        
        String tipoSabor1Nome = sabores.get(0).getTipo();
        TipoSabor tipo1 = tipoSaborDAO.buscarPorNome(tipoSabor1Nome);
        if (tipo1 == null) {
            throw new Exception("Tipo de sabor '" + tipoSabor1Nome + "' não possui um preço cadastrado.");
        }
        
        if (sabores.size() == 1) {
            precoFinal = tipo1.getPreco() * area;
        } else if (sabores.size() == 2) {
            String tipoSabor2Nome = sabores.get(1).getTipo();
            TipoSabor tipo2 = tipoSaborDAO.buscarPorNome(tipoSabor2Nome);
            if (tipo2 == null) {
                throw new Exception("Tipo de sabor '" + tipoSabor2Nome + "' não possui um preço cadastrado.");
            }
            precoFinal = (tipo1.getPreco() + tipo2.getPreco()) * (area / 2.0);
        }
        return precoFinal;
    }

    private Forma criarForma(String tipo, double tamanho, boolean porDimensao) throws Exception {
        if (!isTamanhoValido(tipo, tamanho, porDimensao)) {
            throw new Exception("Tamanho inválido para a forma e modo selecionado.");
        }
        
        switch (tipo.toUpperCase()) {
            case "CIRCULO": return new Circulo(tamanho);
            case "QUADRADO": return new Quadrado(tamanho);
            case "TRIANGULO": return new Triangulo(tamanho);
            default: throw new Exception("Forma de pizza desconhecida.");
        }
    }
    
    private boolean isTamanhoValido(String tipoForma, double tamanho, boolean porDimensao) {
        if (porDimensao) {
            return switch (tipoForma.toUpperCase()) {
                case "CIRCULO", "QUADRADO" -> tamanho >= 10 && tamanho <= 40;
                case "TRIANGULO" -> tamanho >= 20 && tamanho <= 60;
                default -> false;
            };
        } else { 
            return switch (tipoForma.toUpperCase()) {
                case "CIRCULO" -> tamanho >= 300 && tamanho <= 2000;
                case "QUADRADO" -> tamanho >= 100 && tamanho <= 1600;
                case "TRIANGULO" -> tamanho >= 173 && tamanho <= 900;
                default -> false;
            };
        }
    }
}