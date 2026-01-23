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
            
            // ================= CABEÇALHO COM LOGO ÚNICO =================
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            
            // Tenta carregar a imagem combinada
            PdfPCell logoCell = new PdfPCell();
            logoCell.setBorder(0);
            logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            logoCell.setPaddingBottom(10);
            
            try {
                // Tenta carregar a imagem combinada "logos.png"
                String caminhoLogo = "src/img/logoss.png";
                File arquivoLogo = new File(caminhoLogo);
                
                if (arquivoLogo.exists()) {
                    com.itextpdf.text.Image logoCombinado = com.itextpdf.text.Image.getInstance(caminhoLogo);
                    
                    // Ajusta o tamanho da imagem para se adequar ao PDF
                    float larguraDesejada = 600;
                    float alturaDesejada = 220;
                    
                    // Mantém proporção da imagem
                    logoCombinado.scaleToFit(larguraDesejada, alturaDesejada);
                    
                    // Centraliza a imagem
                    logoCombinado.setAlignment(Element.ALIGN_CENTER);
                    
                    logoCell.addElement(logoCombinado);
                } else {
                    // Fallback: se a imagem não existir, usa texto
                    Paragraph fallback = new Paragraph("IRMÃOS M&M", TITLE_FONT);
                    fallback.setAlignment(Element.ALIGN_CENTER);
                    logoCell.addElement(fallback);
                    
                    Paragraph aviso = new Paragraph(
                        "(Imagem logos.png não encontrada)", 
                        SMALL_FONT
                    );
                    aviso.setAlignment(Element.ALIGN_CENTER);
                    logoCell.addElement(aviso);
                }
            } catch (Exception e) {
                Paragraph errorText = new Paragraph("IRMÃOS M&M", TITLE_FONT);
                errorText.setAlignment(Element.ALIGN_CENTER);
                logoCell.addElement(errorText);
                e.printStackTrace();
            }
            
            headerTable.addCell(logoCell);
            document.add(headerTable);
            
            // ================= SERVIÇOS OFERECIDOS =================
            Font fontServicos = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            
            Paragraph servicos = new Paragraph(
                "Temos - Películas - Capas - Carregador\n" +
                "Fone de ouvido - Baterias - Assistência Técnica\n\n",
                fontServicos
            );
            servicos.setAlignment(Element.ALIGN_CENTER);
            document.add(servicos);
            
            // ================= ENDEREÇO =================
            Font ENDERECO_FONT = new Font(
                Font.FontFamily.HELVETICA,
                10,
                Font.ITALIC
            );
            
            Paragraph endereco = new Paragraph(
                "Rua Mato Grosso do Sul, Nº 42 - Centro, Aral Moreira - MS\n\n",
                ENDERECO_FONT
            );
            endereco.setAlignment(Element.ALIGN_CENTER);
            document.add(endereco);
            
            // ================= LINHA DIVISÓRIA =================
            Paragraph linhaDivisoria = new Paragraph("____________________________________________________________\n\n");
            linhaDivisoria.setAlignment(Element.ALIGN_CENTER);
            document.add(linhaDivisoria);
            
            // ================= DADOS DA ORDEM =================
            PdfPTable dadosTable = new PdfPTable(2);
            dadosTable.setWidthPercentage(100);
            dadosTable.setHorizontalAlignment(Element.ALIGN_CENTER); // Centraliza a tabela
            dadosTable.setSpacingBefore(10);
            dadosTable.setSpacingAfter(10);
            
            PdfPCell dataCell = new PdfPCell(new Phrase("Data: " + sdf.format(new Date()), BOLD_FONT));
            dataCell.setBorder(0);
            dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dadosTable.addCell(dataCell);
            
            PdfPCell clienteCell = new PdfPCell(new Phrase("Cliente: " + cliente.getMscNome(), BOLD_FONT));
            clienteCell.setBorder(0);
            clienteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dadosTable.addCell(clienteCell);
            
            PdfPCell telefoneCell = new PdfPCell(new Phrase("Cel.: " + ordem.getMscNumero(), BOLD_FONT));
            telefoneCell.setBorder(0);
            telefoneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dadosTable.addCell(telefoneCell);
            
            PdfPCell telefoneCell2 = new PdfPCell(new Phrase("Cel.: " + ordem.getMscNumero2(), BOLD_FONT));
            telefoneCell2.setBorder(0);
            telefoneCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            dadosTable.addCell(telefoneCell2);
            
            PdfPCell cpfCell = new PdfPCell(new Phrase("CPF: " + (cliente.getMscCpf() != null ? cliente.getMscCpf() : ""), BOLD_FONT));
            cpfCell.setBorder(0);
            cpfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dadosTable.addCell(cpfCell);
            
            document.add(dadosTable);
            
       // ================= DESCRIÇÃO DO EQUIPAMENTO =================
