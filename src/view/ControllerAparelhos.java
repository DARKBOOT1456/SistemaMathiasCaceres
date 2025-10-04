/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import bean.MscAparelhos;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerAparelhos extends AbstractTableModel{

 private List lista;
    
    public void setList(List lista){
         this.lista = lista;
    }
    
    public MscAparelhos getBean(int rowIndex){
         return (MscAparelhos) lista.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
         return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscAparelhos aparelhos = (MscAparelhos) lista.get(rowIndex);
           if(columnIndex == 0) return aparelhos.getIdmscAparelhos();
           if(columnIndex == 1) return aparelhos.getMscMarca();
           if(columnIndex == 2) return aparelhos.getMscModelo();
           if(columnIndex == 3) return aparelhos.getMscCor();
           
    
       return "";
    }
      
    public String getColumnName(int column) {
      if(column == 0) return "codigo";
      if(column == 1) return "Marca";
      if(column == 2) return "Modelo";
      if(column == 3) return "Cor";
      return "";
    }    
}
