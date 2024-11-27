/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author amene
 */
public abstract class Sabor {
    protected String nome;

    public Sabor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método abstrato para obter o preço de cada sabor
    public abstract double getPreco();
    
    // Método abstrato para validar o tipo de sabor
    public abstract void validarTipo();
}
