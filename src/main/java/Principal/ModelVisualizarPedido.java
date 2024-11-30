/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luana
 */
public class ModelVisualizarPedido extends AbstractTableModel {
    private ArrayList<Pedido> pedidos;
    
    
    public ModelVisualizarPedido (ArrayList<Pedido> pedidos){
        this.pedidos = pedidos;
        
    }

    @Override
    public int getRowCount() {
        return this.pedidos.size();
    }

    @Override
    public int getColumnCount() {
      return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex ==0)
            return rowIndex;
        return "batman";
//        if(columnIndex==1)
//            return this.pedidos.get(rowIndex).getCliente().getNome();
//
//        return this.pedidos.get(rowIndex).getEstado();
    }
    
}
