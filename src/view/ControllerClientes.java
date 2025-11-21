/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import bean.ClientesBean;
import dao.ClientesDao;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerClientes extends AbstractTableModel {
    
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
        ClientesBean clientes = (ClientesBean) lista.get(rowIndex);
           if(columnIndex == 0) return clientes.getMsc_idclientes();
           if(columnIndex == 1) return clientes.getMsc_nome();
           if(columnIndex == 2) return clientes.getMsc_cpf();
           if(columnIndex == 3) return clientes.getMsc_telefone();
           
    
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
