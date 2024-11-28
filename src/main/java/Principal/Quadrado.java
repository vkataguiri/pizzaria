/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public class Quadrado extends Forma {

    public Quadrado(double lado) {
        super(lado);
    }

    @Override
    public double calcularArea() {
        return Math.pow(tamanho, 2);  // Calcula a área do quadrado (lado²)
    }

    // Método auxiliar caso queira criar o quadrado a partir da área
    public static Quadrado daArea(double area) {
        double lado = Math.sqrt(area);
        return new Quadrado(lado);
    }
}




 
