package view;


import bean.MscClientes;
import bean.MscOrdemServicoAparelho;
import bean.MscOrdensServico;
import bean.MscServicos;
import bean.MscUsuarios;
import dao.ClientesDao;
import dao.OrdemServicoAparelhoDao;
import dao.Ordem_servicoDao;
import dao.ServicosDao;
import dao.UsuariosDao;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import tools.Util;
import view.JDlgClientes;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

/**
 *
 * @author mathi
 */
public class JDlgOrdemDeServico extends javax.swing.JDialog {
    
  ControllerOrdemDeServicoAparelho controllerOrdemDeServicoAparelho;

 boolean incluir;
    
    /**
     * Creates new form JDlgOrdemDeServico
     */
    public JDlgOrdemDeServico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         setTitle("Movimento Ordem de serviço");
        setLocationRelativeTo(null); 
        Util.habilitar(false, 
    jTxtCod, 
    jCobCliente, 
    jFmtData, 
    jCombUsuario, 
    jCombServ, 
    jCobTec, 
    jCobStatus, 
    jTxtValor,
    jBtnInclusao, 
    jBtnSave, 
    jBtnCancel,
    jBtnConfirmar,  
    jBtnCancelar
);


Util.habilitar(true, 
    jBtnIncluir,
    jBtnAlterar,
    jBtnExcluir, 
    jBtnPesquisar
);
        ClientesDao clientesDAO = new ClientesDao();
        List lista = (List) clientesDAO.listAll();
        for (int i = 0; i < lista.size(); i++) {
            jCobCliente.addItem( (MscClientes) lista.get(i));            
        }
     
         UsuariosDao usuarios = new UsuariosDao();
        List listaUsu = (List) usuarios.listAll();
        for (Object object : listaUsu) {
            jCombUsuario.addItem((MscUsuarios) object);
        }
        ServicosDao servicos = new ServicosDao();
        List listaServ = (List) servicos.listAll();
        for (Object object : listaServ) {
            jCombServ.addItem((MscServicos) object);
        }
        
        controllerOrdemDeServicoAparelho = new ControllerOrdemDeServicoAparelho();
        controllerOrdemDeServicoAparelho.setList(new ArrayList());
        jTableOrdem.setModel(controllerOrdemDeServicoAparelho);
    
    
    }
