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
public class ServicosBean {
    private int msc_idServicos;
    private String msc_nome_servico;
    private String msc_descricao;
    private Double msc_valor;
    private String msc_tempo_estimado;
    private String msc_categoria;
    private Date msc_data_cadastro;

    /**
     * @return the msc_idServicos
     */
    public int getMsc_idServicos() {
        return msc_idServicos;
    }

    /**
     * @param msc_idServicos the msc_idServicos to set
     */
    public void setMsc_idServicos(int msc_idServicos) {
        this.msc_idServicos = msc_idServicos;
    }

    /**
     * @return the msc_nome_servico
     */
    public String getMsc_nome_servico() {
        return msc_nome_servico;
    }

    /**
     * @param msc_nome_servico the msc_nome_servico to set
     */
    public void setMsc_nome_servico(String msc_nome_servico) {
        this.msc_nome_servico = msc_nome_servico;
    }

    /**
     * @return the msc_descricao
     */
    public String getMsc_descricao() {
        return msc_descricao;
    }

    /**
     * @param msc_descricao the msc_descricao to set
     */
    public void setMsc_descricao(String msc_descricao) {
        this.msc_descricao = msc_descricao;
    }

    /**
     * @return the msc_valor
     */
    public Double getMsc_valor() {
        return msc_valor;
    }

    /**
     * @param msc_valor the msc_valor to set
     */
    public void setMsc_valor(Double msc_valor) {
        this.msc_valor = msc_valor;
    }

    /**
     * @return the msc_tempo_estimado
     */
    public String getMsc_tempo_estimado() {
        return msc_tempo_estimado;
    }

    /**
     * @param msc_tempo_estimado the msc_tempo_estimado to set
     */
    public void setMsc_tempo_estimado(String msc_tempo_estimado) {
        this.msc_tempo_estimado = msc_tempo_estimado;
    }

    /**
     * @return the msc_categoria
     */
    public String getMsc_categoria() {
        return msc_categoria;
    }

    /**
     * @param msc_categoria the msc_categoria to set
     */
    public void setMsc_categoria(String msc_categoria) {
        this.msc_categoria = msc_categoria;
    }

    /**
     * @return the msc_data_cadastro
     */
    public Date getMsc_data_cadastro() {
        return msc_data_cadastro;
    }

    /**
     * @param msc_data_cadastro the msc_data_cadastro to set
     */
    public void setMsc_data_cadastro(Date msc_data_cadastro) {
        this.msc_data_cadastro = msc_data_cadastro;
    }

    /**
     * @return the idServicos
     */
}