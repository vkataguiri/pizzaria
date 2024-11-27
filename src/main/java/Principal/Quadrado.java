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
    private double lado;

    public Quadrado(double lado) {
        if (lado < 10 || lado > 40) {
            throw new IllegalArgumentException("Lado deve estar entre 10 e 40 cm.");
        }
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}




 
