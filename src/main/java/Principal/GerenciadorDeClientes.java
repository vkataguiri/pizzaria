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
    private List<Cliente> clientes;

    public GerenciadorDeClientes() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removerCliente(String telefone) {
        Cliente cliente = buscarClientePorTelefone(telefone);
        if (cliente != null) {
            cliente.removerTodosPedidos(); // Remove todos os pedidos associados ao cliente
            clientes.remove(cliente); // Remove o cliente da lista
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public Cliente buscarClientePorTelefone(String telefone) {
        for (Cliente cliente : clientes) {
            if (cliente.getTelefone().equals(telefone)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> buscarClientesPorSobrenome(String sobrenome) {
        List<Cliente> resultados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getSobrenome().equalsIgnoreCase(sobrenome)) {
                resultados.add(cliente);
            }
        }
        return resultados;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}

