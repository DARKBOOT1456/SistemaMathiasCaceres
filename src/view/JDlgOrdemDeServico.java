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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import relatorio.PdfOrdemServico;
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
    private boolean validarDataInicio(String dataStr) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    
   

    try {
        Date data = sdf.parse(dataStr);
        Calendar cal = Calendar.getInstance();
        Date hoje = cal.getTime();

        if (data.after(hoje)) {
            JOptionPane.showMessageDialog(this, "A data de início não pode ser futura!");
            return false;
        }

        return true;

    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Data de início inválida! Digite no formato dd/MM/yyyy");
        return false;
        
    }
    
    
}
private JButton btnRegistrarPagamento;
    /**
     * Creates new form JDlgOrdemDeServico
     */
    public JDlgOrdemDeServico(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        setTitle("Movimento Ordem de serviço");
        setLocationRelativeTo(null);
         MaskFormatter brMask = new MaskFormatter("+55 (##) #####-####");
brMask.setPlaceholderCharacter('_');
jFmtNumero.setFormatterFactory(
    new DefaultFormatterFactory(brMask)
);
    
   MaskFormatter pyMask = new MaskFormatter("+595 ### ### ###");
pyMask.setPlaceholderCharacter('_');
jFmtNumero2.setFormatterFactory(
    new DefaultFormatterFactory(pyMask)
);
        try {
    MaskFormatter mask = new MaskFormatter("##/##/####");
    mask.setPlaceholderCharacter('_');
    jFmtData.setFormatterFactory(new DefaultFormatterFactory(mask));
} catch (Exception e) {
    e.printStackTrace();
}
        
       


        
        Util.habilitar(false,
                jTxtCod,
                jCobCliente,
                jFmtData,
                jFmtNumero,
                jFmtNumero2,
                jCombUsuario,
                jCobTec,
                jCobStatus,
                jCbxPagamento,
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
            jCobCliente.addItem((MscClientes) lista.get(i));
        }

        UsuariosDao usuarios = new UsuariosDao();
        List listaUsu = (List) usuarios.listAll();
        for (Object object : listaUsu) {
            jCombUsuario.addItem((MscUsuarios) object);
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

    // 👉 SÓ SETA O ID SE EXISTIR (ALTERAR / EXCLUIR)
    if (!jTxtCod.getText().trim().isEmpty()) {
        mscordensservico.setIdmscOrdensServico(
            Util.strToInt(jTxtCod.getText())
        );
    }

    mscordensservico.setMscDataInicio(
        Util.strToDate(jFmtData.getText())
    );
    
   mscordensservico.setMscNumero(
    jFmtNumero.getText()
);

mscordensservico.setMscNumero2(
    jFmtNumero2.getText()
);
    

    mscordensservico.setMscValorTotal(
        Util.strToDouble(jTxtValor.getText())
    );

    mscordensservico.setMscClientes(
        (MscClientes) jCobCliente.getSelectedItem()
    );

    mscordensservico.setMscUsuarios(
        (MscUsuarios) jCombUsuario.getSelectedItem()
    );

    mscordensservico.setMscStatus(
        jCobStatus.getSelectedItem().toString()
    );
    
     mscordensservico.setMscFormaPagamento(
        jCbxPagamento.getSelectedItem().toString()
    );

    mscordensservico.setMscTecnicoResponsavel(
        jCobTec.getSelectedItem().toString()
    );

    return mscordensservico;
    }

    public void beanView(MscOrdensServico mscOrdensServico) {
        jTxtCod.setText(Util.intToStr(mscOrdensServico.getIdmscOrdensServico()));
        jFmtData.setText(Util.dateToStr(mscOrdensServico.getMscDataInicio()));
        jFmtNumero.setText(mscOrdensServico.getMscNumero());
        jFmtNumero2.setText(mscOrdensServico.getMscNumero2());
        jTxtValor.setText(Util.doubleToStr(mscOrdensServico.getMscValorTotal()));
        jCbxPagamento.setSelectedItem(mscOrdensServico.getMscFormaPagamento());
        jCobCliente.setSelectedItem(mscOrdensServico.getMscClientes());
        jCombUsuario.setSelectedItem(mscOrdensServico.getMscUsuarios());
        jCobStatus.setSelectedItem(mscOrdensServico.getMscStatus());
        jCobTec.setSelectedItem(mscOrdensServico.getMscTecnicoResponsavel());

        OrdemServicoAparelhoDao ordemServicoAparelhoDao = new OrdemServicoAparelhoDao();
        List lista = (List) ordemServicoAparelhoDao.listAparelhos(mscOrdensServico);
        controllerOrdemDeServicoAparelho.setList(lista);
        atualizarTotal();
    }
private void registrarPagamento() {
    // Calcular o total da ordem
    double totalOrdem = calcularTotalOrdem();
    
    if (totalOrdem <= 0) {
        JOptionPane.showMessageDialog(this,
            "Adicione serviços à ordem antes de registrar pagamento!",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    
}

private double calcularTotalOrdem() {
    double total = 0;
    // Sua lógica para calcular o total dos itens
    // ...
    return total;}
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCobStatus = new javax.swing.JComboBox<String>();
        jFmtData = new javax.swing.JFormattedTextField();
        jBtnAlterar = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
        jCobTec = new javax.swing.JComboBox<String>();
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
        jBtnPdf = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFmtNumero = new javax.swing.JFormattedTextField();
        jFmtNumero2 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jTxtValor = new javax.swing.JTextField();
        jCbxPagamento = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Numero da nota");

        jLabel2.setText("Cliente");

        jLabel3.setText("Tecnico Responsavel");

        jLabel4.setText("Usuario");

        jLabel6.setText("Data de Inicio");

        jLabel7.setText("Status");

        jCobStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Concluido em espera de retirar", "Concluido Retirado Pelo Cliente", "Em andamento", "Aberto(desmontado)", "Esperando confirmação", "Confirmado Pelo Cliente", "Nulo", "Sem Solução" }));

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

        jCobTec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mathias", "Mateus", "Nelson", "Sirlene" }));
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

        jBtnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-imprimir-48.png"))); // NOI18N
        jBtnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPdfActionPerformed(evt);
            }
        });

        jLabel9.setText("Numero de Telefone");

        jLabel10.setText("Numero de Telefone 2");

        jLabel11.setText("Forma de Pagamento");

        jTxtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtValorActionPerformed(evt);
            }
        });

        jCbxPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Débito", "Crédito", "Pix", "Dinheiro" }));
        jCbxPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbxPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCobCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCombUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jFmtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFmtNumero2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCobTec, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCobStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCbxPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnPdf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jBtnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jBtnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFmtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFmtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCombUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCobCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCobTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCobStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCbxPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
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
        if (jTxtCod.getText().isEmpty() || jTxtCod.getText().equals("0")) {
            JOptionPane.showMessageDialog(null,
                    "Antes de Alterar, você deve adicionar ou pesquisar uma ordem de serviço.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO add your handling code here:
        Util.habilitar(true, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus ,jCbxPagamento,jFmtNumero,jFmtNumero2,
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel);
        Util.habilitar(false,jTxtValor ,jTxtCod, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        //Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
        //jCombServ, jCobTec,jCobStatus,jTxtValor);
        incluir = false;
        jCobCliente.grabFocus();

    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        try {
            // TODO add your handling code here:
            if (!validarDataInicio(jFmtData.getText())) {
                return;
            }       } catch (ParseException ex) {
            Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2,
                jBtnConfirmar, jBtnCancelar);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor);
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
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2,
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
        Util.habilitar(true , jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2,
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel);
        Util.habilitar(false, jCbxPagamento,jTxtValor,jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar,jTxtCod);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor);
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
                        jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2);

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
          if (jTableOrdem.getSelectedRow() == -1) {
            Util.mensagem("Antes de Alterar, você deve adicionar um Aparelho.");
        } else {
        JDlgOrdemDeServicoAparelho jDlgOrdemDeServicoAparelho = new JDlgOrdemDeServicoAparelho(null, true);
        MscOrdemServicoAparelho mscOrdemServicoAparelho = controllerOrdemDeServicoAparelho.getBean(jTableOrdem.getSelectedRow());
        jDlgOrdemDeServicoAparelho.setTelaAnterior(this, mscOrdemServicoAparelho);
        jDlgOrdemDeServicoAparelho.setVisible(true);
        atualizarTotal();}
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
            Util.mensagem("Adicione um Aparelho para excluir");
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

    private void jBtnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPdfActionPerformed
        // TODO add your handling code here:
          if (jTxtCod.getText().isEmpty() || jTxtCod.getText().equals("0")) {
            JOptionPane.showMessageDialog(null,
                    "Antes de fazer a impressão, você deve pesquisar ou adicionar uma ordem de serviço!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 1) Pega a ordem que está na tela
    MscOrdensServico ordem = viewBean();

    // 2) Pega os itens da tabela
    List<MscOrdemServicoAparelho> itens = new ArrayList<>();
    for (int i = 0; i < controllerOrdemDeServicoAparelho.getRowCount(); i++) {
        itens.add(controllerOrdemDeServicoAparelho.getBean(i));
    }

    // 3) Gera o PDF
    PdfOrdemServico.gerar(ordem, itens);
    }//GEN-LAST:event_jBtnPdfActionPerformed

    private void jTxtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtValorActionPerformed

    private void jCbxPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbxPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbxPagamentoActionPerformed

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
                JDlgOrdemDeServico dialog = null;
                try {
                    dialog = new JDlgOrdemDeServico(new javax.swing.JFrame(), true);
                } catch (ParseException ex) {
                    Logger.getLogger(JDlgOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton jBtnPdf;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox<String> jCbxPagamento;
    private javax.swing.JComboBox<MscClientes> jCobCliente;
    private javax.swing.JComboBox<String> jCobStatus;
    private javax.swing.JComboBox<String> jCobTec;
    private javax.swing.JComboBox<MscUsuarios> jCombUsuario;
    private javax.swing.JFormattedTextField jFmtData;
    private javax.swing.JFormattedTextField jFmtNumero;
    private javax.swing.JFormattedTextField jFmtNumero2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOrdem;
    private javax.swing.JTextField jTxtCod;
    private javax.swing.JTextField jTxtValor;
    // End of variables declaration//GEN-END:variables
}
