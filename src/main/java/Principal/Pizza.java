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
    
    public double calcularArea() {
        return forma.calcularArea();
}
     
    public double calcularPreco(double precoSabor1, double precoSabor2) {
        double area = calcularArea();
        double precoTotal = 0;

        // Se houver dois sabores, divide a pizza ao meio entre os sabores
        if (sabores.size() == 2) {
            precoTotal = (precoSabor1 + precoSabor2) * (area / 2);  // Divide a área pela metade para dois sabores
        } else if (sabores.size() == 1) {
            precoTotal = precoSabor1 * area;  // Um único sabor cobre toda a área
        }

        return precoTotal;
    }
}
