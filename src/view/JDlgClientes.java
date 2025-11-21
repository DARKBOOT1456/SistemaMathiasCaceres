/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

<<<<<<< HEAD
import bean.ClientesBean;
import dao.ClientesDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
=======
import bean.MscClientes;
import dao.ClientesDao;
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
public class JDlgClientes extends javax.swing.JDialog {

    /**
     * Creates new form JDlgClientes
     */
<<<<<<< HEAD
    
     boolean pesquisado = false;
     boolean incluir = false;
    
    private MaskFormatter  mascaraCep,mascaraCpf, mascaraData;
    
=======
    private boolean incluir;
    private boolean validarData(String dataStr) {
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
    public JDlgClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Cadastro de Clientes");
<<<<<<< HEAD
        setLocationRelativeTo(null); //centraliza o frame
        habilitar(false);
        
         try {   
              mascaraCpf = new MaskFormatter("###.###.###-##");
             mascaraData = new MaskFormatter("##/##/####"); 
              mascaraCep = new MaskFormatter("#####-###"); 
            jFmtDataNascimento.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
             jFmtCpf.setFormatterFactory(new DefaultFormatterFactory(mascaraCpf));
             jFmtDataCadastro.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
             jFtfCep.setFormatterFactory(new DefaultFormatterFactory(mascaraCep));
        } catch (ParseException ex) {
            Logger.getLogger(JDlgClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void habilitar (boolean valor){
    jBtnConfirmar.setEnabled(valor);
        jBtnCancelar.setEnabled(valor);
        jTxtCod.setEnabled(valor);
        jTxtBairro.setEnabled(valor);
        jTxtCidade.setEnabled(valor);
        jTxtDefi.setEnabled(valor);
        jTxtEmail.setEnabled(valor);
        jTxtEnde.setEnabled(valor);
        jTxtEstado.setEnabled(valor);
        jTxtRg.setEnabled(valor);
        jTxtTelefo.setEnabled(valor);
        jFmtDataCadastro.setEnabled(valor);
        jFtfCep.setEnabled(valor);
        jTxtTelefo1.setEnabled(valor);
         jFmtCpf.setEnabled(valor);
           jFmtDataNascimento.setEnabled(valor);
           jTtxtNome.setEnabled(valor);
          
        
        
        jBtnIncluir.setEnabled(!valor);
        jBtnAlterar.setEnabled(!valor);
        jBtnExcluir.setEnabled(!valor);
        jBtnPesquisar.setEnabled(!valor);
    }
     
    public void beanView(ClientesBean clientes){
                
         jTxtCod.setText(String.valueOf(clientes.getMsc_idclientes()));
        jTtxtNome.setText(clientes.getMsc_nome());
        jFmtCpf.setText(clientes.getMsc_cpf());
         jTxtRg.setText(clientes.getMsc_rg()); 
     if (clientes.getMsc_data_nascimento() != null) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    jFmtDataNascimento.setText(sdf.format(clientes.getMsc_data_nascimento()));
} else {
    jFmtDataNascimento.setText(""); 
}
       jTxtTelefo1.setText(clientes.getMsc_telefone());
    jTxtTelefo.setText(clientes.getMsc_telefone_secundario());
     jTxtEmail.setText(clientes.getMsc_email());
      jTxtEnde.setText(clientes.getMsc_endereco());
       jTxtBairro.setText(clientes.getMsc_bairro());
        jTxtCidade.setText(clientes.getMsc_cidade());
         jTxtEstado.setText(clientes.getMsc_estado());
          jFtfCep.setText(clientes.getMsc_cep());
          if (clientes.getMsc_data_cadastro() != null) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    jFmtDataCadastro.setText(sdf.format(clientes.getMsc_data_cadastro()));
} else {
    jFmtDataCadastro.setText(""); 
}      
            jTxtDefi.setText(clientes.getMsc_deficiencia());
    }         
=======
        setLocationRelativeTo(null); 
        
         Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
         
      try {
 
    jFmtDataNascimento.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.MaskFormatter("##/##/####")
        )
    );

  
    jFmtDataCadastro.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.MaskFormatter("##/##/####")
        )
    );
} catch (java.text.ParseException e) {
    e.printStackTrace();
}
       
    }
    public void beanView(MscClientes clientesBean) {
        jTxtCod.setText(Util.intToStr(clientesBean.getIdmscClientes()));
         jTtxtNome.setText(clientesBean.getMscNome());
         jTxtRg.setText(clientesBean.getMscRg());
         jFmtCpf.setText(clientesBean.getMscCpf());
         jFmtDataNascimento.setText(Util.dateToStr(clientesBean.getMscDataNascimento()));
         jTxtTelefo1.setText(clientesBean.getMscTelefone());
          jTxtTelefo.setText(clientesBean.getMscTelefoneSecundario());
         jTxtEmail.setText(clientesBean.getMscEmail());
          jTxtEnde.setText(clientesBean.getMscEndereco());
         jTxtBairro.setText(clientesBean.getMscBairro());
         jTxtCidade.setText(clientesBean.getMscCidade());
          jTxtEstado.setText(clientesBean.getMscEstado());
         jFtfCep.setText(clientesBean.getMscCep());  
          jTxtDefi.setText(clientesBean.getMscDeficiencia());  
         jFmtDataCadastro.setText(Util.dateToStr(clientesBean.getMscDataCadastro()));
   
    }
    public MscClientes viewBean(){
          MscClientes clientesBean = new MscClientes();
    int codigo = Util.strToInt(jTxtCod.getText());
    clientesBean.setIdmscClientes(codigo);

    clientesBean.setMscNome(jTtxtNome.getText());
    clientesBean.setMscRg(jTxtRg.getText());
    clientesBean.setMscCpf(jFmtCpf.getText());
    
   
    String dataNascTexto = jFmtDataNascimento.getText().trim();
    Date dataNascimento;
    
    if (dataNascTexto.isEmpty() || dataNascTexto.equals("  /  /    ")) {
        dataNascimento = new Date(); 
    } else {
        dataNascimento = Util.strToDate(dataNascTexto);
        if (dataNascimento == null) {
            dataNascimento = new Date();
        }
    }
    clientesBean.setMscDataNascimento(dataNascimento);
       
    clientesBean.setMscTelefone(jTxtTelefo1.getText());
    clientesBean.setMscTelefoneSecundario(jTxtTelefo.getText());
    clientesBean.setMscEmail(jTxtEmail.getText());
    clientesBean.setMscEndereco(jTxtEnde.getText());
    clientesBean.setMscBairro(jTxtBairro.getText());
    clientesBean.setMscCidade(jTxtCidade.getText());
    clientesBean.setMscEstado(jTxtEstado.getText());
    clientesBean.setMscCep(jFtfCep.getText());
    clientesBean.setMscDeficiencia(jTxtDefi.getText());
    
    String dataCadTexto = jFmtDataCadastro.getText().trim();
    Date dataCadastro;
    
    if (dataCadTexto.isEmpty() || dataCadTexto.equals("  /  /    ")) {
        dataCadastro = new Date(); 
    } else {
        dataCadastro = Util.strToDate(dataCadTexto);
        if (dataCadastro == null) {
            dataCadastro = new Date();
        }
    }
    clientesBean.setMscDataCadastro(dataCadastro);
          
   
        return clientesBean;
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

        jLabel9 = new javax.swing.JLabel();
        jTxtBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTxtDefi = new javax.swing.JTextField();
        jTxtCod = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFmtCpf = new javax.swing.JFormattedTextField();
        jTtxtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFmtDataNascimento = new javax.swing.JFormattedTextField();
        jFmtDataCadastro = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFtfCep = new javax.swing.JFormattedTextField();
        jTxtRg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtTelefo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTxtCidade = new javax.swing.JTextField();
        jTxtEnde = new javax.swing.JTextField();
        jTxtEstado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBtnIncluir = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
        jTxtTelefo1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setText("Estado");

        jTxtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtBairroActionPerformed(evt);
            }
        });

        jLabel10.setText("Cep");

        jLabel11.setText("Email");

        jTxtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtEmailActionPerformed(evt);
            }
        });

        jLabel12.setText("Cidade");

        jLabel13.setText("Deficiencia");

        jLabel1.setText("Código");

        jTxtDefi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtDefiActionPerformed(evt);
            }
        });

        jTxtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodActionPerformed(evt);
            }
        });

        jLabel15.setText("Data de Cadastro");

        jLabel2.setText("Nome");

        jTtxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTtxtNomeActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF");

        jLabel4.setText("RG");

        jTxtRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtRgActionPerformed(evt);
            }
        });

        jLabel5.setText("Data de Nascimento");

        jTxtTelefo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTelefoActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefone_secundario");

        jLabel7.setText("Endereço");

        jTxtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCidadeActionPerformed(evt);
            }
        });

        jTxtEnde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtEndeActionPerformed(evt);
            }
        });

        jTxtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtEstadoActionPerformed(evt);
            }
        });

        jLabel8.setText("Bairro");

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

        jTxtTelefo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTelefo1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Telefone");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnConfirmar)
                            .addComponent(jBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
<<<<<<< HEAD
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnPesquisar)
                            .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
