/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.util.Date;

/**
 *
 * @author mathi
 */
public class Ordem_servicoBean {

    

     private int msc_idOrdensServico;
    private Date msc_data_inicio;
    private String msc_status;
    private int msc_fkCliente;
    private int msc_fkUsuarios;
    private int msc_fkServico;
    private String msc_Tecnico_responsavel;
    private String msc_valorTotal;

    /**
     * @return the msc_idOrdensServico
     */
    public int getMsc_idOrdensServico() {
        return msc_idOrdensServico;
    }

    /**
     * @param msc_idOrdensServico the msc_idOrdensServico to set
     */
    public void setMsc_idOrdensServico(int msc_idOrdensServico) {
        this.msc_idOrdensServico = msc_idOrdensServico;
    }

    /**
     * @return the msc_data_inicio
     */
    public Date getMsc_data_inicio() {
        return msc_data_inicio;
    }

    /**
     * @param msc_data_inicio the msc_data_inicio to set
     */
    public void setMsc_data_inicio(Date msc_data_inicio) {
        this.msc_data_inicio = msc_data_inicio;
    }

    /**
     * @return the msc_status
     */
    public String getMsc_status() {
        return msc_status;
    }

    /**
     * @param msc_status the msc_status to set
     */
    public void setMsc_status(String msc_status) {
        this.msc_status = msc_status;
    }

    /**
     * @return the msc_fkCliente
     */
    public int getMsc_fkCliente() {
        return msc_fkCliente;
    }

    /**
     * @param msc_fkCliente the msc_fkCliente to set
     */
    public void setMsc_fkCliente(int msc_fkCliente) {
        this.msc_fkCliente = msc_fkCliente;
    }

    /**
     * @return the msc_fkUsuarios
     */
    public int getMsc_fkUsuarios() {
        return msc_fkUsuarios;
    }

    /**
     * @param msc_fkUsuarios the msc_fkUsuarios to set
     */
    public void setMsc_fkUsuarios(int msc_fkUsuarios) {
        this.msc_fkUsuarios = msc_fkUsuarios;
    }

    /**
     * @return the msc_fkServico
     */
    public int getMsc_fkServico() {
        return msc_fkServico;
    }

    /**
     * @param msc_fkServico the msc_fkServico to set
     */
    public void setMsc_fkServico(int msc_fkServico) {
        this.msc_fkServico = msc_fkServico;
    }

    /**
     * @return the msc_Tecnico_responsavel
     */
    public String getMsc_Tecnico_responsavel() {
        return msc_Tecnico_responsavel;
    }

    /**
     * @param msc_Tecnico_responsavel the msc_Tecnico_responsavel to set
     */
    public void setMsc_Tecnico_responsavel(String msc_Tecnico_responsavel) {
        this.msc_Tecnico_responsavel = msc_Tecnico_responsavel;
    }

    /**
     * @return the msc_valorTotal
     */
    public String getMsc_valorTotal() {
        return msc_valorTotal;
    }

    /**
     * @param msc_valorTotal the msc_valorTotal to set
     */
    public void setMsc_valorTotal(String msc_valorTotal) {
        this.msc_valorTotal = msc_valorTotal;
    }

    /**
     * @return the idOrdensServico
     */
}