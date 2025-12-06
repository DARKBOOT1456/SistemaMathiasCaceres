/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscServicos;
import bean.MscUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerConsultasServicos extends AbstractTableModel {

    private List lstServicos;

    public void setList(List lstServicos) {
        this.lstServicos = lstServicos;
        this.fireTableDataChanged();
        
    }
    
    public MscServicos getBean(int rowIndex) {
        return (MscServicos) lstServicos.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstServicos.size();
                
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscServicos mscServicos = (MscServicos) lstServicos.get( rowIndex);
        if ( columnIndex == 0 ){
            return mscServicos.getIdmscServicos();
        } else if (columnIndex ==1) {
            return mscServicos.getMscNomeServico();        
        } else if (columnIndex ==2) {
            return mscServicos.getMscValor();
        } 
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Nome do Serviço";         
        } else if ( columnIndex == 2) {
            return "Valor";
        } 
        return "";
    }
}
