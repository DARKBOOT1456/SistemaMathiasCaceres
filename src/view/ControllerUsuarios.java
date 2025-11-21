/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import bean.MscUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerUsuarios extends AbstractTableModel{
    private List lista;
    
    public void setList(List lista){
         this.lista = lista;
    }
    
    public MscUsuarios getBean(int rowIndex){
        return (MscUsuarios) lista.get(rowIndex);
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
        MscUsuarios usuarios = (MscUsuarios) lista.get(rowIndex);
           if(columnIndex == 0) return usuarios.getIdmscUsuarios();
           if(columnIndex == 1) return usuarios.getMscNome();
           if(columnIndex == 2) return usuarios.getMscCpf();
           if(columnIndex == 3) return usuarios.getMscAtivo();
           
    
       return "";
    }
      
    public String getColumnName(int column) {
      if(column == 0) return "codigo";
      if(column == 1) return "Nome";
      if(column == 2) return "Cpf";
      if(column == 3) return "Ativo";
      return "";
    }    
}
