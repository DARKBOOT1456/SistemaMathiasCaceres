/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

<<<<<<< HEAD
import bean.AparelhosBean;
import dao.AparelhosDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
=======
import bean.MscAparelhos;
import dao.AparelhosDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import tools.Util;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e

/**
 *
 * @author mathi
 */
public class JDlgAparelhos extends javax.swing.JDialog {

    /**
     * Creates new form JDlgAparelhos
     */
<<<<<<< HEAD
    
    boolean pesquisado = false;
     boolean incluir = false;
    
    private MaskFormatter  mascaraDataEntrad;
    
=======
    private boolean incluir;

    private boolean validarData(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date data = sdf.parse(dataStr);
            Calendar cal = Calendar.getInstance();
            Date hoje = cal.getTime();

            if (data.after(hoje)) {
                JOptionPane.showMessageDialog(this, "A data de nascimento não pode ser futura!");
                return false;
            }

            return true;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida! Digite no formato dd/MM/yyyy");
            return false;
        }
    }

>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    public JDlgAparelhos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Cadastro de Aparelhos");
<<<<<<< HEAD
        setLocationRelativeTo(null); //centraliza o frame
        habilitar(false);
        
         try {   
             mascaraDataEntrad = new MaskFormatter("##/##/####"); 
            jFmtData.setFormatterFactory(new DefaultFormatterFactory(mascaraDataEntrad));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgAparelhos.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
     public void habilitar (boolean valor){
    jBtnConfirmar.setEnabled(valor);
        jBtnCancelar.setEnabled(valor);
        jTxtCod.setEnabled(valor);
        jTxtMarca.setEnabled(valor);
        jTxtModelo.setEnabled(valor);
        jTxtNumero.setEnabled(valor);
        jCboTipo.setEnabled(valor);
        jCboChip.setEnabled(valor);
        jFmtData.setEnabled(valor);
        jTxtCor.setEnabled(valor);
        
        jBtnIncluir.setEnabled(!valor);
        jBtnAlterar.setEnabled(!valor);
        jBtnExcluir.setEnabled(!valor);
        jBtnPesquisar.setEnabled(!valor);
    }
    
     
     public void beanView(AparelhosBean aparelhos){
                
         jTxtCod.setText(String.valueOf(aparelhos.getMsc_idAparelhos()));
         if (aparelhos.getMsc_data_entrada() != null) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    jFmtData.setText(sdf.format(aparelhos.getMsc_data_entrada()));
} else {
    jFmtData.setText(""); 
}
        jTxtCor.setText(String.valueOf(aparelhos.getMsc_modelo()));
        jTxtMarca.setText(aparelhos.getMsc_marca());
        jTxtModelo.setText(aparelhos.getMsc_modelo());
        jTxtNumero.setText(aparelhos.getMsc_numero_de_serie());
        jCboTipo.setSelectedItem(aparelhos.getMsc_tipodeEquipamento());
       jCboChip.setSelectedItem(aparelhos.getMsc_chipRetirado());  
       
=======
        setLocationRelativeTo(null);
        Util.habilitar(false, jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip,
                jTxtCor, jBtnConfirmar, jBtnCancelar);

        try {
            jFmtData.setFormatterFactory(
                    new javax.swing.text.DefaultFormatterFactory(
                            new javax.swing.text.MaskFormatter("##/##/####")
                    )
            );
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

    }

    public void beanView(MscAparelhos aparelhosBean) {
        jTxtCod.setText(Util.intToStr(aparelhosBean.getIdmscAparelhos()));
        jTxtMarca.setText(aparelhosBean.getMscMarca());
        jTxtNumero.setText(aparelhosBean.getMscNumeroDeSerie());
        jTxtModelo.setText(aparelhosBean.getMscModelo());
        jFmtData.setText(Util.dateToStr(aparelhosBean.getMscDataEntrada()));
        jTxtCor.setText(aparelhosBean.getMscCor());
        jCboTipo.setSelectedItem(aparelhosBean.getMscTipodeEquipamento());
        String chip = aparelhosBean.getMscChipRetirado();
        jCboChip.setSelectedItem(chip.equals("S") ? "Sim" : "Não");

    }

    public MscAparelhos viewBean() {
        MscAparelhos aparelhos = new MscAparelhos();
        int codigo = Util.strToInt(jTxtCod.getText());
        aparelhos.setIdmscAparelhos(codigo);
        aparelhos.setMscMarca(jTxtMarca.getText());
        aparelhos.setMscNumeroDeSerie(jTxtNumero.getText());
        aparelhos.setMscModelo(jTxtModelo.getText());
        
    
    String dataTexto = jFmtData.getText().trim();
    Date dataEntrada;
    
    if (dataTexto.isEmpty() || dataTexto.equals("  /  /    ")) {
        dataEntrada = new Date(); 
    } else {
        dataEntrada = Util.strToDate(dataTexto);
       
        if (dataEntrada == null) {
            dataEntrada = new Date();
        }
    }
    aparelhos.setMscDataEntrada(dataEntrada);
    
    aparelhos.setMscDataEntrada(dataEntrada);
        aparelhos.setMscCor(jTxtCor.getText());
        aparelhos.setMscTipodeEquipamento(jCboTipo.getSelectedItem().toString());
        aparelhos.setMscChipRetirado(jCboChip.getSelectedItem().toString());

        return aparelhos;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTxtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTxtModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTxtCod = new javax.swing.JTextField();
<<<<<<< HEAD
        jCboChip = new javax.swing.JComboBox<>();
=======
        jCboChip = new javax.swing.JComboBox<String>();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jFmtData = new javax.swing.JFormattedTextField();
        jBtnIncluir = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
<<<<<<< HEAD
        jCboTipo = new javax.swing.JComboBox<>();
=======
        jCboTipo = new javax.swing.JComboBox<String>();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jLabel7 = new javax.swing.JLabel();
        jTxtCor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Marca");

        jTxtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtMarcaActionPerformed(evt);
            }
        });

        jLabel3.setText("Modelo");

        jLabel11.setText("Tipo de equipamento");

        jTxtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtModeloActionPerformed(evt);
            }
        });

        jLabel4.setText("Numero de Serie");

        jTxtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNumeroActionPerformed(evt);
            }
        });

        jLabel5.setText("Data entrada");

        jLabel6.setText("Chip Retirado");

        jLabel1.setText("Código");

        jTxtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jCboChip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Retirado", "Não retirado", "Sem chip", "Sem porta chip", "Apenas Chip", "Apenas Cartão de memoria", "Porta chip Retirado" }));
