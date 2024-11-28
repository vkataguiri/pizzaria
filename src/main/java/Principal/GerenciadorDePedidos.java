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

public class GerenciadorDePedidos {
    private static List<Pedido> pedidos; // Lista que armazena todos os pedidos

    // Construtor
    public GerenciadorDePedidos() {
        this.pedidos = new ArrayList<>();
    }

    // Método para adicionar um pedido
    public static void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Método para listar todos os pedidos
    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    // Método para buscar um pedido por cliente (usando telefone como identificador)
    public Pedido buscarPedidoPorCliente(String telefone) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getTelefone().equals(telefone)) {
                return pedido;
            }
        }
        return null;
    }

    // Método para alterar o estado de um pedido
    public void alterarEstadoPedido(String telefone, String novoEstado) {
        Pedido pedido = buscarPedidoPorCliente(telefone);
        if (pedido != null) {
            pedido.setEstado(novoEstado);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado para o cliente com telefone: " + telefone);
        }
    }
}

