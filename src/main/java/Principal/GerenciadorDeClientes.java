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

public class GerenciadorDeClientes {

    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void adicionarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);  // Adiciona cliente à lista
        }
    }

    public static void editarCliente(String nomeAntigo, String novoNome, String novoSobrenome, String novoTelefone) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeAntigo)) {
                cliente.setNome(novoNome);
                cliente.setSobrenome(novoSobrenome);
                cliente.setTelefone(novoTelefone);
            }
        }
    }

    public static void removerCliente(String telefone) {
        Cliente cliente = buscarClientePorTelefone(telefone);
        if (cliente != null) {
            cliente.removerTodosPedidos(); // Remove todos os pedidos associados ao cliente
            clientes.remove(cliente); // Remove o cliente da lista
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public static Cliente buscarClientePorTelefone(String telefone) {
        for (Cliente cliente : clientes) {
            if (cliente.getTelefone().equals(telefone)) {
                return cliente;
            }
        }
        return null;
    }

    public static List<Cliente> buscarClientesPorSobrenome(String sobrenome) {
        List<Cliente> resultados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getSobrenome().equalsIgnoreCase(sobrenome)) {
                resultados.add(cliente);
            }
        }
        return resultados;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }
}
