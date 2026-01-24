package relatorio;

import bean.MscOrdensServico;
import bean.MscOrdemServicoAparelho;
import bean.MscClientes;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.Ordem_servicoDao;
import dao.OrdemServicoAparelhoDao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

public class PdfRelatorioDiario {
    
    // Fontes
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font SMALL_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
    private static final Font MEDIUM_FONT = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
    
    public static void gerarRelatorioDiario() {
        try {
            // 1. Perguntar a data
            javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 5, 5));
            panel.add(new javax.swing.JLabel("Data (dd/mm/aaaa):"));
            javax.swing.JTextField txtData = new javax.swing.JTextField();
            
            // Preenche com a data atual
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtData.setText(sdf.format(new Date()));
            panel.add(txtData);
            
            panel.add(new javax.swing.JLabel("")); // espaço vazio
            panel.add(new javax.swing.JLabel("")); // espaço vazio
            
            int result = JOptionPane.showConfirmDialog(null, panel,
                "Selecione a data para o relatório",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            
            if (result != JOptionPane.OK_OPTION) {
                return; // Usuário cancelou
            }
            
            // 2. Validar e converter data
            Date dataSelecionada;
            try {
                dataSelecionada = sdf.parse(txtData.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Data inválida! Use o formato dd/mm/aaaa",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 3. Calcular período (todo o dia selecionado)
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataSelecionada);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dataInicio = cal.getTime();
            
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date dataFim = cal.getTime();
            
            String dataFormatada = sdf.format(dataSelecionada);
            
            // 4. Buscar dados
            Ordem_servicoDao ordemDao = new Ordem_servicoDao();
            List<MscOrdensServico> ordens = ordemDao.listarPorPeriodo(dataInicio, dataFim);
            
            if (ordens == null || ordens.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Nenhuma ordem de serviço encontrada para " + dataFormatada + "!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // 5. Gerar PDF
            String nomeArquivo = "RELATORIO_DIARIO_" + dataFormatada.replace("/", "_") + ".pdf";
            gerarPDF(nomeArquivo, dataFormatada, ordens);
            
            // 6. Abrir o PDF
            File pdfFile = new File(nomeArquivo);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
                
                JOptionPane.showMessageDialog(null,
                    "✅ Relatório diário gerado com sucesso!\n" +
                    "📅 Data: " + dataFormatada + "\n" +
                    "📋 Total de ordens: " + ordens.size() + "\n" +
                    "📄 Arquivo: " + nomeArquivo,
                    "Relatório Diário Gerado",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "❌ Erro ao gerar relatório:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void gerarPDF(String nomeArquivo, String data, List<MscOrdensServico> ordens) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();
            
            // ================= CABEÇALHO =================
            adicionarCabecalho(document, data);
            
            // ================= RESUMO DO DIA =================
            adicionarResumoDia(document, ordens.size());
            
            // ================= LISTA DETALHADA DE APARELHOS =================
            adicionarListaAparelhos(document, ordens);
            
            // ================= RESUMO POR TÉCNICO =================
            adicionarResumoTecnico(document, ordens);
            
            // ================= RODAPÉ =================
            adicionarRodape(document);
            
            document.close();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF: " + e.getMessage(), e);
        }
    }
    
    private static void adicionarCabecalho(Document document, String data) throws Exception {
        // Logo (se existir)
        try {
            String caminhoLogo = "src/img/logos.png";
            File arquivoLogo = new File(caminhoLogo);
            if (arquivoLogo.exists()) {
                com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(caminhoLogo);
                logo.scaleToFit(400, 100);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            }
        } catch (Exception e) {
            // Se não tiver logo, usa texto
        }
        
        // Título
        Paragraph titulo = new Paragraph("IRMÃOS M&M", TITLE_FONT);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        Paragraph subtitulo = new Paragraph("Assistência Técnica em Celulares", NORMAL_FONT);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitulo);
        
        // Data do relatório
        document.add(new Paragraph("\n"));
        Paragraph relatorioTitulo = new Paragraph("RELATÓRIO DIÁRIO DE ENTRADAS", HEADER_FONT);
        relatorioTitulo.setAlignment(Element.ALIGN_CENTER);
        document.add(relatorioTitulo);
        
        Paragraph dataRelatorio = new Paragraph("Data: " + data, BOLD_FONT);
        dataRelatorio.setAlignment(Element.ALIGN_CENTER);
        document.add(dataRelatorio);
        
        // Data de emissão com fuso horário do Brasil
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("Canada/Atlantic"));
        
        Paragraph dataEmissao = new Paragraph(
            "Emitido em: " + agora.format(formatter),
            SMALL_FONT
        );
        dataEmissao.setAlignment(Element.ALIGN_RIGHT);
        document.add(dataEmissao);
        
        document.add(new Paragraph("\n"));
    }
    
