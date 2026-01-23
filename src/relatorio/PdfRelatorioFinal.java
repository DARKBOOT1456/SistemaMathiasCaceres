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
import java.util.*;
import javax.swing.JOptionPane;

public class PdfRelatorioFinal {
    
    // Fontes
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font SMALL_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
    
    public static void gerarRelatorioMensal() {
        try {
            // 1. Perguntar o mês e ano
            String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                             "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
            
            Calendar cal = Calendar.getInstance();
            int anoAtual = cal.get(Calendar.YEAR);
            String[] anos = new String[5];
            for (int i = 0; i < 5; i++) {
                anos[i] = String.valueOf(anoAtual - i);
            }
            
            // Painel de seleção
            javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 5, 5));
            panel.add(new javax.swing.JLabel("Mês:"));
            javax.swing.JComboBox<String> cbMes = new javax.swing.JComboBox<>(meses);
            cbMes.setSelectedIndex(cal.get(Calendar.MONTH));
            panel.add(cbMes);
            
            panel.add(new javax.swing.JLabel("Ano:"));
            javax.swing.JComboBox<String> cbAno = new javax.swing.JComboBox<>(anos);
            cbAno.setSelectedIndex(0);
            panel.add(cbAno);
            
            int result = JOptionPane.showConfirmDialog(null, panel,
                "Selecione o mês/ano para o relatório",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            
            if (result != JOptionPane.OK_OPTION) {
                return; // Usuário cancelou
            }
            
            // 2. Calcular período
            int mesSelecionado = cbMes.getSelectedIndex();
            int anoSelecionado = Integer.parseInt((String) cbAno.getSelectedItem());
            
            cal.set(anoSelecionado, mesSelecionado, 1, 0, 0, 0);
            Date dataInicio = cal.getTime();
            
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date dataFim = cal.getTime();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfMesAno = new SimpleDateFormat("MMMM/yyyy");
            String periodo = sdfMesAno.format(dataInicio);
            
            // 3. Buscar dados
            Ordem_servicoDao ordemDao = new Ordem_servicoDao();
            List<MscOrdensServico> ordens = ordemDao.listarPorPeriodo(dataInicio, dataFim);
            
            if (ordens == null || ordens.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Nenhuma ordem de serviço encontrada para " + periodo + "!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // 4. Preparar dados para o relatório
            Map<String, Object> dadosRelatorio = processarDados(ordens);
            
            // 5. Gerar PDF
            String nomeArquivo = "RELATORIO_" + periodo.replace("/", "_") + ".pdf";
            gerarPDF(nomeArquivo, periodo, dadosRelatorio, ordens);
            
            // 6. Abrir o PDF
            File pdfFile = new File(nomeArquivo);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
                JOptionPane.showMessageDialog(null,
                    "✅ Relatório gerado com sucesso!\n" +
                    "📅 Período: " + periodo + "\n" +
                    "📋 Total de ordens: " + ordens.size() + "\n" +
                    "💰 Valor total: R$ " + String.format("%.2f", (Double)dadosRelatorio.get("totalGeral")) + "\n" +
                    "📄 Arquivo: " + nomeArquivo,
                    "Relatório Gerado",
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
    
    private static Map<String, Object> processarDados(List<MscOrdensServico> ordens) {
    Map<String, Object> dados = new HashMap<>();
    
    double totalGeral = 0;
    double totalPix = 0;
    double totalCartaoCredito = 0;
    double totalCartaoDebito = 0;
    double totalDinheiro = 0;
    int totalAparelhos = 0;
    int totalOrdens = ordens.size();
    
    Map<String, Integer> servicosContagem = new HashMap<>();
    Map<String, Integer> marcasContagem = new HashMap<>();
    Map<String, Double> tecnicosValor = new HashMap<>();
    
    // DAO para buscar itens das ordens
    OrdemServicoAparelhoDao itemDao = new OrdemServicoAparelhoDao();
    
    for (MscOrdensServico ordem : ordens) {
        // Buscar itens da ordem usando o método listAparelhos que já existe
        List<MscOrdemServicoAparelho> itens = new ArrayList<>();
        try {
            Object resultado = itemDao.listAparelhos(ordem);
            if (resultado instanceof List) {
                itens = (List<MscOrdemServicoAparelho>) resultado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        double totalOrdem = 0;
        if (itens != null && !itens.isEmpty()) {
            for (MscOrdemServicoAparelho item : itens) {
                double valorItem = item.getMscQuantidade() * item.getMscValorUnitario();
                totalOrdem += valorItem;
                totalAparelhos += item.getMscQuantidade();
                
                // Contar serviços
                if (item.getMscServicos() != null) {
                    String servico = item.getMscServicos().getMscNomeServico();
                    servicosContagem.put(servico, servicosContagem.getOrDefault(servico, 0) + 1);
                }
                
                // Contar marcas
                if (item.getMscAparelhos() != null && item.getMscAparelhos().getMscMarca() != null) {
                    String marca = item.getMscAparelhos().getMscMarca();
                    marcasContagem.put(marca, marcasContagem.getOrDefault(marca, 0) + 1);
                }
            }
        }
        
        totalGeral += totalOrdem;
        
        // ============ CORREÇÃO AQUI ============
        // Use o campo real que armazena a forma de pagamento
        // Verifique qual é o nome correto do método no seu bean MscOrdensServico
        
        String formaPagamento = null;
        
        // Opção 1: Se tiver um método getFormaPagamento()
        try {
            // Tente diferentes nomes de métodos (ajuste conforme sua classe)
            if (ordem.getMscFormaPagamento() != null) {
                formaPagamento = ordem.getMscFormaPagamento();
            } else if (ordem.getMscFormaPagamento() != null) {
                formaPagamento = ordem.getMscFormaPagamento();
            } else if (ordem.getMscFormaPagamento() != null) {
                formaPagamento = ordem.getMscFormaPagamento();
            }
        } catch (Exception e) {
            // Método não existe, continue
        }
        
        // Se não encontrar, verifique se existe um campo diretamente
        if (formaPagamento == null) {
            // Tente acessar via reflexão ou verifique o nome real
            System.out.println("DEBUG: Verifique os métodos disponíveis em MscOrdensServico:");
            System.out.println("Métodos: " + ordem.getClass().getDeclaredMethods());
        }
        
        // Agora use a forma de pagamento real
        if (formaPagamento != null && !formaPagamento.trim().isEmpty()) {
            String formaPagamentoUpper = formaPagamento.toUpperCase().trim();
            
            if (formaPagamentoUpper.contains("PIX")) {
                totalPix += totalOrdem;
            } else if (formaPagamentoUpper.contains("CRÉDITO") || 
                      formaPagamentoUpper.contains("CREDITO") || 
                      formaPagamentoUpper.contains("CARTAO CREDITO")) {
                totalCartaoCredito += totalOrdem;
            } else if (formaPagamentoUpper.contains("DÉBITO") || 
                      formaPagamentoUpper.contains("DEBITO") || 
                      formaPagamentoUpper.contains("CARTAO DEBITO")) {
                totalCartaoDebito += totalOrdem;
            } else if (formaPagamentoUpper.contains("DINHEIRO")) {
                totalDinheiro += totalOrdem;
            } else {
                // Se não reconhecer, adicione a um campo "Outros" ou ao mais provável
                // Por padrão, vamos colocar em Dinheiro (ou ajuste conforme sua necessidade)
                totalDinheiro += totalOrdem;
            }
        } else {
            // Se não houver forma de pagamento cadastrada
            // REMOVA a simulação abaixo e defina como 0 ou distribua conforme necessidade
            // totalDinheiro += totalOrdem; // Ou 0 se quiser separado
        }
        // ============ FIM DA CORREÇÃO ============
        
        // Contar por técnico
        String tecnico = ordem.getMscTecnicoResponsavel();
        if (tecnico != null && !tecnico.isEmpty()) {
            tecnicosValor.put(tecnico, tecnicosValor.getOrDefault(tecnico, 0.0) + totalOrdem);
        }
    }
    
    // Ordenar mapas
    Map<String, Integer> servicosOrdenados = ordenarMapa(servicosContagem);
    Map<String, Integer> marcasOrdenadas = ordenarMapa(marcasContagem);
    Map<String, Double> tecnicosOrdenados = ordenarMapaDouble(tecnicosValor);
    
    // Armazenar dados
    dados.put("totalGeral", totalGeral);
    dados.put("totalPix", totalPix);
    dados.put("totalCartaoCredito", totalCartaoCredito);
    dados.put("totalCartaoDebito", totalCartaoDebito);
    dados.put("totalDinheiro", totalDinheiro);
    dados.put("totalAparelhos", totalAparelhos);
    dados.put("totalOrdens", totalOrdens);
    dados.put("servicos", servicosOrdenados);
    dados.put("marcas", marcasOrdenadas);
    dados.put("tecnicos", tecnicosOrdenados);
    
    return dados;
}
    
    private static void gerarPDF(String nomeArquivo, String periodo, Map<String, Object> dados, List<MscOrdensServico> ordens) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();
            
            // ================= CABEÇALHO =================
            adicionarCabecalho(document, periodo);
            
            // ================= RESUMO GERAL =================
            adicionarResumoGeral(document, dados);
            
            // ================= DETALHES POR ORDEM =================
            adicionarDetalhesOrdens(document, ordens);
            
            // ================= ESTATÍSTICAS =================
            adicionarEstatisticas(document, dados);
            
            // ================= RESUMO FINANCEIRO =================
            adicionarResumoFinanceiro(document, dados);
            
            // ================= RODAPÉ =================
            adicionarRodape(document);
            
            document.close();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF: " + e.getMessage(), e);
        }
    }
    
    private static void adicionarCabecalho(Document document, String periodo) throws Exception {
        // Logo (se existir)
        try {
            String caminhoLogo = "src/img/logos.png";
            File arquivoLogo = new File(caminhoLogo);
            if (arquivoLogo.exists()) {
                com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(caminhoLogo);
                logo.scaleToFit(500, 120);
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
        
        Paragraph endereco = new Paragraph("Rua Mato Grosso do Sul, Nº 42 - Centro, Aral Moreira - MS", SMALL_FONT);
        endereco.setAlignment(Element.ALIGN_CENTER);
        document.add(endereco);
        
        // Título do relatório
        document.add(new Paragraph("\n"));
        Paragraph relatorioTitulo = new Paragraph("RELATÓRIO FINAL DE SERVIÇOS - " + periodo.toUpperCase(), HEADER_FONT);
        relatorioTitulo.setAlignment(Element.ALIGN_CENTER);
        document.add(relatorioTitulo);
        
        // Data de emissão
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Paragraph dataEmissao = new Paragraph("Emitido em: " + sdf.format(new Date()), SMALL_FONT);
        dataEmissao.setAlignment(Element.ALIGN_RIGHT);
        document.add(dataEmissao);
        
        document.add(new Paragraph("\n\n"));
    }
    
    private static void adicionarResumoGeral(Document document, Map<String, Object> dados) throws Exception {
        Paragraph titulo = new Paragraph("RESUMO GERAL", BOLD_FONT);
        titulo.setSpacingBefore(10);
        document.add(titulo);
        
        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(100);
        tabela.setSpacingBefore(5);
        
        adicionarLinhaResumo(tabela, "Total de Ordens de Serviço:", dados.get("totalOrdens").toString());
        adicionarLinhaResumo(tabela, "Total de Aparelhos Atendidos:", dados.get("totalAparelhos").toString());
        adicionarLinhaResumo(tabela, "Valor Total Geral:", String.format("R$ %.2f", (Double)dados.get("totalGeral")));
        
        document.add(tabela);
        document.add(new Paragraph("\n"));
    }
    
    private static void adicionarDetalhesOrdens(Document document, List<MscOrdensServico> ordens) throws Exception {
        Paragraph titulo = new Paragraph("DETALHAMENTO DAS ORDENS DE SERVIÇO", BOLD_FONT);
        titulo.setSpacingBefore(15);
        document.add(titulo);
        
        OrdemServicoAparelhoDao itemDao = new OrdemServicoAparelhoDao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (MscOrdensServico ordem : ordens) {
            MscClientes cliente = ordem.getMscClientes();
            
            // Cabeçalho da ordem
            Paragraph ordemHeader = new Paragraph(
                "OS Nº: " + ordem.getIdmscOrdensServico() + 
                " | Cliente: " + (cliente != null ? cliente.getMscNome() : "N/A") +
                " | Data: " + sdf.format(ordem.getMscDataInicio()) +
                " | Técnico: " + (ordem.getMscTecnicoResponsavel() != null ? ordem.getMscTecnicoResponsavel() : "N/A"),
                NORMAL_FONT
            );
            ordemHeader.setSpacingBefore(10);
            document.add(ordemHeader);
            
            // Buscar itens da ordem usando o método listAparelhos
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
                PdfPTable tabelaItens = new PdfPTable(5);
                tabelaItens.setWidthPercentage(100);
                tabelaItens.setSpacingBefore(5);
                
                // Cabeçalhos
                adicionarCelulaCabecalho(tabelaItens, "Aparelho");
                adicionarCelulaCabecalho(tabelaItens, "Marca");
                adicionarCelulaCabecalho(tabelaItens, "Serviço");
                adicionarCelulaCabecalho(tabelaItens, "Qtd");
                adicionarCelulaCabecalho(tabelaItens, "Valor R$");
                
                double totalOrdem = 0;
                
                for (MscOrdemServicoAparelho item : itens) {
                    String aparelho = item.getMscAparelhos() != null ? 
                        item.getMscAparelhos().getMscModelo() : "N/A";
                    String marca = item.getMscAparelhos() != null ? 
                        item.getMscAparelhos().getMscMarca() : "N/A";
                    String servico = item.getMscServicos() != null ? 
                        item.getMscServicos().getMscNomeServico() : "N/A";
                    int quantidade = item.getMscQuantidade();
                    double valorUnitario = item.getMscValorUnitario();
                    double valorTotal = quantidade * valorUnitario;
                    totalOrdem += valorTotal;
                    
                    tabelaItens.addCell(new PdfPCell(new Phrase(aparelho, NORMAL_FONT)));
                    tabelaItens.addCell(new PdfPCell(new Phrase(marca, NORMAL_FONT)));
                    tabelaItens.addCell(new PdfPCell(new Phrase(servico, NORMAL_FONT)));
                    
                    PdfPCell qtdCell = new PdfPCell(new Phrase(String.valueOf(quantidade), NORMAL_FONT));
                    qtdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabelaItens.addCell(qtdCell);
                    
                    PdfPCell valorCell = new PdfPCell(new Phrase(String.format("R$ %.2f", valorTotal), NORMAL_FONT));
                    valorCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tabelaItens.addCell(valorCell);
                }
                
                // Total da ordem
                PdfPCell vazio = new PdfPCell(new Phrase(""));
                vazio.setColspan(3);
                vazio.setBorder(0);
                tabelaItens.addCell(vazio);
                
                PdfPCell totalLabel = new PdfPCell(new Phrase("Total OS:", BOLD_FONT));
                totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
                totalLabel.setBorder(0);
                tabelaItens.addCell(totalLabel);
                
                PdfPCell totalValor = new PdfPCell(new Phrase(String.format("R$ %.2f", totalOrdem), BOLD_FONT));
                totalValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tabelaItens.addCell(totalValor);
                
                document.add(tabelaItens);
            } else {
                // Se não houver itens
                Paragraph semItens = new Paragraph("   (Nenhum item cadastrado para esta ordem)", SMALL_FONT);
                document.add(semItens);
            }
            
            document.add(new Paragraph(" "));
        }
    }
    
    private static void adicionarEstatisticas(Document document, Map<String, Object> dados) throws Exception {
        Paragraph titulo = new Paragraph("ESTATÍSTICAS", BOLD_FONT);
        titulo.setSpacingBefore(20);
        document.add(titulo);
        
        // Serviços mais realizados
        Paragraph servicosTitulo = new Paragraph("Serviços Mais Realizados:", NORMAL_FONT);
        servicosTitulo.setSpacingBefore(5);
        document.add(servicosTitulo);
        
        Map<String, Integer> servicos = (Map<String, Integer>) dados.get("servicos");
        if (servicos != null && !servicos.isEmpty()) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : servicos.entrySet()) {
                if (count++ >= 5) break; // Mostra apenas os 5 primeiros
                Paragraph servico = new Paragraph("   • " + entry.getKey() + ": " + entry.getValue() + " vezes", SMALL_FONT);
                document.add(servico);
            }
        } else {
            Paragraph semServicos = new Paragraph("   (Nenhum serviço registrado)", SMALL_FONT);
            document.add(semServicos);
        }
        
        // Marcas mais atendidas
        Paragraph marcasTitulo = new Paragraph("\nMarcas Mais Atendidas:", NORMAL_FONT);
        marcasTitulo.setSpacingBefore(5);
        document.add(marcasTitulo);
        
        Map<String, Integer> marcas = (Map<String, Integer>) dados.get("marcas");
        if (marcas != null && !marcas.isEmpty()) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : marcas.entrySet()) {
                if (count++ >= 5) break;
                Paragraph marca = new Paragraph("   • " + entry.getKey() + ": " + entry.getValue() + " aparelhos", SMALL_FONT);
                document.add(marca);
            }
        } else {
            Paragraph semMarcas = new Paragraph("   (Nenhuma marca registrada)", SMALL_FONT);
            document.add(semMarcas);
        }
        
        // Técnicos que mais renderam
        Paragraph tecnicosTitulo = new Paragraph("\nTécnicos (por valor atendido):", NORMAL_FONT);
        tecnicosTitulo.setSpacingBefore(5);
        document.add(tecnicosTitulo);
        
        Map<String, Double> tecnicos = (Map<String, Double>) dados.get("tecnicos");
        if (tecnicos != null && !tecnicos.isEmpty()) {
            int count = 0;
            for (Map.Entry<String, Double> entry : tecnicos.entrySet()) {
                if (count++ >= 5) break;
                Paragraph tecnico = new Paragraph("   • " + entry.getKey() + ": R$ " + String.format("%.2f", entry.getValue()), SMALL_FONT);
                document.add(tecnico);
            }
        } else {
            Paragraph semTecnicos = new Paragraph("   (Nenhum técnico registrado)", SMALL_FONT);
            document.add(semTecnicos);
        }
    }
    
