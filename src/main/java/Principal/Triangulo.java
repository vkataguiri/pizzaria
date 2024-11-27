/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public class Triangulo extends Forma {
    private double lado;

    public Triangulo(double lado) {
        if (lado < 20 || lado > 60) {
            throw new IllegalArgumentException("Lado deve estar entre 20 e 60 cm.");
        }
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return (Math.sqrt(3) / 4) * lado * lado; // Fórmula para área de um triângulo equilátero
    }
}
