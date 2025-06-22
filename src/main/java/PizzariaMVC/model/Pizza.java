/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzariaMVC.model;

import java.util.List;

/**
 *
 * @author amene
 */
public class Pizza {
    private int id;
    private Forma forma;
    private List<Sabor> sabores;

    public Pizza(Forma forma, List<Sabor> sabores) {
        this.forma = forma;
        this.sabores = sabores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }
    
    public double calcularArea() {
        if (this.forma == null) {
            return 0;
        }
        return this.forma.calcularArea();
    }
}