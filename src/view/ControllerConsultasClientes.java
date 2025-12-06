/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscClientes;
import bean.MscUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerConsultasClientes extends AbstractTableModel {

    private List lstMscClientes;

    public void setList(List lstMscClientes) {
        this.lstMscClientes = lstMscClientes;
        this.fireTableDataChanged();
        
    }
    
    public MscClientes getBean(int rowIndex) {
        return (MscClientes) lstMscClientes.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstMscClientes.size();
                
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscClientes mscClientes = (MscClientes) lstMscClientes.get( rowIndex);
        if ( columnIndex == 0 ){
            return mscClientes.getIdmscClientes();
        } else if (columnIndex ==1) {
            return mscClientes.getMscNome();        
        } else if (columnIndex ==2) {
            return mscClientes.getMscCpf();
        } 
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Nome";         
        } else if ( columnIndex == 2) {
            return "CPF";
        } 
        return "";
    }
}
