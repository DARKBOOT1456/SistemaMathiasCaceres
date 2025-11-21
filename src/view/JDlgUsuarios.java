 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

<<<<<<< HEAD
import bean.UsuariosBean;
import dao.UsuariosDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
=======

import bean.MscUsuarios;
import dao.UsuariosDao;
import tools.Util;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e

/**
 *
 * @author mathi
 * 
 */
<<<<<<< HEAD
public class JDlgUsuarios extends javax.swing.JDialog {

=======

public class JDlgUsuarios extends javax.swing.JDialog {

    private boolean incluir;
    
    private boolean validarDataNascimento(String dataStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false); 
    
    try {
        Date data = sdf.parse(dataStr);
        Calendar cal = Calendar.getInstance();
        Date hoje = cal.getTime();
        
        if(data.after(hoje)) {
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
    /**
     * Creates new form JDlgUsuarios
     */
    public JDlgUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents(); 
        setTitle("Cadastro de Usuarios");
<<<<<<< HEAD
        setLocationRelativeTo(null); //centraliza o frame
        habilitar(false);
    }

    public void habilitar (boolean valor){
    jBtnConfirmar.setEnabled(valor);
        jBtnCancelar.setEnabled(valor);
        jTxtCodigo.setEnabled(valor);
        jTxtNome.setEnabled(valor);
        jTxtApelido.setEnabled(valor);
        jFmtCpf.setEnabled(valor);
        jFmtDataNascimento.setEnabled(valor);
        jPwdSenha.setEnabled(valor);
        jCboNivel.setEnabled(valor);
        jChbAtivo.setEnabled(valor);
        
        jBtnIncluir.setEnabled(!valor);
        jBtnAlterar.setEnabled(!valor);
        jBtnExcluir.setEnabled(!valor);
        jBtnPesquisar.setEnabled(!valor);
    }
    
    public void limpar(){
    
        jTxtCodigo.setText("");
        jTxtNome.setText("");
        jTxtApelido.setText("");
        jFmtCpf.setText("");
        jFmtDataNascimento.setText("");
        jPwdSenha.setText("");
         jCboNivel.setSelectedIndex(-1);
        jChbAtivo.setSelected(false);
        
        
    }
  
    
    
=======
        setLocationRelativeTo(null); 
       Util.habilitar(false, jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo,    jBtnConfirmar, jBtnCancelar);

    try {
    jFmtDataNascimento.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.MaskFormatter("##/##/####")
        )
    );
} catch (java.text.ParseException e) {
    e.printStackTrace();
}
    }
    
    public void beanView(MscUsuarios usuarios) {
        jTxtCodigo.setText(Util.intToStr(usuarios.getIdmscUsuarios()));
         jTxtNome.setText(usuarios.getMscNome());
         jTxtApelido.setText(usuarios.getMscApelido());
         jFmtCpf.setText(usuarios.getMscCpf());
         jFmtDataNascimento.setText(Util.dateToStr(usuarios.getMscDataNascimento()));
         jPwdSenha.setText(usuarios.getMscSenha());
        jCboNivel.setSelectedItem(usuarios.getMscNivel());
         jChbAtivo.setSelected(usuarios.getMscAtivo().equals("S"));
   
    }
   
    public MscUsuarios viewBean(){
         MscUsuarios usuarios = new MscUsuarios();
    int codigo = Util.strToInt(jTxtCodigo.getText());
    usuarios.setIdmscUsuarios(codigo);
    usuarios.setMscNome(jTxtNome.getText());
    usuarios.setMscApelido(jTxtApelido.getText());
    usuarios.setMscCpf(jFmtCpf.getText());
    
    // CORREÇÃO DA DATA DE NASCIMENTO
    String dataTexto = jFmtDataNascimento.getText().trim();
    Date dataNascimento;
    
    if (dataTexto.isEmpty() || dataTexto.equals("  /  /    ")) {
        dataNascimento = new Date(); 
    } else {
        dataNascimento = Util.strToDate(dataTexto);
        if (dataNascimento == null) {
            dataNascimento = new Date();
        }
    }
    usuarios.setMscDataNascimento(dataNascimento);
    
    usuarios.setMscSenha(jPwdSenha.getText());
    usuarios.setMscNivel(jCboNivel.getSelectedItem().toString());
    
    if(jChbAtivo.isSelected() == true){
        usuarios.setMscAtivo("S");
    } else {
        usuarios.setMscAtivo("N");          
    } 
   
        return usuarios;
    }