=======
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(138, 138, 138)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTtxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFmtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFmtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTxtTelefo1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jFtfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jFmtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTxtDefi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTxtTelefo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTxtEnde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(72, 72, 72)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(72, 72, 72))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jTxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTtxtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jFmtCpf)
                    .addComponent(jTxtCod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTxtRg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jFmtDataNascimento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtTelefo1))
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtEnde, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jTxtEmail)
                    .addComponent(jTxtTelefo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                    .addComponent(jFmtDataCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
=======
                    .addComponent(jFmtDataCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
                    .addComponent(jFtfCep)
                    .addComponent(jTxtDefi, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConfirmar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtBairroActionPerformed

    private void jTxtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtEmailActionPerformed

    private void jTxtDefiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtDefiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtDefiActionPerformed

    private void jTxtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodActionPerformed

    private void jTtxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTtxtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTtxtNomeActionPerformed

    private void jTxtRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtRgActionPerformed

    private void jTxtTelefoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTelefoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtTelefoActionPerformed

    private void jTxtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCidadeActionPerformed

    private void jTxtEndeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtEndeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtEndeActionPerformed

    private void jTxtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtEstadoActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        habilitar(true);
         limpar();
         incluir = true;
        jTxtCod.grabFocus();
=======
        Util.habilitar(true, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
          Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
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
         jTtxtNome.grabFocus();
         jTxtCod.setEnabled(false);
         
=======
        Util.habilitar(true, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
         incluir = false;
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        //desabilitar();
        habilitar(false);  
         ClientesBean clientes = new ClientesBean();
    int cod = Integer.parseInt(jTxtCod.getText());
    clientes.setMsc_idclientes(cod);
    clientes.setMsc_nome(jTtxtNome.getText());
    clientes.setMsc_cpf(jFmtCpf.getText());
   clientes.setMsc_rg(jTxtRg.getText());
    clientes.setMsc_telefone(jTxtTelefo1.getText());
    clientes.setMsc_telefone_secundario(jTxtTelefo.getText());
    clientes.setMsc_email(jTxtEmail.getText());
    clientes.setMsc_endereco(jTxtEnde.getText());
    clientes.setMsc_bairro(jTxtBairro.getText());
    clientes.setMsc_cidade(jTxtCidade.getText());
    clientes.setMsc_estado(jTxtEstado.getText());
    clientes.setMsc_cep(jFtfCep.getText());
    clientes.setMsc_deficiencia(jTxtDefi.getText());
    
    String textoData = jFmtDataNascimento.getText();
    String textoData2 = jFmtDataCadastro.getText();
    
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);

    try {
        Date datanasc = sdf.parse(textoData);
        Date dataCadas = sdf.parse(textoData2);
        clientes.setMsc_data_nascimento(datanasc);
        clientes.setMsc_data_cadastro(dataCadas);

        // Mostrar data formatada só para debug
        
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy.");
        return; 
    }
  
     ClientesDao clientesDao = new ClientesDao();
    if (incluir == true) {
        clientesDao.insert(clientes);
    } else {
        clientesDao.update(clientes);
    }

    limpar();
=======
             if(!validarData(jFmtDataCadastro.getText())) {
        return; 
    }
             
         ClientesDao clientesDAO = new ClientesDao();
    if(incluir == true){
        clientesDAO.insert(viewBean());
    } else{
        clientesDAO.update(viewBean());
    }
     
        Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
       Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);   
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        //desabilitar();
        habilitar(false);
        limpar();
=======
        Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
         Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
          Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
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
        if(resp == JOptionPane.YES_OPTION){
           ClientesBean clientes = new ClientesBean();
            int cod = Integer.parseInt(jTxtCod.getText());
        clientes.setMsc_idclientes(cod);
        clientes.setMsc_nome(jTtxtNome.getText());
        clientes.setMsc_cpf(jFmtCpf.getText());
        clientes.setMsc_rg(jTxtRg.getText());
        clientes.setMsc_data_nascimento(null); 
        clientes.setMsc_telefone(jTxtTelefo1.getText());
        clientes.setMsc_telefone_secundario(jTxtTelefo.getText());
         clientes.setMsc_email(jTxtEmail.getText());
          clientes.setMsc_endereco(jTxtEnde.getText());
           clientes.setMsc_bairro(jTxtBairro.getText());
            clientes.setMsc_cidade(jTxtCidade.getText());
             clientes.setMsc_estado(jTxtEstado.getText());
              clientes.setMsc_cep(jFtfCep.getText());
               clientes.setMsc_data_cadastro(null);
                clientes.setMsc_deficiencia(jTxtDefi.getText());

            
            ClientesDao clientesDao = new ClientesDao();
            clientesDao.delete(clientes);
        }
        JOptionPane.showMessageDialog(null, "Aparelho excluído com sucesso!");
limpar();
pesquisado = false; 
=======
         if (Util.perguntar("Deseja Excluir?") == true){
      
          
      }
         ClientesDao clientesDAO = new ClientesDao();
         clientesDAO.delete( viewBean());
     
      Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jTxtTelefo1, jTxtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jTxtTelefo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTelefo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtTelefo1ActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        
         JDlgClientesPesquisar jDlgClientesPesquisar =  new JDlgClientesPesquisar(null,true);
        jDlgClientesPesquisar.setTelaPai(this);
        jDlgClientesPesquisar.setVisible(true);
        pesquisado = true;
        
//         String id = JOptionPane.showInputDialog(null, "Entre com o código");
//        int codigo = Integer.parseInt(id);
//        ClientesDao clientesDao = new ClientesDao();
//         ClientesBean clientes = (ClientesBean) clientesDao.list(codigo);
//        if (clientes ==  null){
//            JOptionPane.showMessageDialog(null, "Codigo Não encontrado");
//        }
//        else{
//            jTxtCod.setText(id);
//        jTtxtNome.setText(clientes.getNome());
//        jFmtCpf.setText(clientes.getCpf());
//         jTxtRg.setText(clientes.getRg());
//         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    String dataFormatada = sdf.format(clientes.getData_nascimento());
//        jFmtDataNascimento.setText(dataFormatada);
//       jTxtTelefo1.setText(clientes.getTelefone());
//    jTxtTelefo.setText(clientes.getTelefone_secundario());
//     jTxtEmail.setText(clientes.getEmail());
//      jTxtEnde.setText(clientes.getEndereco());
//       jTxtBairro.setText(clientes.getBairro());
//        jTxtCidade.setText(clientes.getCidade());
//         jTxtEstado.setText(clientes.getEstado());
//          jFtfCep.setText(clientes.getCep());
//          String dataCadastroFormatada = sdf.format(clientes.getData_cadastro());
//jFmtDataCadastro.setText(dataCadastroFormatada);
//            jTxtDefi.setText(clientes.getDeficiencia());
//    }             
    }//GEN-LAST:event_jBtnPesquisarActionPerformed
public void limpar(){
        jTxtCod.setText("");
        jTtxtNome.setText("");
        jFmtCpf.setText("");
        jTxtRg.setText("");
        jTxtTelefo1.setText("");
        jTxtTelefo.setText("");
        jTxtEmail.setText("");
        jTxtEnde.setText("");
        jTxtBairro.setText("");
        jTxtCidade.setText("");
        jFtfCep.setText("");  
        jTxtEstado.setText("");
        jTxtDefi.setText("");
        jFmtDataNascimento.setText("");
        jFmtDataCadastro.setText("");
}
=======
        JDlgClientesPesquisar jDlgClientesPesquisar =  new JDlgClientesPesquisar(null,true);
         jDlgClientesPesquisar.setTelaAnterior(this);
        jDlgClientesPesquisar.setVisible(true);
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
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgClientes dialog = new JDlgClientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JFormattedTextField jFmtCpf;
    private javax.swing.JFormattedTextField jFmtDataCadastro;
    private javax.swing.JFormattedTextField jFmtDataNascimento;
    private javax.swing.JFormattedTextField jFtfCep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTtxtNome;
    private javax.swing.JTextField jTxtBairro;
    private javax.swing.JTextField jTxtCidade;
    private javax.swing.JTextField jTxtCod;
    private javax.swing.JTextField jTxtDefi;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEnde;
    private javax.swing.JTextField jTxtEstado;
    private javax.swing.JTextField jTxtRg;
    private javax.swing.JTextField jTxtTelefo;
    private javax.swing.JTextField jTxtTelefo1;
    // End of variables declaration//GEN-END:variables
}