    private static void adicionarResumoDia(Document document, int totalOrdens) throws Exception {
        Paragraph titulo = new Paragraph("RESUMO DO DIA", BOLD_FONT);
        titulo.setSpacingBefore(10);
        document.add(titulo);
        
        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(100);
        tabela.setSpacingBefore(5);
        
        adicionarLinhaResumo(tabela, "Total de Entradas no Dia:", String.valueOf(totalOrdens));
        adicionarLinhaResumo(tabela, "Data do Relatório:", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        
        document.add(tabela);
        document.add(new Paragraph("\n"));
    }
    
   private static void adicionarListaAparelhos(Document document, List<MscOrdensServico> ordens) throws Exception {
    Paragraph titulo = new Paragraph("LISTA DE APARELHOS DO DIA", BOLD_FONT);
    titulo.setSpacingBefore(15);
    document.add(titulo);
    
    OrdemServicoAparelhoDao itemDao = new OrdemServicoAparelhoDao();
    SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
    
    // Ordena por hora (mais recente primeiro)
    Collections.sort(ordens, new Comparator<MscOrdensServico>() {
        @Override
        public int compare(MscOrdensServico o1, MscOrdensServico o2) {
            // Ordena por data (mais recente primeiro)
            return o2.getMscDataInicio().compareTo(o1.getMscDataInicio());
        }
    });
    
    for (MscOrdensServico ordem : ordens) {
        MscClientes cliente = ordem.getMscClientes();
        
        // Pega a hora da ordem (se não tiver, usa a hora atual do sistema)
        String horaFormatada;
        if (ordem.getMscDataInicio() != null) {
            horaFormatada = sdfHora.format(ordem.getMscDataInicio());
        } else {
            horaFormatada = "00:00";
        }
        
        // Cabeçalho da ordem (estilo de nota)
        Paragraph ordemHeader = new Paragraph(
            "══════════════════════════════════════════════════════════\n" +
            "NOTA: " + ordem.getIdmscOrdensServico() + 
            "  |  CLIENTE: " + (cliente != null ? cliente.getMscNome() : "N/A") +
            "  |  TEL: " + (ordem.getMscNumero() != null ? ordem.getMscNumero() : "N/A"),
            NORMAL_FONT
        );
        ordemHeader.setSpacingBefore(10);
        document.add(ordemHeader);
        
        // Buscar itens da ordem
        List<MscOrdemServicoAparelho> itens = new ArrayList<>();
        try {
            Object resultado = itemDao.listAparelhos(ordem);
            if (resultado instanceof List) {
                itens = (List<MscOrdemServicoAparelho>) resultado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (itens != null && !itens.isEmpty()) {
            // Tabela compacta para os aparelhos
            PdfPTable tabelaAparelhos = new PdfPTable(5); // Adicionada coluna para QTD
            tabelaAparelhos.setWidthPercentage(100);
            tabelaAparelhos.setSpacingBefore(5);
            tabelaAparelhos.setWidths(new float[]{3, 2, 3, 2, 1});
            
            // Cabeçalhos
            adicionarCelulaCabecalhoCompacta(tabelaAparelhos, "APARELHO");
            adicionarCelulaCabecalhoCompacta(tabelaAparelhos, "MARCA");
            adicionarCelulaCabecalhoCompacta(tabelaAparelhos, "SERVIÇO");
            adicionarCelulaCabecalhoCompacta(tabelaAparelhos, "TÉCNICO");
            adicionarCelulaCabecalhoCompacta(tabelaAparelhos, "QTD");
            
            for (MscOrdemServicoAparelho item : itens) {
                // Aparelho
                String aparelho = item.getMscAparelhos() != null ? 
                    item.getMscAparelhos().getMscModelo() : "N/A";
                tabelaAparelhos.addCell(new PdfPCell(new Phrase(aparelho, MEDIUM_FONT)));
                
                // Marca
                String marca = item.getMscAparelhos() != null ? 
                    item.getMscAparelhos().getMscMarca() : "N/A";
                tabelaAparelhos.addCell(new PdfPCell(new Phrase(marca, MEDIUM_FONT)));
                
                // Serviço
                String servico = item.getMscServicos() != null ? 
                    item.getMscServicos().getMscNomeServico() : "N/A";
                tabelaAparelhos.addCell(new PdfPCell(new Phrase(servico, MEDIUM_FONT)));
                
                // Técnico
                String tecnico = ordem.getMscTecnicoResponsavel() != null ? 
                    ordem.getMscTecnicoResponsavel() : "N/A";
                tabelaAparelhos.addCell(new PdfPCell(new Phrase(tecnico, MEDIUM_FONT)));
                
                // Quantidade
                PdfPCell qtdCell = new PdfPCell(new Phrase(
                    String.valueOf(item.getMscQuantidade()), 
                    MEDIUM_FONT
                ));
                qtdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAparelhos.addCell(qtdCell);
            }
            
            document.add(tabelaAparelhos);
            
            // Observações se houver
            for (MscOrdemServicoAparelho item : itens) {
                if (item.getMscObservacoes() != null && !item.getMscObservacoes().trim().isEmpty()) {
                    Paragraph obs = new Paragraph(
                        "   OBS: " + item.getMscObservacoes(),
                        new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC)
                    );
                    obs.setSpacingBefore(2);
                    document.add(obs);
                }
            }
            
            // Status da ordem
            String pagamento = ordem.getMscFormaPagamento();
            String statusPagamento = (pagamento != null && !pagamento.isEmpty() && 
                                     !pagamento.equals("Pagamento não Realizado")) ? 
                                     pagamento : "Não realizado";
            
            Paragraph status = new Paragraph(
                "   STATUS: " + ordem.getMscStatus() + 
                "  |  PAGAMENTO: " + statusPagamento,
                new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD)
            );
            status.setSpacingBefore(5);
            document.add(status);
            
        } else {
            Paragraph semItens = new Paragraph("   (Nenhum aparelho cadastrado)", SMALL_FONT);
            document.add(semItens);
        }
        
        document.add(new Paragraph(" "));
    }
}    
    private static void adicionarResumoTecnico(Document document, List<MscOrdensServico> ordens) throws Exception {
        Paragraph titulo = new Paragraph("RESUMO POR TÉCNICO", BOLD_FONT);
        titulo.setSpacingBefore(20);
        document.add(titulo);
        
        // Contar ordens por técnico
        Map<String, Integer> contagemTecnicos = new HashMap<>();
        Map<String, Integer> aparelhosPorTecnico = new HashMap<>();
        
        OrdemServicoAparelhoDao itemDao = new OrdemServicoAparelhoDao();
        
        for (MscOrdensServico ordem : ordens) {
            String tecnico = ordem.getMscTecnicoResponsavel();
            if (tecnico != null && !tecnico.isEmpty()) {
                contagemTecnicos.put(tecnico, contagemTecnicos.getOrDefault(tecnico, 0) + 1);
                
                // Contar aparelhos deste técnico
                List<MscOrdemServicoAparelho> itens = new ArrayList<>();
                try {
                    Object resultado = itemDao.listAparelhos(ordem);
                    if (resultado instanceof List) {
                        itens = (List<MscOrdemServicoAparelho>) resultado;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                int totalAparelhos = 0;
                if (itens != null) {
                    for (MscOrdemServicoAparelho item : itens) {
                        totalAparelhos += item.getMscQuantidade();
                    }
                }
                
                aparelhosPorTecnico.put(tecnico, 
                    aparelhosPorTecnico.getOrDefault(tecnico, 0) + totalAparelhos);
            }
        }
        
        if (!contagemTecnicos.isEmpty()) {
            PdfPTable tabelaTecnicos = new PdfPTable(3);
            tabelaTecnicos.setWidthPercentage(100);
            tabelaTecnicos.setSpacingBefore(10);
            tabelaTecnicos.setWidths(new float[]{4, 2, 2});
            
            // Cabeçalhos
            adicionarCelulaCabecalhoCompacta(tabelaTecnicos, "TÉCNICO");
            adicionarCelulaCabecalhoCompacta(tabelaTecnicos, "ORDENS");
            adicionarCelulaCabecalhoCompacta(tabelaTecnicos, "APARELHOS");
            
            for (Map.Entry<String, Integer> entry : contagemTecnicos.entrySet()) {
                String tecnico = entry.getKey();
                int ordensCount = entry.getValue();
                int aparelhosCount = aparelhosPorTecnico.getOrDefault(tecnico, 0);
                
                tabelaTecnicos.addCell(new PdfPCell(new Phrase(tecnico, NORMAL_FONT)));
                
                PdfPCell ordensCell = new PdfPCell(new Phrase(String.valueOf(ordensCount), NORMAL_FONT));
                ordensCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaTecnicos.addCell(ordensCell);
                
                PdfPCell aparelhosCell = new PdfPCell(new Phrase(String.valueOf(aparelhosCount), NORMAL_FONT));
                aparelhosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaTecnicos.addCell(aparelhosCell);
            }
            
            document.add(tabelaTecnicos);
        } else {
            Paragraph semTecnicos = new Paragraph("   (Nenhum técnico registrado)", NORMAL_FONT);
            document.add(semTecnicos);
        }
    }
    
    private static void adicionarRodape(Document document) throws Exception {
        document.add(new Paragraph("\n\n"));
        
        Paragraph linha = new Paragraph("_________________________________________________________", NORMAL_FONT);
        linha.setAlignment(Element.ALIGN_CENTER);
        document.add(linha);
        
        Paragraph assinatura = new Paragraph("Assinatura do Responsável", NORMAL_FONT);
        assinatura.setAlignment(Element.ALIGN_CENTER);
        document.add(assinatura);
        
        Paragraph rodape = new Paragraph("\n\nRelatório diário gerado automaticamente pelo Sistema IRMÃOS M&M", SMALL_FONT);
        rodape.setAlignment(Element.ALIGN_CENTER);
        document.add(rodape);
    }
    
    // Métodos auxiliares
    private static void adicionarLinhaResumo(PdfPTable tabela, String label, String valor) {
        PdfPCell cellLabel = new PdfPCell(new Phrase(label, BOLD_FONT));
        cellLabel.setBorder(0);
        tabela.addCell(cellLabel);
        
        PdfPCell cellValor = new PdfPCell(new Phrase(valor, NORMAL_FONT));
        cellValor.setBorder(0);
        cellValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cellValor);
    }
    
    private static void adicionarCelulaCabecalhoCompacta(PdfPTable tabela, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(240, 240, 240));
        cell.setPadding(3);
        tabela.addCell(cell);
    }
    
    // Para adicionar no menu principal
    public static void adicionarAoToolbar() {
        // Adicione este método onde você tem os botões do sistema
        // Exemplo de uso:
        /*
        JButton btnRelatorioDiario = new JButton("📅 Relatório Diário");
        btnRelatorioDiario.addActionListener(e -> PdfRelatorioDiario.gerarRelatorioDiario());
        */
    }
}