/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.ArrayList;

/**
 *
 * @author vinic
 */
public class GerenciadorSabores {

    private static ArrayList<Sabor> sabores = new ArrayList<>();

    public static void adicionarSabor(Sabor sabor) {
        sabores.add(sabor);
    }

    public static void editarSabor(Sabor sabor, String novoNome, String novoTipo) {
        Sabor novoSabor;
        switch (novoTipo) {
            case "Simples" -> {
                // sabor simples
                novoSabor = new SaborSimples(novoNome);
            }
            case "Especial" -> {
                novoSabor = new SaborEspecial(novoNome);
            }
            case "Premium" -> {
                novoSabor = new SaborPremium(novoNome);
            }
            default -> {
                throw new RuntimeException("Index invalido na edicao de sabores.");
            }
        }

        sabores.remove(sabor);
        sabores.add(novoSabor);
    }

    public static void removerSabor(Sabor sabor) {
        sabores.remove(sabor);
    }
    
    public static Sabor encontrarPorNome(String nome) {
        for (Sabor sabor : sabores) {
            if (sabor.getNome().equalsIgnoreCase(nome)) {
                return sabor;
            }
        }
        
        // Caso saia do loop, quer dizer que nao encontrou nenhum sabor
        throw new RuntimeException("Nao foi possivel encontrar um sabor com o nome " + nome + ".");
    }

    public static ArrayList<Sabor> listarSabores() {
        return sabores;
    }

}
