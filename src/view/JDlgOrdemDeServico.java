package view;

import bean.MscAparelhos;
import bean.MscClientes;
import bean.MscOrdemServicoAparelho;
import bean.MscOrdensServico;
import bean.MscServicos;
import bean.MscUsuarios;
import dao.AparelhosDao;
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
private javax.swing.JButton jBtnRegistrarPagamento;
private void recarregarListaAparelhos() {
    try {
        // Aqui você recarrega a lista de aparelhos se necessário
        // Por exemplo, se tiver algum combobox com aparelhos na sua tela:
        /*
        jCobAparelhos.removeAllItems();
        AparelhosDao aparelhosDao = new AparelhosDao();
        List<MscAparelhos> listaAparelhos = (List<MscAparelhos>) aparelhosDao.listAll();
        for (MscAparelhos aparelho : listaAparelhos) {
            jCobAparelhos.addItem(aparelho);
        }
        */
        
        System.out.println("Lista de aparelhos atualizada com sucesso!");
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "❌ Erro ao recarregar lista de aparelhos: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
}
private void recarregarListaServicos() {
    try {
        // Recarrega a lista de serviços se necessário
        // Por exemplo, se tiver algum combobox com serviços na sua tela:
        /*
        jCobServicos.removeAllItems();
        ServicosDao servicosDao = new ServicosDao();
        List<MscServicos> listaServicos = (List<MscServicos>) servicosDao.listAll();
        for (MscServicos servico : listaServicos) {
            jCobServicos.addItem(servico);
        }
        */
        
        System.out.println("Lista de serviços atualizada com sucesso!");
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "❌ Erro ao recarregar lista de serviços: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
}
    /**
     * Creates new form JDlgOrdemDeServico
     */
    public JDlgOrdemDeServico(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        setTitle("Movimento Ordem de serviço");
        setLocationRelativeTo(null);
        
        // Adicione esta variável na declaração de variáveis:


// No método initComponents(), depois de criar jBtnPdf, adicione:
jBtnRegistrarPagamento = new javax.swing.JButton();
jBtnRegistrarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinheiro.png"))); // NOI18N
jBtnRegistrarPagamento.setText("Registrar Pagamento");
jBtnRegistrarPagamento.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jBtnRegistrarPagamento2ActionPerformed(evt);
    }
});

// Se não tiver o ícone, você pode usar texto simples:
// jBtnRegistrarPagamento.setText("💰 Registrar Pagamento");
        
        // ===== ITENS PADRÃO =====
jCobCliente.insertItemAt(null, 0); // para objetos
jCobCliente.setSelectedIndex(0);

jCombUsuario.insertItemAt(null, 0);
jCombUsuario.setSelectedIndex(0);

jCobTec.insertItemAt("Selecione", 0);
jCobTec.setSelectedIndex(0);

jCobStatus.insertItemAt("Selecione", 0);
jCobStatus.setSelectedIndex(0);

jCbxPagamento.insertItemAt("Selecione", 0);
jCbxPagamento.setSelectedIndex(0);

        
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
                jBtnCancelar,
                jBtnRegistrarPagamento
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

         if (jCobTec.getSelectedIndex() > 0) {
    mscordensservico.setMscTecnicoResponsavel(
        jCobTec.getSelectedItem().toString()
    );
}

if (jCobStatus.getSelectedIndex() > 0) {
    mscordensservico.setMscStatus(
        jCobStatus.getSelectedItem().toString()
    );
}
 if (jCbxPagamento.getSelectedIndex() > 0) {
        String formaPagamento = jCbxPagamento.getSelectedItem().toString();
        // Se for "À retirar", salva como null ou string vazia
        if (formaPagamento.equals("Pagamento não Realizado")) {
            mscordensservico.setMscFormaPagamento(null); // ou "" dependendo do seu banco
        } else {
            mscordensservico.setMscFormaPagamento(formaPagamento);
        }
    }

