package relatorio;

import bean.MscClientes;
import bean.MscOrdensServico;
import bean.MscOrdemServicoAparelho;
import bean.MscUsuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Desktop;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PdfOrdemServico {

    public static void gerar(
            MscOrdensServico ordem,
            List<MscOrdemServicoAparelho> itens
    ) {
// Pega a hora atual de Charlottetown, Ilha do Príncipe Eduardo
        ZonedDateTime agoraCharlottetown = ZonedDateTime.now(ZoneId.of("Canada/Atlantic"));

        // Formata apenas horas e minutos
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = agoraCharlottetown.format(formatoHora);

        try {

            MscClientes cliente = ordem.getMscClientes();
            MscUsuarios usuario = ordem.getMscUsuarios();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder html = new StringBuilder();

            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'/>");
            html.append("<style>");
            html.append("body{font-family:Arial; font-size:12px;}");
            html.append("h2{text-align:center;}");
            html.append("table{width:100%; border-collapse:collapse; margin-top:10px;}");
            html.append("th,td{border:1px solid black; padding:5px;}");
            html.append("th{background:#f0f0f0;}");
            html.append("</style>");
            html.append("</head>");

            html.append("<body>");

            // ================= CABEÇALHO =================
            html.append("<h2>ORDEM DE SERVIÇO</h2>");

            html.append("<p>");
            html.append("<b>Nº OS:</b> ").append(ordem.getIdmscOrdensServico()).append("<br/>");
            html.append("<b>Data:</b> ").append(sdf.format(ordem.getMscDataInicio())).append("<br/>");
            html.append("<p><b>Hora:</b> ")
    .append(horaFormatada)
    .append("</p>");
            html.append("<b>Cliente:</b> ").append(cliente.getMscNome()).append("<br/>");
            html.append("<b>Numero De Telefone:</b> ").append(ordem.getMscNumero()).append("<br/>");
            html.append("<b>Numero De Telefone Secundario:</b> ").append(ordem.getMscNumero2()).append("<br/>");
            html.append("<b>Usuário:</b> ")
                .append(usuario != null ? usuario.getMscNome() : "")
                .append("<br/>");
            html.append("<b>Técnico:</b> ").append(ordem.getMscTecnicoResponsavel()).append("<br/>");
            html.append("<b>Status:</b> ").append(ordem.getMscStatus());
            html.append("</p>");

            // ================= TABELA =================
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>Marca</th>");
            html.append("<th>Modelo</th>");
            html.append("<th>Serviço</th>");
            html.append("<th>Observações</th>");
            html.append("<th>Qtd</th>");
            html.append("<th>Valor Unit.</th>");
            html.append("<th>Total</th>");
            html.append("</tr>");

            double totalGeral = 0;

            for (MscOrdemServicoAparelho item : itens) {

                String marca = item.getMscAparelhos() != null
                        ? item.getMscAparelhos().getMscMarca()
                        : "";

                String modelo = item.getMscAparelhos() != null
                        ? item.getMscAparelhos().getMscModelo()
                        : "";

                String servico = item.getMscServicos() != null
                        ? item.getMscServicos().getMscNomeServico()
                        : "";

                double totalItem = item.getMscQuantidade() * item.getMscValorUnitario();
                totalGeral += totalItem;

                html.append("<tr>");
                html.append("<td>").append(marca).append("</td>");
                html.append("<td>").append(modelo).append("</td>");
                html.append("<td>").append(servico).append("</td>");
                html.append("<td>").append(item.getMscObservacoes()).append("</td>");
                html.append("<td>").append(item.getMscQuantidade()).append("</td>");
                html.append("<td>R$ ").append(String.format("%.2f", item.getMscValorUnitario())).append("</td>");
                html.append("<td>R$ ").append(String.format("%.2f", totalItem)).append("</td>");
                html.append("</tr>");
            }

            html.append("</table>");

            // ================= TOTAL =================
            html.append("<p style='text-align:right; margin-top:10px;'>");
            html.append("<b>VALOR TOTAL: R$ ")
                .append(String.format("%.2f", totalGeral))
                .append("</b>");
            html.append("</p>");

            html.append("</body></html>");

            // ================= PDF =================
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(
                    document,
                    new FileOutputStream("ordem_servico.pdf")
            );

            document.open();

            XMLWorkerHelper.getInstance().parseXHtml(
                    writer,
                    document,
                    new ByteArrayInputStream(html.toString().getBytes("UTF-8"))
            );

            document.close();

            Desktop.getDesktop().open(new File("ordem_servico.pdf"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