PdfPTable tituloEquipamentoTable = new PdfPTable(1);
tituloEquipamentoTable.setWidthPercentage(80);
tituloEquipamentoTable.setHorizontalAlignment(Element.ALIGN_CENTER);
tituloEquipamentoTable.setSpacingBefore(15);

PdfPCell tituloEquipamentoCell = new PdfPCell(new Phrase("DESCRIÇÃO DO EQUIPAMENTO", HEADER_FONT));
tituloEquipamentoCell.setBackgroundColor(new com.itextpdf.text.BaseColor(230, 230, 230));
tituloEquipamentoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
tituloEquipamentoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
tituloEquipamentoCell.setPadding(10);

// Efeito 3D
tituloEquipamentoCell.setBorderWidthTop(2);
tituloEquipamentoCell.setBorderWidthLeft(2);
tituloEquipamentoCell.setBorderColorTop(new com.itextpdf.text.BaseColor(255, 255, 255));
tituloEquipamentoCell.setBorderColorLeft(new com.itextpdf.text.BaseColor(255, 255, 255));
tituloEquipamentoCell.setBorderWidthBottom(2);
tituloEquipamentoCell.setBorderWidthRight(2);
tituloEquipamentoCell.setBorderColorBottom(new com.itextpdf.text.BaseColor(150, 150, 150));
tituloEquipamentoCell.setBorderColorRight(new com.itextpdf.text.BaseColor(150, 150, 150));

tituloEquipamentoTable.addCell(tituloEquipamentoCell);
document.add(tituloEquipamentoTable);

// Restante do código da descrição dos equipamentos
PdfPTable equipamentoTable = new PdfPTable(1);
equipamentoTable.setWidthPercentage(80);
equipamentoTable.setHorizontalAlignment(Element.ALIGN_CENTER);

Font FONTE_MAIOR = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
Font FONTE_NEGRITO = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

Paragraph descricaoParagrafo = new Paragraph();
descricaoParagrafo.setAlignment(Element.ALIGN_CENTER);

for (int i = 0; i < itens.size(); i++) {
    MscOrdemServicoAparelho item = itens.get(i);
    
    if (item.getMscAparelhos() != null) {
        String modelo = item.getMscAparelhos().getMscModelo();
        String marca = item.getMscAparelhos().getMscMarca();
        String CHIP = item.getMscAparelhos().getMscChipRetirado();
        String cor = item.getMscAparelhos().getMscCor();
         String tipo = item.getMscAparelhos().getMscTipodeEquipamento();
        
        // Título em negrito com espaço depois
        descricaoParagrafo.add(new Paragraph("APARELHO " + (i + 1) + ":", FONTE_NEGRITO));
        
        
        
        // Detalhes
        descricaoParagrafo.add(new Paragraph("- Modelo: " + (modelo != null ? modelo : ""), FONTE_MAIOR));
        descricaoParagrafo.add(new Paragraph("- Marca: " + (marca != null ? marca : ""), FONTE_MAIOR));
         descricaoParagrafo.add(new Paragraph("- Chip: " + (CHIP != null ? CHIP : ""), FONTE_MAIOR));
          descricaoParagrafo.add(new Paragraph("- Cor: " + (cor != null ? cor : ""), FONTE_MAIOR));
          descricaoParagrafo.add(new Paragraph("- Tipo do Equipamento: " + (tipo != null ? tipo : ""), FONTE_MAIOR));
        
        
       
        
        if (item.getMscQuantidade() > 1) {
            descricaoParagrafo.add(new Paragraph("- Quantidade: " + item.getMscQuantidade(), FONTE_MAIOR));
        }
        
        // Espaço maior entre aparelhos
        if (i < itens.size() - 1) {
            descricaoParagrafo.add(new Paragraph("\n"));
        }
    }
}

