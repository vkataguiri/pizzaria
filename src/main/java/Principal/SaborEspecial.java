/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public class SaborEspecial extends Sabor {
    private static double precoEspecial = 2; // Preço padrão para a categoria Especial

    public SaborEspecial(String nome) {
        super(nome);
    }

    @Override
    public double getPreco() {
        return precoEspecial;
    }

    // Método para alterar o preço
    public static void setPrecoEspecial(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        precoEspecial = preco;
    }

    // Método para obter o preço
    public static double getPrecoEspecial() {
        return precoEspecial;
    }
}
