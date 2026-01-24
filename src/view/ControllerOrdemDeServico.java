/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscOrdensServico;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerOrdemDeServico extends AbstractTableModel {

    private List lstOrdemDeServico;

    public void setList(List lstOrdemDeServico) {
        this.lstOrdemDeServico = lstOrdemDeServico;
    }
    
    public MscOrdensServico getBean(int rowIndex) {
        return (MscOrdensServico) lstOrdemDeServico.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstOrdemDeServico.size();
                
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscOrdensServico mscOrdensServico = (MscOrdensServico) lstOrdemDeServico.get( rowIndex);
        if ( columnIndex == 0 ){
            return mscOrdensServico.getIdmscOrdensServico();
        } else if (columnIndex ==1) {
            return mscOrdensServico.getMscDataInicio();        
        } else if (columnIndex ==2) {
            return mscOrdensServico.getMscValorTotal();
        }  else if (columnIndex == 3) {
        
        if (mscOrdensServico.getMscClientes() == null) {
            return "CLIENTE NÃO SELECIONADO";
        }
        return mscOrdensServico.getMscClientes().getMscNome() != null ? 
               mscOrdensServico.getMscClientes().getMscNome() : "Cliente sem nome";
    }
        return ""; 
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Data";         
        } else if ( columnIndex == 2) {
            return "Total";
        } else if ( columnIndex == 3) {
            return "Cliente";
        } 
        return "";
    }
}