if (itens.isEmpty()) {
    descricaoParagrafo.add(new Paragraph("Nenhum aparelho cadastrado", FONTE_MAIOR));
}

PdfPCell descricaoCell = new PdfPCell();
descricaoCell.addElement(descricaoParagrafo);
descricaoCell.setBorder(0);
descricaoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
equipamentoTable.addCell(descricaoCell);
document.add(equipamentoTable);
            
           // ================= LAUDO TÉCNICO =================
PdfPTable tituloTable = new PdfPTable(1);
tituloTable.setWidthPercentage(80);
tituloTable.setHorizontalAlignment(Element.ALIGN_CENTER);
tituloTable.setSpacingBefore(15);

PdfPCell tituloCell = new PdfPCell(new Phrase("LAUDO TÉCNICO", HEADER_FONT));
tituloCell.setBackgroundColor(new com.itextpdf.text.BaseColor(230, 230, 230));
tituloCell.setHorizontalAlignment(Element.ALIGN_CENTER);
tituloCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
tituloCell.setPadding(10);
tituloCell.setBorderWidthTop(2);
tituloCell.setBorderWidthLeft(2);
tituloCell.setBorderColorTop(new com.itextpdf.text.BaseColor(255, 255, 255)); // Borda superior clara
tituloCell.setBorderColorLeft(new com.itextpdf.text.BaseColor(255, 255, 255)); // Borda esquerda clara
tituloCell.setBorderWidthBottom(2);
tituloCell.setBorderWidthRight(2);
tituloCell.setBorderColorBottom(new com.itextpdf.text.BaseColor(150, 150, 150)); // Borda inferior escura
tituloCell.setBorderColorRight(new com.itextpdf.text.BaseColor(150, 150, 150)); // Borda direita escura

tituloTable.addCell(tituloCell);
document.add(tituloTable);


// Criar tabela para organizar horizontalmente (80% serviços, 20% senha)
PdfPTable laudoTable = new PdfPTable(2);
laudoTable.setWidthPercentage(90);
laudoTable.setHorizontalAlignment(Element.ALIGN_CENTER);
laudoTable.setSpacingBefore(5);
laudoTable.setWidths(new float[]{8, 2}); // 80% serviços, 20% senha

// Célula dos serviços (esquerda)
PdfPCell servicosCell = new PdfPCell();
servicosCell.setBorder(0);
servicosCell.setVerticalAlignment(Element.ALIGN_TOP);
servicosCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Alterei para LEFT para melhor leitura

// Adicionar serviços SEPARADOS POR APARELHO
Paragraph servicosLaudo = new Paragraph();

for (int i = 0; i < itens.size(); i++) {
    MscOrdemServicoAparelho item = itens.get(i);
    
    // Título do aparelho
    String aparelhoInfo = "";
    if (item.getMscAparelhos() != null) {
        String marca = item.getMscAparelhos().getMscMarca();
        String modelo = item.getMscAparelhos().getMscModelo();
        aparelhoInfo = String.format("%s %s", 
            marca != null ? marca : "", 
            modelo != null ? modelo : "");
    }
    
    // Adicionar título do aparelho
    Chunk tituloAparelho = new Chunk(
        String.format("APARELHO %d: %s\n", i + 1, aparelhoInfo), 
        new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)
    );
    servicosLaudo.add(tituloAparelho);
    
    // Adicionar serviços deste aparelho
    String servico = item.getMscServicos() != null ? 
        item.getMscServicos().getMscNomeServico() : "";
    
    String observacoes = item.getMscObservacoes() != null && !item.getMscObservacoes().isEmpty() ?
        " - " + item.getMscObservacoes() : "";
    
    Chunk detalheServico = new Chunk("  • " + servico + observacoes + "\n", NORMAL_FONT);
    servicosLaudo.add(detalheServico);
    
    // Adicionar linha em branco entre aparelhos (exceto no último)
    if (i < itens.size() - 1) {
        servicosLaudo.add(new Chunk("\n", NORMAL_FONT));
    }
}

