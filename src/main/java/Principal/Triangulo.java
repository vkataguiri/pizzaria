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

    public Triangulo(double lado) {
        super(lado);
    }

    @Override
    public double calcularArea() {
        return (Math.sqrt(3) / 4) * Math.pow(tamanho, 2);  // Fórmula para a área de um triângulo equilátero
    }
    
    // Método auxiliar caso queira criar o triângulo a partir da área
    public static Triangulo daArea(double area) {
        double lado = Math.sqrt((4 * area) / Math.sqrt(3));
        return new Triangulo(lado);
    }
}