>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnIncluir = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTxtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtApelido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jBtnPesquisar = new javax.swing.JButton();
        jFmtDataNascimento = new javax.swing.JFormattedTextField();
        jPwdSenha = new javax.swing.JPasswordField();
        jFmtCpf = new javax.swing.JFormattedTextField();
<<<<<<< HEAD
        jCboNivel = new javax.swing.JComboBox<>();
=======
        jCboNivel = new javax.swing.JComboBox<String>();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jChbAtivo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
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

        jLabel1.setText("Código");

        jTxtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodigoActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome");

        jTxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNomeActionPerformed(evt);
            }
        });

        jLabel5.setText("Apelido");

        jTxtApelido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtApelidoActionPerformed(evt);
            }
        });

        jLabel6.setText("CPF");

        jLabel2.setText("Data de Nascimento");

        jLabel7.setText("Senha");

        jLabel9.setText("Nivel");

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
=======
        jFmtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFmtDataNascimentoActionPerformed(evt);
            }
        });

>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jFmtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFmtCpfActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jCboNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Funcionário", "Convidado" }));
=======
        jCboNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Funcionário", "Convidado" }));
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        jCboNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboNivelActionPerformed(evt);
            }
        });

        jChbAtivo.setText("Ativo");
        jChbAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChbAtivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(155, 155, 155)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFmtDataNascimento)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtApelido)
                                    .addComponent(jCboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFmtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(211, 211, 211)
<<<<<<< HEAD
                                .addComponent(jChbAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))))
=======
                                .addComponent(jChbAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnIncluir)
                            .addComponent(jBtnAlterar))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnConfirmar)
                            .addComponent(jBtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
<<<<<<< HEAD
                .addGap(0, 66, Short.MAX_VALUE))
=======
                .addGap(0, 64, Short.MAX_VALUE))
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFmtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFmtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jChbAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConfirmar)
                    .addComponent(jBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodigoActionPerformed

    private void jTxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNomeActionPerformed

    private void jTxtApelidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtApelidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtApelidoActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        habilitar(true);
        limpar();
=======
         Util.habilitar(true, jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo,    jBtnConfirmar, jBtnCancelar);
                
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
           Util.limpar(jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo);
            incluir = true;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
         int resp = JOptionPane.showConfirmDialog(null, "Deseja excluir ?");
        if(resp == JOptionPane.YES_OPTION){
            UsuariosBean usuarios = new UsuariosBean();
            int cod = Integer.parseInt(jTxtCodigo.getText());
        usuarios.setMsc_idusuarios(cod);
        usuarios.setMsc_nome(jTxtNome.getText());
        usuarios.setMsc_apelido(jTxtApelido.getText());
        usuarios.setMsc_cpf(jFmtCpf.getText());
        usuarios.setMsc_dataNascimento(null); //jTxtApelido.getText());
        usuarios.setMsc_senha(jPwdSenha.getText());
       usuarios.setMsc_nivel(jCboNivel.getSelectedItem().toString());

        if (jChbAtivo.isSelected() == true) {
            usuarios.setMsc_ativo("S");
        } else {
            usuarios.setMsc_ativo("N");
        }
            
            UsuariosDao usuariosDao = new UsuariosDao();
            usuariosDao.delete(usuarios);
            
             JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        }
        limpar();
=======
        if (Util.perguntar("Deseja Excluir?") == true){
      
          
      }
          UsuariosDao usuariosDAO = new UsuariosDao();
          
         usuariosDAO.delete( viewBean());
      
      Util.limpar(jTxtCodigo, jTxtNome, jTxtApelido, 
                jFmtCpf, jFmtDataNascimento, 
                jPwdSenha,jCboNivel,jChbAtivo);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jChbAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChbAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jChbAtivoActionPerformed

    private void jCboNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboNivelActionPerformed

    private void jFmtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFmtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFmtCpfActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
       //desabilitar();
        habilitar(false);
    UsuariosBean usuarios = new UsuariosBean();
    int cod = Integer.parseInt(jTxtCodigo.getText());
    usuarios.setMsc_idusuarios(cod);
    usuarios.setMsc_nome(jTxtNome.getText());
    usuarios.setMsc_apelido(jTxtApelido.getText());
    usuarios.setMsc_cpf(jFmtCpf.getText());

    String dataTexto = jFmtDataNascimento.getText().trim();
    if (dataTexto.isEmpty() || dataTexto.contains(" ")) {
        JOptionPane.showMessageDialog(this, "A data de nascimento é obrigatória!");
        return;
    }
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse(dataTexto);
        usuarios.setMsc_dataNascimento(data);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Data inválida!");
        return;
    }

    usuarios.setMsc_senha(jPwdSenha.getText());
    usuarios.setMsc_nivel(jCboNivel.getSelectedItem().toString());
    usuarios.setMsc_ativo(jChbAtivo.isSelected() ? "S" : "N");


     UsuariosDao dao = new UsuariosDao();
    
     UsuariosBean existente = (UsuariosBean) dao.list(cod);
    if (existente == null) {
        dao.insert(usuarios);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
    } else {
        dao.update(usuarios);
        JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!");
    }

    

    limpar();
