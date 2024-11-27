/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public class SaborSimples extends Sabor {
    private static double precoSimples = 1; // Preço padrão para a categoria Simples

    public SaborSimples(String nome) {
        super(nome);
    }

    @Override
    public double getPreco() {
        return precoSimples;
    }

    // Método para alterar o preço
    public static void setPrecoSimples(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        precoSimples = preco;
    }
}


