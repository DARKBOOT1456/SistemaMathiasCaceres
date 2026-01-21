package relatorio;

import bean.MscClientes;
import bean.MscOrdensServico;
import bean.MscOrdemServicoAparelho;
import bean.MscUsuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PdfOrdemServico {

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font SMALL_FONT = new Font(Font.FontFamily.HELVETICA, 8);
    
    public static void gerar(
            MscOrdensServico ordem,
            List<MscOrdemServicoAparelho> itens
    ) {
        try {
            MscClientes cliente = ordem.getMscClientes();
            MscUsuarios usuario = ordem.getMscUsuarios();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Pega a hora atual
            ZonedDateTime agoraCharlottetown = ZonedDateTime.now(ZoneId.of("Canada/Atlantic"));
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            String horaFormatada = agoraCharlottetown.format(formatoHora);
            
            // Calcula total geral
            double totalGeral = 0;
            for (MscOrdemServicoAparelho item : itens) {
                totalGeral += item.getMscQuantidade() * item.getMscValorUnitario();
            }
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(
                document,
                new FileOutputStream("ordem_servico.pdf")
            );
            
            document.open();
            
            // ================= CABEÇALHO COM LOGOS =================
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.setWidthPercentage(100);
            
            // Logo esquerda
            PdfPCell logoLeftCell = new PdfPCell();
            logoLeftCell.setBorder(0);
            try {
                com.itextpdf.text.Image logoLeft = com.itextpdf.text.Image.getInstance("src/img/logo_esquerda.png");
                logoLeft.scaleToFit(80, 80);
                logoLeftCell.addElement(logoLeft);
            } catch (Exception e) {
                logoLeftCell.addElement(new Paragraph("LOGO ESQ", SMALL_FONT));
            }
            
            // Título central
            PdfPCell titleCell = new PdfPCell(new Phrase("IRMÃOS M&M", TITLE_FONT));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setBorder(0);
            titleCell.setPaddingBottom(10);
            
            // Logo direita
            PdfPCell logoRightCell = new PdfPCell();
            logoRightCell.setBorder(0);
            logoRightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            try {
                com.itextpdf.text.Image logoRight = com.itextpdf.text.Image.getInstance("src/img/logo_direita.png");
                logoRight.scaleToFit(80, 80);
                logoRightCell.addElement(logoRight);
            } catch (Exception e) {
                logoRightCell.addElement(new Paragraph("LOGO DIR", SMALL_FONT));
            }
            
            headerTable.addCell(logoLeftCell);
            headerTable.addCell(titleCell);
            headerTable.addCell(logoRightCell);
            
            document.add(headerTable);
            
            // ================= SERVIÇOS OFERECIDOS =================
            Paragraph servicos = new Paragraph(
                "Temos - Películas - Capas - Carregador\n" +
                "Fone de ouvido - Baterias - Assistência Técnica\n\n",
                NORMAL_FONT
            );
            servicos.setAlignment(Element.ALIGN_CENTER);
            document.add(servicos);
            
            // ================= ENDEREÇO =================
            Paragraph endereco = new Paragraph(
                "Rua Mato Grosso do Sul. N:42. Centro de Aral Moreira - MS\n\n",
                NORMAL_FONT
            );
            endereco.setAlignment(Element.ALIGN_CENTER);
            document.add(endereco);
            
            // ================= LINHA DIVISÓRIA =================
            document.add(new Paragraph("____________________________________________________________\n\n"));
            
            // ================= DADOS DA ORDEM =================
            PdfPTable dadosTable = new PdfPTable(2);
            dadosTable.setWidthPercentage(100);
            dadosTable.setSpacingBefore(10);
            dadosTable.setSpacingAfter(10);
            
            PdfPCell dataCell = new PdfPCell(new Phrase("Data: " + sdf.format(new Date()), BOLD_FONT));
            dataCell.setBorder(0);
            dadosTable.addCell(dataCell);
            
            PdfPCell clienteCell = new PdfPCell(new Phrase("Cliente: " + cliente.getMscNome(), BOLD_FONT));
            clienteCell.setBorder(0);
            dadosTable.addCell(clienteCell);
            
            PdfPCell telefoneCell = new PdfPCell(new Phrase("Cel.: " + ordem.getMscNumero(), BOLD_FONT));
            telefoneCell.setBorder(0);
            dadosTable.addCell(telefoneCell);
            
            PdfPCell cpfCell = new PdfPCell(new Phrase("CPF: " + (cliente.getMscCpf() != null ? cliente.getMscCpf() : ""), BOLD_FONT));
            cpfCell.setBorder(0);
            dadosTable.addCell(cpfCell);
            
            document.add(dadosTable);
            
            // ================= DESCRIÇÃO DO EQUIPAMENTO =================
            Paragraph equipamentoTitle = new Paragraph("DESCRIÇÃO DO EQUIPAMENTO\n", HEADER_FONT);
            equipamentoTitle.setSpacingBefore(15);
            document.add(equipamentoTitle);
            
            PdfPTable equipamentoTable = new PdfPTable(1);
            equipamentoTable.setWidthPercentage(100);
            
            String descricao = "- Aparelho: " + (itens.size() > 0 && itens.get(0).getMscAparelhos() != null ? 
                itens.get(0).getMscAparelhos().getMscModelo() : "") + "\n" +
                "- Marca: " + (itens.size() > 0 && itens.get(0).getMscAparelhos() != null ? 
                itens.get(0).getMscAparelhos().getMscMarca() : "");
            
            PdfPCell descricaoCell = new PdfPCell(new Phrase(descricao, NORMAL_FONT));
            descricaoCell.setBorder(0);
            equipamentoTable.addCell(descricaoCell);
            document.add(equipamentoTable);
            
            // ================= LAUDO TÉCNICO =================
            Paragraph laudoTitle = new Paragraph("\nLAUDO TÉCNICO\n", HEADER_FONT);
            laudoTitle.setSpacingBefore(15);
            document.add(laudoTitle);
            
            // Criar tabela para organizar horizontalmente (80% serviços, 20% senha)
            PdfPTable laudoTable = new PdfPTable(2);
            laudoTable.setWidthPercentage(100);
            laudoTable.setSpacingBefore(5);
            laudoTable.setWidths(new float[]{8, 2}); // 80% serviços, 20% senha
            
            // Célula dos serviços (esquerda)
            PdfPCell servicosCell = new PdfPCell();
            servicosCell.setBorder(0);
            servicosCell.setVerticalAlignment(Element.ALIGN_TOP);
            
            // Adicionar serviços
            Paragraph servicosLaudo = new Paragraph();
            for (MscOrdemServicoAparelho item : itens) {
                String servico = item.getMscServicos() != null ? 
                    item.getMscServicos().getMscNomeServico() : "";
                
                String observacoes = item.getMscObservacoes() != null && !item.getMscObservacoes().isEmpty() ?
                    " - " + item.getMscObservacoes() : "";
                
                servicosLaudo.add(servico + observacoes + "\n");
            }
            servicosCell.addElement(servicosLaudo);
            laudoTable.addCell(servicosCell);
            
            // Célula da senha (direita) - compacta
            PdfPCell senhaCell = new PdfPCell();
            senhaCell.setBorder(0);
            senhaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            senhaCell.setVerticalAlignment(Element.ALIGN_TOP);
            
            // Container para texto e imagem
            Paragraph senhaContainer = new Paragraph();
            senhaContainer.setAlignment(Element.ALIGN_CENTER);
            
            // Texto SENHA
            Chunk senhaTexto = new Chunk("SENHA\n", BOLD_FONT);
            senhaContainer.add(senhaTexto);
            
            // Imagem da senha
            try {
                String caminhoImagemSenha = "src/img/9dots.png";
                File arquivoImagem = new File(caminhoImagemSenha);
                
                if (arquivoImagem.exists()) {
                    com.itextpdf.text.Image senhaImg = com.itextpdf.text.Image.getInstance(caminhoImagemSenha);
                    senhaImg.scaleToFit(100, 100); // Tamanho compacto
                    senhaContainer.add(new Chunk(senhaImg, 0, 0, true));
                } else {
                    // Fallback
                    Chunk pontos = new Chunk("\n• • • • • •", 
                        new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
                    senhaContainer.add(pontos);
                }
            } catch (Exception e) {
                Chunk pontos = new Chunk("\n• • • • • •", 
                    new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
                senhaContainer.add(pontos);
            }
            
            senhaCell.addElement(senhaContainer);
            laudoTable.addCell(senhaCell);
            
            document.add(laudoTable);
            
            // Linha horizontal
            document.add(new Paragraph("____________________________________________________________\n\n"));
            
            // ================= GARANTIA =================
           
            
            Paragraph garantia = new Paragraph(
                String.format("Garantia de %d dias\n\n", 90), 
                BOLD_FONT
            );
            garantia.setAlignment(Element.ALIGN_CENTER);
            document.add(garantia);
            
            // ================= TERMOS E CONDIÇÕES =================
            Paragraph termos = new Paragraph(
                "1. A GARANTIA dos serviços executados é de 90 (noventa) dias, à partir da data de confirmação de manutenção do equipamento.\n" +
                "2. A cada 30 dias será cobrada uma taxa a partir do momento que o aparelho estiver pronto. Completando 120 dias, será retirado as peças usadas no aparelho e o aparelho será descartado (Art.06, item 03 do código de defesa do consumidor).\n" +
                "3. Os equipamentos somente serão retirados mediante apresentação da Nota De Serviço, na falta somente com dados da identidade.\n" +
                "4. A loja não se responsabilizará por capas, chips, carregadores, cabos e acessórios deixados junto com o telefone, pois o mesmo é entregue para o cliente no ato da criação da nota.\n\n",
                SMALL_FONT
            );
            document.add(termos);
            
            // ================= TABELA DE PRODUTOS/SERVIÇOS =================
            PdfPTable produtosTable = new PdfPTable(3);
            produtosTable.setWidthPercentage(100);
            produtosTable.setWidths(new float[]{1, 4, 2});
            produtosTable.setSpacingBefore(10);
            
            // Cabeçalho da tabela
            PdfPCell cell1 = new PdfPCell(new Phrase("QUANT.", BOLD_FONT));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            produtosTable.addCell(cell1);
            
            PdfPCell cell2 = new PdfPCell(new Phrase("DESCRIÇÃO DOS PRODUTOS", BOLD_FONT));
            produtosTable.addCell(cell2);
            
            PdfPCell cell3 = new PdfPCell(new Phrase("VALOR R$", BOLD_FONT));
            produtosTable.addCell(cell3);
            
            // Itens da tabela - apenas marca e modelo
            for (MscOrdemServicoAparelho item : itens) {
                String marca = item.getMscAparelhos() != null ? 
                    item.getMscAparelhos().getMscMarca() : "";
                String modelo = item.getMscAparelhos() != null ? 
                    item.getMscAparelhos().getMscModelo() : "";
                
                PdfPCell qtdCell = new PdfPCell(new Phrase(String.valueOf(item.getMscQuantidade()), NORMAL_FONT));
                qtdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                produtosTable.addCell(qtdCell);
                
                PdfPCell descCell = new PdfPCell(new Phrase(
                    marca + " " + modelo, 
                    NORMAL_FONT
                ));
                produtosTable.addCell(descCell);
                
                PdfPCell valorCell = new PdfPCell(new Phrase(
                    String.format("R$ %.2f", item.getMscValorUnitario() * item.getMscQuantidade()), 
                    NORMAL_FONT
                ));
                produtosTable.addCell(valorCell);
            }
            
            document.add(produtosTable);
            
            // ================= FORMA DE PAGAMENTO =================
            Paragraph pagamento = new Paragraph(
                "\n☐ Dinheiro   ☐ Crédito   ☐ Débito\n\n",
                NORMAL_FONT
            );
            document.add(pagamento);
            
            Paragraph totalFinal = new Paragraph(
                String.format("TOTAL R$ %.2f\n\n", totalGeral), 
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
            );
            document.add(totalFinal);
            
            // ================= ASSINATURA =================
            Paragraph assinatura = new Paragraph(
                "Ass. Cliente: _________________________________________\n\n",
                NORMAL_FONT
            );
            document.add(assinatura);
            
            // ================= INFORMAÇÕES ADICIONAIS =================
            Paragraph infoAdicional = new Paragraph(
                "Nº OS: " + ordem.getIdmscOrdensServico() + " | " +
                "Data: " + sdf.format(ordem.getMscDataInicio()) + " | " +
                "Hora: " + horaFormatada + " | " +
                "Técnico: " + ordem.getMscTecnicoResponsavel() + " | " +
                "Status: " + ordem.getMscStatus(),
                SMALL_FONT
            );
            infoAdicional.setAlignment(Element.ALIGN_CENTER);
            document.add(infoAdicional);
            
            document.close();
            
            // Abre o PDF automaticamente
            Desktop.getDesktop().open(new File("ordem_servico.pdf"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}