public void atualizarTotal() {
    double total = 0;

    for (int i = 0; i < controllerOrdemDeServicoAparelho.getRowCount(); i++) {
        MscOrdemServicoAparelho item = controllerOrdemDeServicoAparelho.getBean(i);

        double valorItem = item.getMscQuantidade() * item.getMscValorUnitario();
        total += valorItem;
    }

    jTxtValor.setText(String.valueOf(total));
}

    public JTable getjTableOrdem() {
        return jTableOrdem;
    }
    
     public MscOrdensServico viewBean() {
        MscOrdensServico mscordensservico = new MscOrdensServico();
        mscordensservico.setIdmscOrdensServico( Util.strToInt(jTxtCod.getText()));
        mscordensservico.setMscDataInicio(Util.strToDate(jFmtData.getText()));
        mscordensservico.setMscValorTotal(Util.strToDouble(jTxtValor.getText()));
        mscordensservico.setMscClientes((MscClientes) jCobCliente.getSelectedItem());
        mscordensservico.setMscUsuarios((MscUsuarios) jCombUsuario.getSelectedItem());
        mscordensservico.setMscServicos((MscServicos) jCombServ.getSelectedItem());
         mscordensservico.setMscStatus(jCobStatus.getSelectedItem().toString());
        mscordensservico.setMscTecnicoResponsavel(jCobTec.getSelectedItem().toString());

       
        return mscordensservico;
    }
      public void beanView(MscOrdensServico mscOrdensServico) {
        jTxtCod.setText(Util.intToStr(mscOrdensServico.getIdmscOrdensServico()));
        jFmtData.setText(Util.dateToStr(mscOrdensServico.getMscDataInicio()));
        jTxtValor.setText(Util.doubleToStr(mscOrdensServico.getMscValorTotal()));
        jCobCliente.setSelectedItem(mscOrdensServico.getMscClientes());
        jCombUsuario.setSelectedItem(mscOrdensServico.getMscUsuarios());
         jCombServ.setSelectedItem(mscOrdensServico.getMscServicos());
         jCobStatus.setSelectedItem(mscOrdensServico.getMscStatus());
        jCobTec.setSelectedItem(mscOrdensServico.getMscTecnicoResponsavel());
        
        OrdemServicoAparelhoDao ordemServicoAparelhoDao = new OrdemServicoAparelhoDao();
        List lista = (List) ordemServicoAparelhoDao.listAparelhos(mscOrdensServico);
        controllerOrdemDeServicoAparelho.setList(lista);
        atualizarTotal();
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCobStatus = new javax.swing.JComboBox<>();
        jFmtData = new javax.swing.JFormattedTextField();
        jBtnAlterar = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
        jCobTec = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrdem = new javax.swing.JTable();
        jBtnIncluir = new javax.swing.JButton();
        jTxtCod = new javax.swing.JTextField();
        jBtnExcluir = new javax.swing.JButton();
        jBtnInclusao = new javax.swing.JButton();
        jBtnSave = new javax.swing.JButton();
        jBtnCancel = new javax.swing.JButton();
        jCobCliente = new javax.swing.JComboBox<MscClientes>();
        jCombUsuario = new javax.swing.JComboBox<MscUsuarios>();
        jCombServ = new javax.swing.JComboBox<MscServicos>();
        jTxtValor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Numero da nota");

        jLabel2.setText("Cliente");

        jLabel3.setText("Tecnico Responsavel");

        jLabel4.setText("Usuario");

        jLabel5.setText(" Serviço");

        jLabel6.setText("Data de Inicio");

        jLabel7.setText("Status");

        jCobStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concluido", "Em andamento", "Aberto", "Esperando confirmação" }));

        jFmtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFmtDataActionPerformed(evt);
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

        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisar.png"))); // NOI18N
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });

        jCobTec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mathias", "Mateus" }));
        jCobTec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCobTecActionPerformed(evt);
            }
        });

        jLabel8.setText("Valor Total");

        jTableOrdem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Title 2", "Title 3", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTableOrdem);

        jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
        jBtnIncluir.setText("Incluir");
        jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIncluirActionPerformed(evt);
            }
        });

        jTxtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodActionPerformed(evt);
            }
        });

        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setToolTipText("");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });

        jBtnInclusao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/incluir.png"))); // NOI18N
        jBtnInclusao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInclusaoActionPerformed(evt);
            }
        });

        jBtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alterar.png"))); // NOI18N
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jBtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Excluir.png"))); // NOI18N
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelActionPerformed(evt);
            }
        });

        jCobCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCobClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCobTec, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCobStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .addComponent(jTxtValor)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jCobCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jCombUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jCombServ, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnCancelar)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCobCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCobTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCobStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(29, 29, 29))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnConfirmar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnPesquisar)
                        .addComponent(jBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFmtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFmtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFmtDataActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
          Util.habilitar(true, jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor,
            jBtnConfirmar, jBtnCancelar,jBtnInclusao, jBtnSave,jBtnCancel);
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        //Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            //jCombServ, jCobTec,jCobStatus,jTxtValor);
        incluir = false;
       
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
       Ordem_servicoDao ordem_servicoDao = new Ordem_servicoDao();       
            OrdemServicoAparelhoDao ordemServicoAparelhoDao = new OrdemServicoAparelhoDao();
        MscOrdensServico mscOrdensServico = viewBean();
        if (incluir == true) {
            ordem_servicoDao.insert(mscOrdensServico);
            for (int ind = 0; ind < jTableOrdem.getRowCount(); ind++) {
                MscOrdemServicoAparelho mscOrdemServicoAparelho = controllerOrdemDeServicoAparelho.getBean(ind);
                mscOrdemServicoAparelho.setMscOrdensServico(mscOrdensServico);
                ordemServicoAparelhoDao.insert(mscOrdemServicoAparelho);
            }
        } else {
            ordem_servicoDao.update(mscOrdensServico);
 //excluo todos os pedidos produtos do pedido
            ordemServicoAparelhoDao.deleteAparelhos(mscOrdensServico);
            //incluo os pedidos produtos
            for (int ind = 0; ind < jTableOrdem.getRowCount(); ind++) {
                MscOrdemServicoAparelho mscOrdemServicoAparelho = controllerOrdemDeServicoAparelho.getBean(ind);
                mscOrdemServicoAparelho.setMscOrdensServico(mscOrdensServico);
                ordemServicoAparelhoDao.insert(mscOrdemServicoAparelho);
            }
        }

       Util.habilitar(false, jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor,
            jBtnConfirmar, jBtnCancelar);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor);
