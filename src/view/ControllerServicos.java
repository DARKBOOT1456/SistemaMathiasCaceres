/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import bean.MscServicos;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author mathi
 */
public class ControllerServicos extends AbstractTableModel {
   private List lista;
    
    public void setList(List lista){
         this.lista = lista;
    }
    
    public MscServicos getBean(int rowIndex){
       return (MscServicos) lista.get(rowIndex);
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
        MscServicos servicos = (MscServicos) lista.get(rowIndex);
           if(columnIndex == 0) return servicos.getIdmscServicos();
           if(columnIndex == 1) return servicos.getMscNomeServico();
           if(columnIndex == 2) return servicos.getMscCategoria();
           if(columnIndex == 3) return servicos.getMscDataCadastro();
           
    
       return "";
    }
      
    public String getColumnName(int column) {
      if(column == 0) return "codigo";
      if(column == 1) return "Nome do Serviço";
      if(column == 2) return "Categoria";
      if(column == 3) return "Data de Cadastro";
      return "";
    }    
}