=======
        
         if (!validarDataNascimento(jFmtDataNascimento.getText())) {
        return; 
    }
         
        UsuariosDao usuariosDAO = new UsuariosDao();
       if(incluir == true){
        usuariosDAO.insert(viewBean());
    } else{
        usuariosDAO.update(viewBean());
    }
        
         Util.habilitar(false, jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo,    jBtnConfirmar, jBtnCancelar);
                
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        //desabilitar();
        
        habilitar(false);
        limpar();
=======
       Util.habilitar(false, jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo,    jBtnConfirmar, jBtnCancelar);
                
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
         Util.limpar(jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        habilitar(true);
          

=======
        Util.habilitar(true, jTxtCodigo,jTxtNome,  jTxtApelido,
                jFmtCpf, jFmtDataNascimento, jPwdSenha, jCboNivel, 
                jChbAtivo,    jBtnConfirmar, jBtnCancelar);
                
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
           incluir = false;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
         String id = JOptionPane.showInputDialog(null, "Entre com o código");
        int codigo = Integer.parseInt(id);
        UsuariosDao usuariosDao = new UsuariosDao();
        UsuariosBean usuarios = (UsuariosBean) usuariosDao.list(codigo);
        if (usuarios ==  null){
            JOptionPane.showMessageDialog(null, "Codigo Não encontrado");
        }
        else{
            jTxtCodigo.setText(id);
        jTxtNome.setText(usuarios.getMsc_nome());
        jTxtApelido.setText(usuarios.getMsc_apelido());
        jFmtCpf.setText(usuarios.getMsc_cpf());
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dataFormatada = sdf.format(usuarios.getMsc_dataNascimento());
    jFmtDataNascimento.setText(dataFormatada);
        jPwdSenha.setText(usuarios.getMsc_senha());
      jCboNivel.setSelectedItem(usuarios.getMsc_nivel());

        if(usuarios.getMsc_ativo().equals("S")){
            jChbAtivo.setSelected(true);
        }else{
            jChbAtivo.setSelected(false);
        }
        }
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

=======
         JDlgUsuariosPesquisar jDlgUsuariosPesquisar =  new JDlgUsuariosPesquisar(null,true);
         jDlgUsuariosPesquisar.setTelaAnterior(this);
        jDlgUsuariosPesquisar.setVisible(true);
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jFmtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFmtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFmtDataNascimentoActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgUsuarios dialog = new JDlgUsuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jCboNivel;
    private javax.swing.JCheckBox jChbAtivo;
    private javax.swing.JFormattedTextField jFmtCpf;
    private javax.swing.JFormattedTextField jFmtDataNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPwdSenha;
    private javax.swing.JTextField jTxtApelido;
    private javax.swing.JTextField jTxtCodigo;
    private javax.swing.JTextField jTxtNome;
    // End of variables declaration//GEN-END:variables
}
