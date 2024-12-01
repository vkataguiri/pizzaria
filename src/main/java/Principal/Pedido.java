/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Pizza> pizzas;
    private String estado;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        this.estado = "Aberto";
    }

    public void adicionarPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
    
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setEstado(String estado) {
        if (!estado.equals("Aberto") && !estado.equals("A Caminho") && !estado.equals("Entregue")) {
            throw new IllegalArgumentException("Estado inválido!");
        }
        this.estado = estado;
    }
    public String getEstado(){
        return this.estado;
    }
            
    public double calcularPrecoTotal(double preco1, double preco2) {
        return pizzas.stream().mapToDouble(p -> p.calcularPreco(preco1,preco2)).sum();
    }
    
}
