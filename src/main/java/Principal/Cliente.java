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

public class Cliente {
    private String nome;
    private String sobrenome;
    private String telefone;
    private List<Pedido> pedidos;

    public Cliente(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.pedidos = new ArrayList<>();
        validarTelefone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        validarTelefone(telefone);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void removerPedido(Pedido pedido) {
        if (!this.pedidos.remove(pedido)) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
    }

    public void removerTodosPedidos() {
        this.pedidos.clear();
    }

     public static void validarTelefone(String telefone) {
        // 11 digitos
        if (telefone == null || telefone.isEmpty() || !telefone.matches("\\d{11}")) {
            throw new IllegalArgumentException("Telefone inválido! Deve conter 11 dígitos numéricos.");
        }
    }
}
