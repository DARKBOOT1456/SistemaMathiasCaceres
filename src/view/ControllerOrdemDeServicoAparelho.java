/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bean.MscOrdemServicoAparelho;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Marcos
 */
public class ControllerOrdemDeServicoAparelho extends AbstractTableModel {

    private List lstOrdemDeServicoAparelho;

    public void setList(List lstOrdemDeServicoAparelho) {
        this.lstOrdemDeServicoAparelho = lstOrdemDeServicoAparelho;
        
        this.fireTableDataChanged();
    }
    
    public MscOrdemServicoAparelho getBean(int rowIndex) {
        return (MscOrdemServicoAparelho) lstOrdemDeServicoAparelho.get(rowIndex);
    }

    public void addBean(MscOrdemServicoAparelho ordemDeServicoAparelho) {
        lstOrdemDeServicoAparelho.add(ordemDeServicoAparelho);
        this.fireTableDataChanged();
    }
    
    public void removeBean(int rowIndex) {
         lstOrdemDeServicoAparelho.remove(rowIndex);
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return lstOrdemDeServicoAparelho.size();                
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MscOrdemServicoAparelho ordemDeServicoAparelho = (MscOrdemServicoAparelho)  lstOrdemDeServicoAparelho.get( rowIndex);
        if ( columnIndex == 0 ){
            return ordemDeServicoAparelho.getMscAparelhos().getIdmscAparelhos();
        } else if (columnIndex ==1) {
            return ordemDeServicoAparelho.getMscAparelhos().getMscMarca();
        } else if (columnIndex ==2) {
            return ordemDeServicoAparelho.getMscAparelhos().getMscModelo();
        } else if (columnIndex ==3) { 
              return ordemDeServicoAparelho.getMscServicos().getMscNomeServico();
        } else if (columnIndex ==4) {
            return ordemDeServicoAparelho.getMscQuantidade();
        }else if (columnIndex ==5) {
             return ordemDeServicoAparelho.getMscObservacoes();
        }else if (columnIndex ==6) {
            return ordemDeServicoAparelho.getMscValorUnitario();
        }else if (columnIndex ==7) {
            return ordemDeServicoAparelho.getMscValorUnitario()*ordemDeServicoAparelho.getMscQuantidade();
        }
        return ""; 
    }

    @Override
    public String getColumnName(int columnIndex) {
        if ( columnIndex == 0) {
            return "Código";
        } else if ( columnIndex == 1) {
            return "Aparelho";  
        }else if ( columnIndex == 2) {
            return "Marca";
        }else if ( columnIndex == 3) {
            return "Serviço";
        } else if ( columnIndex == 4) {
            return "Quantidade";
        } else if ( columnIndex == 5) {
            return "Observação";
        }else if ( columnIndex == 6) {
            return "Valor Unitário";
        }  else if ( columnIndex == 7) {
            return "Total";
        }
        return "";
    }
}
