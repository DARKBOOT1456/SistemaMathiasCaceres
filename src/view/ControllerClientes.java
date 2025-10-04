/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import bean.MscClientes;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerClientes extends AbstractTableModel {
    
   private List lista;
    
    public void setList(List lista){
         this.lista = lista;
    }
    
    public MscClientes getBean(int rowIndex){
        return (MscClientes) lista.get(rowIndex);
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
        MscClientes clientes = (MscClientes) lista.get(rowIndex);
           if(columnIndex == 0) return clientes.getIdmscClientes();
           if(columnIndex == 1) return clientes.getMscNome();
           if(columnIndex == 2) return clientes.getMscCpf();
           if(columnIndex == 3) return clientes.getMscTelefone();
           
    
       return "";
    }
      
    public String getColumnName(int column) {
      if(column == 0) return "codigo";
      if(column == 1) return "Nome";
      if(column == 2) return "Cpf";
      if(column == 3) return "Telefone";
      return "";
    }    
}
