/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.MscClientes;
import dao.ClientesDao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import tools.Util;



/**
 *
 * @author mathi
 */
public class JDlgClientes extends javax.swing.JDialog {

    /**
     * Creates new form JDlgClientes
     */
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
    public JDlgClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Cadastro de Clientes");
        setLocationRelativeTo(null); 
        
        jTxtEstado.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e) {

            
            if (!Character.isLetter(e.getKeyChar())) {
                e.consume();
                return;
            }

            
            if (jTxtEstado.getText().length() >= 2) {
                e.consume();
            }
        }
    });
        
         Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
         
      try {
 
    jFmtDataNascimento.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.MaskFormatter("##/##/####")
        )
    );
    
    MaskFormatter brMask = new MaskFormatter("+55 (##) #####-####");
brMask.setPlaceholderCharacter('_');
jFmtTelefo1.setFormatterFactory(
    new DefaultFormatterFactory(brMask)
);
    
   MaskFormatter pyMask = new MaskFormatter("+595 ### ### ###");
pyMask.setPlaceholderCharacter('_');
jFmtTelefo.setFormatterFactory(
    new DefaultFormatterFactory(pyMask)
);

    
    MaskFormatter cepMask = new MaskFormatter("#####-###");
cepMask.setPlaceholderCharacter('_');
jFtfCep.setFormatterFactory(
    new DefaultFormatterFactory(cepMask)
);
    
    MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
cpfMask.setPlaceholderCharacter('_');
jFmtCpf.setFormatterFactory(
    new DefaultFormatterFactory(cpfMask)
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
         jFmtTelefo1.setText(clientesBean.getMscTelefone());
          jFmtTelefo.setText(clientesBean.getMscTelefoneSecundario());
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

    // 👉 SÓ SETA O ID SE EXISTIR (ALTERAR / EXCLUIR)
    if (!jTxtCod.getText().trim().isEmpty()) {
        clientesBean.setIdmscClientes(
            Util.strToInt(jTxtCod.getText())
        );
    }

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

    clientesBean.setMscTelefone(jFmtTelefo1.getText());
    clientesBean.setMscTelefoneSecundario(jFmtTelefo.getText());
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
        jLabel14 = new javax.swing.JLabel();
        jFmtTelefo1 = new javax.swing.JFormattedTextField();
        jFmtTelefo = new javax.swing.JFormattedTextField();

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

        jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
        jBtnIncluir.setText("Incluir");
        jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIncluirActionPerformed(evt);
            }
        });

        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alterar.png"))); // NOI18N
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });

        jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });

        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisar.png"))); // NOI18N
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
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
                            .addComponent(jBtnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFmtTelefo1)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFtfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFmtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtDefi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFmtTelefo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtEnde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(jTxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(54, Short.MAX_VALUE))
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
                    .addComponent(jFmtDataNascimento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFmtTelefo1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtRg, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtEmail)
                        .addComponent(jFmtTelefo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxtEnde))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFmtDataCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jFtfCep)
                    .addComponent(jTxtDefi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnConfirmar)
                    .addComponent(jBtnPesquisar)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        Util.habilitar(true, jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar,jTxtCod);
          Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
          incluir = true;
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
        if (jTxtCod.getText().isEmpty() || jTxtCod.getText().equals("0")) {
            JOptionPane.showMessageDialog(null,
                    "Antes de Alterar, você deve adicionar ou pesquisar uma ordem de serviço.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Util.habilitar(true, jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(false,jTxtCod, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
         incluir = false;
         jTtxtNome.grabFocus();
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
        
          String email = jTxtEmail.getText().trim();

    if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        JOptionPane.showMessageDialog(null, "Email inválido");
        jTxtEmail.requestFocus();
        return;
    }
             if(!validarData(jFmtDataCadastro.getText())) {
        return; 
    }
             
         ClientesDao clientesDAO = new ClientesDao();
         MscClientes clientes = viewBean();
    if(incluir == true){
        clientesDAO.insert(clientes);
    } else{
        clientesDAO.update(clientes);
    }
     
        Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
       Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);   
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        Util.habilitar(false, jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi,  jBtnConfirmar, jBtnCancelar);
        
         Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
          Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
         if (jTxtCod.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pesquise para excluir", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
         if (Util.perguntar("Deseja Excluir?") == true){
      
           ClientesDao clientesDAO = new ClientesDao();
         clientesDAO.delete( viewBean());
      }
        
     
      Util.limpar(jTxtCod,jTtxtNome,  jFmtCpf,
                jTxtRg, jFmtDataNascimento, jFmtTelefo1, jFmtTelefo, 
                jTxtEmail,    jTxtEnde, jTxtBairro, jTxtCidade, jTxtEstado, jFtfCep,jFmtDataCadastro, 
                jTxtDefi);
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
        JDlgClientesPesquisar jDlgClientesPesquisar =  new JDlgClientesPesquisar(null,true);
         jDlgClientesPesquisar.setTelaAnterior(this);
        jDlgClientesPesquisar.setVisible(true);
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

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
    private javax.swing.JFormattedTextField jFmtTelefo;
    private javax.swing.JFormattedTextField jFmtTelefo1;
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
    // End of variables declaration//GEN-END:variables
}