=======
        jCboChip.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Retirado", "Não retirado", "Sem chip", "Sem porta chip", "Apenas Chip", "Apenas Cartão de memoria", "Porta chip Retirado" }));
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jCboChip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboChipItemStateChanged(evt);
            }
        });
        jCboChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboChipActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/incluir.png"))); // NOI18N
=======
        jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnIncluir.setText("Incluir");
        jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIncluirActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
=======
        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alterar.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
=======
        jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/exit.png"))); // NOI18N
=======
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
=======
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
=======
        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisar.png"))); // NOI18N
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jCboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Tablet", "Notebook", "Computador", "Televisão", "Console de Videogame", "Outros Aparelhos Eletrônicos", " " }));
=======
        jCboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Celular", "Tablet", "Notebook", "Computador", "Televisão", "Console de Videogame", "Outros Aparelhos Eletrônicos", " " }));
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e

        jLabel7.setText("Cor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jBtnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnConfirmar)
                            .addComponent(jBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnPesquisar)
                            .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jCboTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTxtNumero))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboChip, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtModelo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFmtData)
                            .addComponent(jTxtCor))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jTxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jFmtData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jCboTipo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCboChip, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jTxtCor))
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConfirmar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtMarcaActionPerformed

    private void jTxtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtModeloActionPerformed

    private void jTxtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNumeroActionPerformed

    private void jTxtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodActionPerformed

    private void jCboChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboChipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboChipActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        habilitar(true);
         limpar();
         incluir = true;
        jTxtCod.grabFocus();
=======
        Util.habilitar(true, jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip,
                jTxtCor, jBtnConfirmar, jBtnCancelar);

        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip,
                jTxtCor);
        incluir = true;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        if (!pesquisado) {
        JOptionPane.showMessageDialog(this, "Você deve pesquisar um aparelho antes de alterar.");
        return;
    }
        habilitar(true);
        incluir = false;
         jTxtMarca.grabFocus();
         jTxtCod.setEnabled(false);
         
=======
        Util.habilitar(true, jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip,
                jTxtCor, jBtnConfirmar, jBtnCancelar);

        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        incluir = false;

>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        //desabilitar();
         habilitar(false);
    AparelhosBean aparelhos = new AparelhosBean();

    int cod = Integer.parseInt(jTxtCod.getText());
    aparelhos.setMsc_idAparelhos(cod);
    aparelhos.setMsc_cor(jTxtCor.getText());
    aparelhos.setMsc_marca(jTxtMarca.getText());
    aparelhos.setMsc_modelo(jTxtModelo.getText());
    aparelhos.setMsc_numero_de_serie(jTxtNumero.getText());
    aparelhos.setMsc_chipRetirado(jCboChip.getSelectedItem().toString());
    aparelhos.setMsc_tipodeEquipamento(jCboTipo.getSelectedItem().toString());

 
String textoData = jFmtData.getText();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);

    try {
        Date dataEntrada = sdf.parse(textoData);
        aparelhos.setMsc_data_entrada(dataEntrada);

        // Mostrar data formatada só para debug
        
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy.");
        return; 
    }
   

    AparelhosDao aparelhosDao = new AparelhosDao();
    if (incluir == true) {
        aparelhosDao.insert(aparelhos);
    } else {
        aparelhosDao.update(aparelhos);
    }

    limpar();

    }//GEN-LAST:event_jBtnConfirmarActionPerformed
    
    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        //desabilitar();
        habilitar(false);
        limpar();
        