controllerOrdemDeServicoAparelho.setList(new ArrayList());
        
    
    
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
         // TODO add your handling code here:
        JDlgOrdemDeServicoPesquisar jDlgOrdemDeServicoPesquisar = new JDlgOrdemDeServicoPesquisar(null, true);
        jDlgOrdemDeServicoPesquisar.setTelaAnterior(this);
        jDlgOrdemDeServicoPesquisar.setVisible(true);
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

         
        
        
    
    private void jCobTecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCobTecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCobTecActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        Util.habilitar(false, jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor,
            jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave,jBtnCancel);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
         Util.habilitar(true, jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor,
            jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave,jBtnCancel);
        Util.habilitar(false, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
            jCombServ, jCobTec,jCobStatus,jTxtValor);
controllerOrdemDeServicoAparelho.setList(new ArrayList());

        incluir = true;
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jTxtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
  
    if (jTxtCod.getText().isEmpty() || jTxtCod.getText().equals("0")) {
        JOptionPane.showMessageDialog(null, 
            "Antes de excluir, você deve adicionar ou pesquisar uma ordem de serviço.", 
            "Aviso", JOptionPane.WARNING_MESSAGE);
        return; 
    }
    if (Util.perguntar("Deseja excluir esta ordem de serviço?")) {
        Ordem_servicoDao ordem_servicoDao = new Ordem_servicoDao();       
        OrdemServicoAparelhoDao ordemServicoAparelhoDao = new OrdemServicoAparelhoDao();

        try {
            for (int ind = 0; ind < jTableOrdem.getRowCount(); ind++) {
                MscOrdemServicoAparelho mscOrdemServicoAparelho = controllerOrdemDeServicoAparelho.getBean(ind);
                ordemServicoAparelhoDao.delete(mscOrdemServicoAparelho);
            }
      
            ordem_servicoDao.delete(viewBean());

            Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
                        jCombServ, jCobTec, jCobStatus, jTxtValor);
           
            controllerOrdemDeServicoAparelho.setList(new ArrayList());
            JOptionPane.showMessageDialog(null, "Ordem de serviço excluída com sucesso!", 
                                          "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {  
            JOptionPane.showMessageDialog(null, "Erro ao excluir a ordem de serviço: " + ex.getMessage(), 
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
      JDlgOrdemDeServicoAparelho jDlgOrdemDeServicoAparelho = new JDlgOrdemDeServicoAparelho(null, true);
       MscOrdemServicoAparelho mscOrdemServicoAparelho = controllerOrdemDeServicoAparelho.getBean(jTableOrdem.getSelectedRow());
        jDlgOrdemDeServicoAparelho.setTelaAnterior(this, mscOrdemServicoAparelho);
      jDlgOrdemDeServicoAparelho.setVisible(true);
      atualizarTotal();
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnInclusaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInclusaoActionPerformed
        // TODO add your handling code here:
   JDlgOrdemDeServicoAparelho jDlgOrdemDeServicoAparelho = new JDlgOrdemDeServicoAparelho(null, true);
        jDlgOrdemDeServicoAparelho.setTelaAnterior(this, null);
        jDlgOrdemDeServicoAparelho.setVisible(true);
        atualizarTotal();
    }//GEN-LAST:event_jBtnInclusaoActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
        // TODO add your handling code here:
           if (jTableOrdem.getSelectedRow() == -1) {
            Util.mensagem("Oh seu loco, precisa selecionar uma linha.");
        } else {
            if (Util.perguntar("Deseja excluir o produto ?") == true) {
                controllerOrdemDeServicoAparelho.removeBean(jTableOrdem.getSelectedRow());
                atualizarTotal();
            }
        }
          

    }//GEN-LAST:event_jBtnCancelActionPerformed

    private void jCobClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCobClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCobClienteActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgOrdemDeServico dialog = new JDlgOrdemDeServico(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnIncluir;
    private javax.swing.JButton jBtnInclusao;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox<MscClientes> jCobCliente;
    private javax.swing.JComboBox<String> jCobStatus;
    private javax.swing.JComboBox<String> jCobTec;
    private javax.swing.JComboBox<MscServicos> jCombServ;
    private javax.swing.JComboBox<MscUsuarios> jCombUsuario;
    private javax.swing.JFormattedTextField jFmtData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOrdem;
    private javax.swing.JTextField jTxtCod;
    private javax.swing.JTextField jTxtValor;
    // End of variables declaration//GEN-END:variables
}