    private static void adicionarResumoFinanceiro(Document document, Map<String, Object> dados) throws Exception {
        Paragraph titulo = new Paragraph("RESUMO FINANCEIRO POR FORMA DE PAGAMENTO", BOLD_FONT);
        titulo.setSpacingBefore(20);
        document.add(titulo);
        
        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(60);
        tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabela.setSpacingBefore(10);
        
        adicionarLinhaResumo(tabela, "PIX:", String.format("R$ %.2f", (Double)dados.get("totalPix")));
        adicionarLinhaResumo(tabela, "Cartão Crédito:", String.format("R$ %.2f", (Double)dados.get("totalCartaoCredito")));
        adicionarLinhaResumo(tabela, "Cartão Débito:", String.format("R$ %.2f", (Double)dados.get("totalCartaoDebito")));
        adicionarLinhaResumo(tabela, "Dinheiro:", String.format("R$ %.2f", (Double)dados.get("totalDinheiro")));
        
        // Linha de total
        PdfPCell cellTotalLabel = new PdfPCell(new Phrase("TOTAL GERAL:", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        cellTotalLabel.setBorder(0);
        cellTotalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cellTotalLabel);
        
        PdfPCell cellTotalValor = new PdfPCell(new Phrase(
            String.format("R$ %.2f", (Double)dados.get("totalGeral")), 
            new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        cellTotalValor.setBorder(0);
        cellTotalValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cellTotalValor);
        
        document.add(tabela);
    }
    
    private static void adicionarRodape(Document document) throws Exception {
        document.add(new Paragraph("\n\n\n"));
        
        Paragraph linha = new Paragraph("_________________________________________________________", NORMAL_FONT);
        linha.setAlignment(Element.ALIGN_CENTER);
        document.add(linha);
        
        Paragraph assinatura = new Paragraph("Assinatura do Responsável", NORMAL_FONT);
        assinatura.setAlignment(Element.ALIGN_CENTER);
        document.add(assinatura);
        
        Paragraph rodape = new Paragraph("\n\nRelatório gerado automaticamente pelo Sistema IRMÃOS M&M", SMALL_FONT);
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
    
    private static void adicionarCelulaCabecalho(PdfPTable tabela, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, BOLD_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(240, 240, 240));
        cell.setPadding(5);
        tabela.addCell(cell);
    }
    
   private static Map<String, Integer> ordenarMapa(Map<String, Integer> mapa) {
    if (mapa == null || mapa.isEmpty()) {
        return new LinkedHashMap<>();
    }
    
    // Converte para lista para ordenar
    List<Map.Entry<String, Integer>> entries = new LinkedList<>(mapa.entrySet());
    
    // Ordena a lista
    java.util.Collections.sort(entries, new java.util.Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            // Ordem decrescente
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    
    // Converte de volta para mapa
    Map<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : entries) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }
    
    return sortedMap;
}

private static Map<String, Double> ordenarMapaDouble(Map<String, Double> mapa) {
    if (mapa == null || mapa.isEmpty()) {
        return new LinkedHashMap<>();
    }
    
    // Converte para lista para ordenar
    List<Map.Entry<String, Double>> entries = new LinkedList<>(mapa.entrySet());
    
    // Ordena a lista
    java.util.Collections.sort(entries, new java.util.Comparator<Map.Entry<String, Double>>() {
        public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
            // Ordem decrescente
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    
    // Converte de volta para mapa
    Map<String, Double> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Double> entry : entries) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }
    
    return sortedMap;
}
}