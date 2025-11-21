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
public class Ordem_servico_AparelhoBean {
    private int msc_idOsAparelho;
    private int msc_quantidade;
    private Double msc_valor_unitario;
    private int msc_fkAparelho;
    private String msc_observacoes;
    private int msc_fkOs;

    /**
     * @return the msc_idOsAparelho
     */
    public int getMsc_idOsAparelho() {
        return msc_idOsAparelho;
    }

    /**
     * @param msc_idOsAparelho the msc_idOsAparelho to set
     */
    public void setMsc_idOsAparelho(int msc_idOsAparelho) {
        this.msc_idOsAparelho = msc_idOsAparelho;
    }

    /**
     * @return the msc_quantidade
     */
    public int getMsc_quantidade() {
        return msc_quantidade;
    }

    /**
     * @param msc_quantidade the msc_quantidade to set
     */
    public void setMsc_quantidade(int msc_quantidade) {
        this.msc_quantidade = msc_quantidade;
    }

    /**
     * @return the msc_valor_unitario
     */
    public Double getMsc_valor_unitario() {
        return msc_valor_unitario;
    }

    /**
     * @param msc_valor_unitario the msc_valor_unitario to set
     */
    public void setMsc_valor_unitario(Double msc_valor_unitario) {
        this.msc_valor_unitario = msc_valor_unitario;
    }

    /**
     * @return the msc_fkAparelho
     */
    public int getMsc_fkAparelho() {
        return msc_fkAparelho;
    }

    /**
     * @param msc_fkAparelho the msc_fkAparelho to set
     */
    public void setMsc_fkAparelho(int msc_fkAparelho) {
        this.msc_fkAparelho = msc_fkAparelho;
    }

    /**
     * @return the msc_observacoes
     */
    public String getMsc_observacoes() {
        return msc_observacoes;
    }

    /**
     * @param msc_observacoes the msc_observacoes to set
     */
    public void setMsc_observacoes(String msc_observacoes) {
        this.msc_observacoes = msc_observacoes;
    }

    /**
     * @return the msc_fkOs
     */
    public int getMsc_fkOs() {
        return msc_fkOs;
    }

    /**
     * @param msc_fkOs the msc_fkOs to set
     */
    public void setMsc_fkOs(int msc_fkOs) {
        this.msc_fkOs = msc_fkOs;
    }

    /**
     * @return the idOsAparelho
     */
}