=======
        
    if (!validarData(jFmtData.getText())) {
        return; 
    }
    
    
    AparelhosDao aparelhosDAO = new AparelhosDao();
    if(incluir == true){
        aparelhosDAO.insert(viewBean());
    } else{
        aparelhosDAO.update(viewBean());
    }
    
    
    Util.habilitar(false, jTxtCod, jTxtMarca, jTxtNumero,
            jFmtData, jCboTipo, jTxtModelo, jCboChip,
            jTxtCor, jBtnConfirmar, jBtnCancelar);

    Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
    Util.limpar(jTxtCod, jTxtMarca, jTxtNumero,
            jFmtData, jCboTipo, jTxtModelo, jCboChip,
            jTxtCor);
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        Util.habilitar(false, jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip, jTxtCor, jBtnConfirmar, jBtnCancelar);

        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jTxtMarca, jTxtNumero,
                jFmtData, jCboTipo, jTxtModelo, jCboChip,
                jTxtCor);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        if (!pesquisado) {
    JOptionPane.showMessageDialog(null, "Por favor, realize uma pesquisa antes de excluir.");
    return; 
}

int resp = JOptionPane.showConfirmDialog(null, "Deseja excluir ?");
if (resp == JOptionPane.YES_OPTION) {
    AparelhosBean aparelhos = new AparelhosBean();
    int cod = Integer.parseInt(jTxtCod.getText());
    aparelhos.setMsc_idAparelhos(cod);
    aparelhos.setMsc_cor(jTxtCor.getText());
    aparelhos.setMsc_marca(jTxtMarca.getText());
    aparelhos.setMsc_modelo(jTxtModelo.getText());
    aparelhos.setMsc_numero_de_serie(jTxtNumero.getText()); //jTxtApelido.getText());
   String dataTexto = jFmtData.getText();
    aparelhos.setMsc_tipodeEquipamento(jCboTipo.getSelectedItem().toString());
    aparelhos.setMsc_chipRetirado(jCboChip.getSelectedItem().toString());

    AparelhosDao aparelhosDao = new AparelhosDao();
    aparelhosDao.delete(aparelhos);
}

JOptionPane.showMessageDialog(null, "Aparelho excluído com sucesso!");
limpar();
pesquisado = false; 
=======
        if (Util.perguntar("Deseja Excluir?") == true) {
        AparelhosDao aparelhosDao = new AparelhosDao();
        aparelhosDao.delete(viewBean()); 
    }
    Util.limpar(jTxtCod, jTxtMarca, jTxtNumero,
            jFmtData, jCboTipo, jTxtModelo, jCboChip,
            jTxtCor);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jCboChipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboChipItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboChipItemStateChanged

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        JDlgAparelhosPesquisar jDlgAparelhosPesquisar =  new JDlgAparelhosPesquisar(null,true);
        jDlgAparelhosPesquisar.setTelaPai(this);
        jDlgAparelhosPesquisar.setVisible(true);
        pesquisado = true;
//         String id = JOptionPane.showInputDialog(null, "Entre com o código");
//        int codigo = Integer.parseInt(id);
//        AparelhosDao aparelhosDao = new AparelhosDao();
//        AparelhosBean aparelhos = (AparelhosBean) aparelhosDao.list(codigo);
//        if (aparelhos ==  null){
//            JOptionPane.showMessageDialog(null, "Codigo Não encontrado");
//        }
//        else{
//            jTxtCod.setText(id);
//       jTxtCor.setText(String.valueOf(aparelhos.getCor()));
//        jTxtMarca.setText(aparelhos.getMarca());
//        jTxtModelo.setText(aparelhos.getModelo());
//         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    String dataFormatada = sdf.format(aparelhos.getData_entrada());
//    jFmtData.setText(dataFormatada);
//        jTxtNumero.setText(aparelhos.getNumero_de_serie());
//        jCboTipo.setSelectedItem(aparelhos.getTipodeEquipamento());
//       jCboChip.setSelectedItem(aparelhos.getChipRetirado());
//
//      
//        }
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    
     public void limpar(){
        jTxtCod.setText("");
        jTxtCor.setText("");
        jTxtMarca.setText("");
        jTxtModelo.setText("");
        jTxtNumero.setText("");
        jCboTipo.setSelectedIndex(-1);
        jCboChip.setSelectedIndex(-1);
         jFmtData.setText("");
         
    }
=======
        JDlgAparelhosPesquisar jDlgAparelhosPesquisar = new JDlgAparelhosPesquisar(null, true);
        jDlgAparelhosPesquisar.setTelaAnterior(this);
        jDlgAparelhosPesquisar.setVisible(true);
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgAparelhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgAparelhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgAparelhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
<<<<<<< HEAD
    java.util.logging.Logger.getLogger(JDlgAparelhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
}
=======
            java.util.logging.Logger.getLogger(JDlgAparelhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgAparelhos dialog = new JDlgAparelhos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnIncluir;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JComboBox<String> jCboChip;
    private javax.swing.JComboBox<String> jCboTipo;
    private javax.swing.JFormattedTextField jFmtData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTxtCod;
    private javax.swing.JTextField jTxtCor;
    private javax.swing.JTextField jTxtMarca;
    private javax.swing.JTextField jTxtModelo;
    private javax.swing.JTextField jTxtNumero;
    // End of variables declaration//GEN-END:variables
}
