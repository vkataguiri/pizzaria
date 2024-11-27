/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
import java.util.List;

public class Pizza {
    private Forma forma;
    private List<Sabor> sabores;

    public Pizza(Forma forma, List<Sabor> sabores) {
        if (sabores.size() > 2) {
            throw new IllegalArgumentException("Uma pizza não pode ter mais de dois sabores!");
        }
        this.forma = forma;
        this.sabores = sabores;
    }

    public double calcularPreco(double precoSimples, double precoEspecial, double precoPremium) {
        double precoPorCm2 = sabores.stream()
            .mapToDouble(s -> {
                if (s instanceof SaborSimples) return precoSimples;
                if (s instanceof SaborEspecial) return precoEspecial;
                if (s instanceof SaborPremium) return precoPremium;
                throw new IllegalArgumentException("Tipo de sabor inválido.");
            })
            .average()
            .orElse(0);
        return forma.calcularArea() * precoPorCm2;
    }
}
