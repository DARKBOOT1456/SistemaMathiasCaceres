/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.AparelhosBean;
import dao.AparelhosDao;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerAparelhos extends AbstractTableModel{

List lista;
    
    public void setList(List lista){
         this.lista = lista;
    }
    
    public Object getBean(int rowIndex){
        return lista.get(rowIndex);
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
        AparelhosBean aparelhos = (AparelhosBean) lista.get(rowIndex);
           if(columnIndex == 0) return aparelhos.getMsc_idAparelhos();
           if(columnIndex == 1) return aparelhos.getMsc_marca();
           if(columnIndex == 2) return aparelhos.getMsc_modelo();
           if(columnIndex == 3) return aparelhos.getMsc_cor();
           
    
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
