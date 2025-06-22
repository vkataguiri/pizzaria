/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author amene
 */
public class Pedido {

    private int id;
    private Cliente cliente;
    private List<Pizza> pizzas;
    private String estado;
    private double valorTotal;
    private Timestamp dataPedido;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        this.estado = "Aberto"; // Estado inicial padrão
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Pizza> getPizzas() { return pizzas; }
    public void setPizzas(List<Pizza> pizzas) { this.pizzas = pizzas; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public Timestamp getDataPedido() { return dataPedido; }
    public void setDataPedido(Timestamp dataPedido) { this.dataPedido = dataPedido; }
    
    public void adicionarPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
}