/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscOrdensServico;
import bean.MscUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerConsultasOrdemDeServico extends AbstractTableModel {

    private List lstMscOrdensServico;

    public void setList(List lstMscAparelhos) {
        this.lstMscOrdensServico = lstMscAparelhos;
        this.fireTableDataChanged();
        
    }
    
    public MscOrdensServico getBean(int rowIndex) {
        return (MscOrdensServico) lstMscOrdensServico.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstMscOrdensServico.size();
                
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscOrdensServico ordensServico = (MscOrdensServico) lstMscOrdensServico.get( rowIndex);
        if ( columnIndex == 0 ){
            return ordensServico.getIdmscOrdensServico();
        } else if (columnIndex ==1) {
            return ordensServico.getMscTecnicoResponsavel();            
        } else if (columnIndex ==2) {
            return ordensServico.getMscValorTotal();
        } 
        
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código Da Nota";
        } else if ( columnIndex == 1) {
            return "Tecnico Responsavel";               
        }  else if ( columnIndex == 2) {
            return "Valor Total";
        } 
        return "";
    }
}
