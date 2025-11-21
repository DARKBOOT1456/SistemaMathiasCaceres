/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
<<<<<<< HEAD
import bean.ClientesBean;
import dao.ClientesDao;
=======
import bean.MscClientes;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerClientes extends AbstractTableModel {
    
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
    public MscClientes getBean(int rowIndex){
        return (MscClientes) lista.get(rowIndex);
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
        ClientesBean clientes = (ClientesBean) lista.get(rowIndex);
           if(columnIndex == 0) return clientes.getMsc_idclientes();
           if(columnIndex == 1) return clientes.getMsc_nome();
           if(columnIndex == 2) return clientes.getMsc_cpf();
           if(columnIndex == 3) return clientes.getMsc_telefone();
=======
        MscClientes clientes = (MscClientes) lista.get(rowIndex);
           if(columnIndex == 0) return clientes.getIdmscClientes();
           if(columnIndex == 1) return clientes.getMscNome();
           if(columnIndex == 2) return clientes.getMscCpf();
           if(columnIndex == 3) return clientes.getMscTelefone();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
           
    
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