// Se não houver serviços
if (itens.isEmpty()) {
    servicosLaudo.add(new Chunk("Nenhum serviço cadastrado", NORMAL_FONT));
}

servicosCell.addElement(servicosLaudo);
laudoTable.addCell(servicosCell);

// Célula da senha (direita) - compacta (mantida igual)
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
        senhaImg.scaleToFit(100, 100);
        senhaImg.setAlignment(Element.ALIGN_CENTER);
        senhaContainer.add(new Chunk(senhaImg, 0, 0, true));
    } else {
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
Paragraph linhaHorizontal = new Paragraph("PIN:____________________________________________________________\n\n");
linhaHorizontal.setAlignment(Element.ALIGN_CENTER);
document.add(linhaHorizontal);
            
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
            termos.setAlignment(Element.ALIGN_CENTER);
            document.add(termos);
            
            // ================= TABELA DE PRODUTOS/SERVIÇOS =================
            PdfPTable produtosTable = new PdfPTable(3);
            produtosTable.setWidthPercentage(90); // Reduz para centralizar melhor
            produtosTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            produtosTable.setWidths(new float[]{1, 4, 2});
            produtosTable.setSpacingBefore(10);
            
            // Cabeçalho da tabela - centralizado
            PdfPCell cell1 = new PdfPCell(new Phrase("QUANT.", BOLD_FONT));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            produtosTable.addCell(cell1);
            
            PdfPCell cell2 = new PdfPCell(new Phrase("DESCRIÇÃO DOS PRODUTOS", BOLD_FONT));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            produtosTable.addCell(cell2);
            
            PdfPCell cell3 = new PdfPCell(new Phrase("VALOR R$", BOLD_FONT));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            produtosTable.addCell(cell3);
            
            // Itens da tabela - centralizados
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
                descCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                produtosTable.addCell(descCell);
                
                PdfPCell valorCell = new PdfPCell(new Phrase(
                    String.format("R$ %.2f", item.getMscValorUnitario() * item.getMscQuantidade()), 
                    NORMAL_FONT
                ));
                valorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                produtosTable.addCell(valorCell);
            }
            
            document.add(produtosTable);
            
            // ================= FORMA DE PAGAMENTO =================
Paragraph pagamento = new Paragraph();
pagamento.setAlignment(Element.ALIGN_CENTER);
pagamento.setSpacingBefore(10);

// Usar caracteres ASCII simples que sempre funcionam
String opcoesPagamento = "\n[ ] Dinheiro   [ ] Crédito   [ ] Débito   [ ] Pix\n\n";

pagamento.add(new Chunk(opcoesPagamento, NORMAL_FONT));
document.add(pagamento);
            
            // ================= TOTAL =================
            Paragraph totalFinal = new Paragraph(
                String.format("TOTAL R$ %.2f\n\n", totalGeral), 
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
            );
            totalFinal.setAlignment(Element.ALIGN_CENTER);
            document.add(totalFinal);
            
            // ================= ASSINATURA =================
            Paragraph assinatura = new Paragraph(
                "Ass. Cliente: _________________________________________\n\n",
                NORMAL_FONT
            );
            assinatura.setAlignment(Element.ALIGN_CENTER);
            document.add(assinatura);
            
            // ================= INFORMAÇÕES ADICIONAIS =================
Paragraph infoAdicional = new Paragraph(
    "Nº OS: " + ordem.getIdmscOrdensServico() + " | " +
    "Data: " + sdf.format(ordem.getMscDataInicio()) + " | " +
    "Hora: " + horaFormatada + " | " +
    "Usuário: " + (usuario != null ? usuario.getMscNome() : "N/A") + " | " +
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