//if (jCbxPagamento.getSelectedIndex() > 0) {
//    mscordensservico.setMscFormaPagamento(
//        jCbxPagamento.getSelectedItem().toString()
//    );
//}
         
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
        // Ajuste para forma de pagamento
    if (mscOrdensServico.getMscFormaPagamento() == null || 
        mscOrdensServico.getMscFormaPagamento().isEmpty()) {
        jCbxPagamento.setSelectedItem("Pagamento não Realizado");
    } else {
        jCbxPagamento.setSelectedItem(mscOrdensServico.getMscFormaPagamento());
    }
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
        jBtnPdf = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFmtNumero = new javax.swing.JFormattedTextField();
        jFmtNumero2 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jTxtValor = new javax.swing.JTextField();
        jCbxPagamento = new javax.swing.JComboBox<>();
        jBtnRegistrarPagamento2 = new javax.swing.JButton();
        jBtnServicos = new javax.swing.JButton();
        jBtnAparelho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Numero da nota");

        jLabel2.setText("Cliente");

        jLabel3.setText("Tecnico Responsavel");

        jLabel4.setText("Usuario");

        jLabel6.setText("Data de Inicio");

        jLabel7.setText("Status");

        jCobStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concluido em espera de retirar", "Concluido Retirado Pelo Cliente", "Em andamento", "Aberto(desmontado)", "Esperando confirmação", "Confirmado Pelo Cliente", "Nulo", "Sem Solução" }));

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

        jCobTec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mathias", "Mateus", "Nelson", "Sirlene" }));
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

        jCbxPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagamento não Realizado", "Débito", "Crédito", "Pix", "Dinheiro" }));
        jCbxPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbxPagamentoActionPerformed(evt);
            }
        });

        jBtnRegistrarPagamento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinheiro.png"))); // NOI18N
        jBtnRegistrarPagamento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarPagamento2ActionPerformed(evt);
            }
        });

        jBtnServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/servicoss.png"))); // NOI18N
        jBtnServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnServicosActionPerformed(evt);
            }
        });

        jBtnAparelho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/celulares.png"))); // NOI18N
        jBtnAparelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAparelhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 121, Short.MAX_VALUE))
                            .addComponent(jCbxPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jBtnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jBtnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jBtnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnRegistrarPagamento2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37)
                .addComponent(jBtnServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 1052, Short.MAX_VALUE)
                    .addComponent(jBtnAparelho, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(jBtnRegistrarPagamento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jBtnPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnPesquisar)
                        .addComponent(jBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(217, 217, 217)
                    .addComponent(jBtnAparelho, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(400, Short.MAX_VALUE)))
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
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel,jBtnRegistrarPagamento);
        Util.habilitar(false,jTxtValor ,jTxtCod, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        //Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario, 
        //jCombServ, jCobTec,jCobStatus,jTxtValor);
        incluir = false;
        jCobCliente.grabFocus();

    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // ===== VALIDAÇÕES =====
    // Para combobox de objetos (jCobCliente, jCombUsuario)
    if (jCobCliente.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this,
            "Selecione um cliente",
            "Campo obrigatório",
            JOptionPane.WARNING_MESSAGE
        );
        jCobCliente.requestFocus();
        return;
    }

    if (jCombUsuario.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this,
            "Selecione um usuário",
            "Campo obrigatório",
            JOptionPane.WARNING_MESSAGE
        );
        jCombUsuario.requestFocus();
        return;
    }

    // Para combobox de strings (jCobTec, jCobStatus, jCbxPagamento)
    if (jCobTec.getSelectedItem() == null || 
        jCobTec.getSelectedItem().equals("Selecione")) {
        JOptionPane.showMessageDialog(this,
            "Selecione o técnico responsável",
            "Campo obrigatório",
            JOptionPane.WARNING_MESSAGE
        );
        jCobTec.requestFocus();
        return;
    }

    if (jCobStatus.getSelectedItem() == null || 
        jCobStatus.getSelectedItem().equals("Selecione")) {
        JOptionPane.showMessageDialog(this,
            "Selecione o status da ordem",
            "Campo obrigatório",
            JOptionPane.WARNING_MESSAGE
        );
        jCobStatus.requestFocus();
        return;
    }

   if (jCbxPagamento.getSelectedItem() == null || 
    jCbxPagamento.getSelectedItem().equals("Selecione")) {
    // Define automaticamente como "Pagamento não Realizado"
    jCbxPagamento.setSelectedItem("Pagamento não Realizado");

    }
    
    // Verificar se tem itens na tabela
    if (controllerOrdemDeServicoAparelho.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this,
            "Adicione pelo menos um aparelho/serviço!",
            "Tabela vazia",
            JOptionPane.WARNING_MESSAGE
        );
        jBtnInclusao.requestFocus();
        return;
    }
        
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
                jBtnConfirmar, jBtnCancelar,jBtnRegistrarPagamento);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2);
        controllerOrdemDeServicoAparelho.setList(new ArrayList());
