/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public abstract class Forma {
    protected double tamanho;

    // Construtor
    public Forma(double tamanho) {
        this.tamanho = tamanho;
    }

    // Método para calcular área, cada classe concreta deve implementar
    public abstract double calcularArea();
}

