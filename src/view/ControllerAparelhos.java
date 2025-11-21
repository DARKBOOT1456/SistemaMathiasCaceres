/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
<<<<<<< HEAD

import bean.AparelhosBean;
import dao.AparelhosDao;
=======
import bean.MscAparelhos;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerAparelhos extends AbstractTableModel{

<<<<<<< HEAD
List lista;
=======
 private List lista;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    
    public void setList(List lista){
         this.lista = lista;
    }
    
<<<<<<< HEAD
    public Object getBean(int rowIndex){
        return lista.get(rowIndex);
=======
    public MscAparelhos getBean(int rowIndex){
         return (MscAparelhos) lista.get(rowIndex);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
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
<<<<<<< HEAD
        AparelhosBean aparelhos = (AparelhosBean) lista.get(rowIndex);
           if(columnIndex == 0) return aparelhos.getMsc_idAparelhos();
           if(columnIndex == 1) return aparelhos.getMsc_marca();
           if(columnIndex == 2) return aparelhos.getMsc_modelo();
           if(columnIndex == 3) return aparelhos.getMsc_cor();
=======
        MscAparelhos aparelhos = (MscAparelhos) lista.get(rowIndex);
           if(columnIndex == 0) return aparelhos.getIdmscAparelhos();
           if(columnIndex == 1) return aparelhos.getMscMarca();
           if(columnIndex == 2) return aparelhos.getMscModelo();
           if(columnIndex == 3) return aparelhos.getMscCor();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
           
    
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
