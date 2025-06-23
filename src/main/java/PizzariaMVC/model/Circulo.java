/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model;

/**
 *
 * @author amene
 */
public class Circulo extends Forma {

    public Circulo(double raio) {
        super(raio);
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(tamanho, 2);  
    }

    public static Circulo daArea(double area) {
        double raio = Math.sqrt(area / Math.PI);
        return new Circulo(raio);
    }
}
