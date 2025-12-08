/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscAparelhos;
import bean.MscUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerConsultasAparelhos extends AbstractTableModel {

    private List lstMscAparelhos;

    public void setList(List lstMscAparelhos) {
        this.lstMscAparelhos = lstMscAparelhos;
        this.fireTableDataChanged();
        
    }
    
    public MscAparelhos getBean(int rowIndex) {
        return (MscAparelhos) lstMscAparelhos.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstMscAparelhos.size();
                
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscAparelhos aparelhos = (MscAparelhos) lstMscAparelhos.get( rowIndex);
        if ( columnIndex == 0 ){
            return aparelhos.getIdmscAparelhos();
        } else if (columnIndex ==1) {
            return aparelhos.getMscMarca();        
        } else if (columnIndex ==2) {
            return aparelhos.getMscValorUnitario();
        } 
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Marca";         
        } else if ( columnIndex == 2) {
            return "Valor Unitario";
        } 
        return "";
    }
}