jCobCliente.setSelectedIndex(0);
jCombUsuario.setSelectedIndex(0);
jCobTec.setSelectedIndex(0);
jCobStatus.setSelectedIndex(0);
jCbxPagamento.setSelectedIndex(0);

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
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel,jBtnRegistrarPagamento);
        Util.habilitar(true, jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero2,jFmtNumero);
        jCobCliente.setSelectedIndex(0);
jCombUsuario.setSelectedIndex(0);
jCobTec.setSelectedIndex(0);
jCobStatus.setSelectedIndex(0);
jCbxPagamento.setSelectedIndex(0);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
        Util.habilitar(true , jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2,
                jBtnConfirmar, jBtnCancelar, jBtnInclusao, jBtnSave, jBtnCancel);
        Util.habilitar(false ,jTxtValor,jBtnIncluir, jBtnAlterar, jBtnExcluir, jBtnPesquisar,jTxtCod,jBtnRegistrarPagamento);
        Util.limpar(jTxtCod, jCobCliente, jFmtData, jCombUsuario,
                jCobTec, jCobStatus, jCbxPagamento,jTxtValor,jFmtNumero,jFmtNumero2);
        controllerOrdemDeServicoAparelho.setList(new ArrayList());
jCobCliente.setSelectedIndex(0);
jCombUsuario.setSelectedIndex(0);
jCobTec.setSelectedIndex(0);
jCobStatus.setSelectedIndex(0);
jCbxPagamento.setSelectedIndex(0);
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
jCobCliente.setSelectedIndex(0);
jCombUsuario.setSelectedIndex(0);
jCobTec.setSelectedIndex(0);
jCobStatus.setSelectedIndex(0);
jCbxPagamento.setSelectedIndex(0);
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

    private void jBtnRegistrarPagamento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarPagamento2ActionPerformed
        // TODO add your handling code here:
         if (jTxtCod.getText().isEmpty() || jTxtCod.getText().equals("0")) {
        JOptionPane.showMessageDialog(this,
            "Selecione uma ordem de serviço para registrar o pagamento!",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Verificar se já está pago
    String formaPagamentoAtual = (String) jCbxPagamento.getSelectedItem();
    if (formaPagamentoAtual != null && 
        !formaPagamentoAtual.equals("Selecione") && 
        !formaPagamentoAtual.equals("Pagamento não Realizado")) {
        
        int resposta = JOptionPane.showConfirmDialog(this,
            "Esta ordem já tem forma de pagamento registrada: " + formaPagamentoAtual + 
            "\nDeseja alterar a forma de pagamento?",
            "Pagamento já registrado",
            JOptionPane.YES_NO_OPTION);
        
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }
    }
    
    // Mostrar opções de pagamento
    String[] opcoesPagamento = {"Débito", "Crédito", "Pix", "Dinheiro", "Cancelar"};
    int escolha = JOptionPane.showOptionDialog(this,
        "REGISTRAR PAGAMENTO PARA RETIRADA\n\n" +
        "Cliente: " + (jCobCliente.getSelectedItem() != null ? 
                      ((MscClientes)jCobCliente.getSelectedItem()).getMscNome() : "N/A") + "\n" +
        "Valor Total: R$ " + jTxtValor.getText() + "\n" +
        "Status atual: " + jCobStatus.getSelectedItem(),
        "Registrar Pagamento",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        opcoesPagamento,
        opcoesPagamento[4]);
    
    if (escolha >= 0 && escolha <= 3) {
        String formaPagamentoSelecionada = opcoesPagamento[escolha];
        
        // Atualizar no combobox
        jCbxPagamento.setSelectedItem(formaPagamentoSelecionada);
        
        // Perguntar se quer atualizar o status também
        int atualizarStatus = JOptionPane.showConfirmDialog(this,
            "Deseja atualizar o status para 'Concluido Retirado Pelo Cliente'?",
            "Atualizar Status",
            JOptionPane.YES_NO_OPTION);
        
        if (atualizarStatus == JOptionPane.YES_OPTION) {
            jCobStatus.setSelectedItem("Concluido Retirado Pelo Cliente");
        }
        
        // Perguntar se quer salvar agora
        int salvarAgora = JOptionPane.showConfirmDialog(this,
            "Deseja salvar as alterações agora?",
            "Salvar Alterações",
            JOptionPane.YES_NO_OPTION);
        
        if (salvarAgora == JOptionPane.YES_OPTION) {
            try {
                Ordem_servicoDao ordemDao = new Ordem_servicoDao();
                MscOrdensServico ordem = viewBean();
                ordemDao.update(ordem);
                
                JOptionPane.showMessageDialog(this,
                    "✅ Pagamento registrado com sucesso!\n" +
                    "• Forma: " + formaPagamentoSelecionada + "\n" +
                    "• Status: " + jCobStatus.getSelectedItem(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "❌ Erro ao registrar pagamento: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Alterações preparadas. Clique em 'Confirmar' para salvar.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    }//GEN-LAST:event_jBtnRegistrarPagamento2ActionPerformed

    private void jBtnServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnServicosActionPerformed
        // TODO add your handling code here:
         try {
        // Abre a tela de cadastro de serviços
        JDlgServicos dialog = new JDlgServicos((java.awt.Frame)this.getParent(), true);
        
        // Guarda o estado antes de abrir (número de serviços)
        ServicosDao servicosDao = new ServicosDao();
        List<MscServicos> servicosAntes = (List<MscServicos>) servicosDao.listAll();
        int quantidadeAntes = servicosAntes != null ? servicosAntes.size() : 0;
        
        // Abre o diálogo
        dialog.setVisible(true);
        
        // Verifica se houve cadastro (compara antes e depois)
        List<MscServicos> servicosDepois = (List<MscServicos>) servicosDao.listAll();
        int quantidadeDepois = servicosDepois != null ? servicosDepois.size() : 0;
        
        if (quantidadeDepois > quantidadeAntes) {
            // Houve cadastro
            JOptionPane.showMessageDialog(this,
                "✅ Serviço cadastrado com sucesso!\n" +
                "Ele já está disponível para uso nas ordens de serviço.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Atualiza a lista se necessário
            recarregarListaServicos();
        } else {
            // Não houve cadastro (usuário apenas fechou)
            System.out.println("Usuário fechou a tela sem cadastrar novo serviço");
        }
            
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "❌ Erro ao abrir cadastro de serviços: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jBtnServicosActionPerformed

    private void jBtnAparelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAparelhoActionPerformed
        // TODO add your handling code here:
         try {
        // Abre a tela de cadastro de aparelhos
        JDlgAparelhos dialog = new JDlgAparelhos((java.awt.Frame)this.getParent(), true);
        
        // Guarda o estado antes de abrir (número de aparelhos)
        AparelhosDao aparelhosDao = new AparelhosDao();
        List<MscAparelhos> aparelhosAntes = (List<MscAparelhos>) aparelhosDao.listAll();
        int quantidadeAntes = aparelhosAntes != null ? aparelhosAntes.size() : 0;
        
        // Abre o diálogo
        dialog.setVisible(true);
        
        // Verifica se houve cadastro (compara antes e depois)
        List<MscAparelhos> aparelhosDepois = (List<MscAparelhos>) aparelhosDao.listAll();
        int quantidadeDepois = aparelhosDepois != null ? aparelhosDepois.size() : 0;
        
        if (quantidadeDepois > quantidadeAntes) {
            // Houve cadastro
            JOptionPane.showMessageDialog(this,
                "✅ Aparelho cadastrado com sucesso!\n" +
                "Ele já está disponível para uso nas ordens de serviço.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Atualiza a lista se necessário
            recarregarListaAparelhos();
        } else {
            // Não houve cadastro (usuário apenas fechou)
            System.out.println("Usuário fechou a tela sem cadastrar novo aparelho");
        }
            
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "❌ Erro ao abrir cadastro de aparelhos: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
                   
    }//GEN-LAST:event_jBtnAparelhoActionPerformed

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
    private javax.swing.JButton jBtnAparelho;
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnIncluir;
    private javax.swing.JButton jBtnInclusao;
    private javax.swing.JButton jBtnPdf;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnRegistrarPagamento2;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnServicos;
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
