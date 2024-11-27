/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public class SaborPremium extends Sabor {
    private static double precoPremium = 3; // Preço padrão para a categoria Premium

    public SaborPremium(String nome) {
        super(nome);
    }

    @Override
    public double getPreco() {
        return precoPremium;
    }

    // Método para alterar o preço
    public static void setPrecoPremium(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        precoPremium = preco;
    }
}
