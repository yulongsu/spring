package cn.su.study.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @author hiro.syl
 * @date 2018/04/20
 */
public class Mypdf {
    public static void main(String[] args) {
        Document document = new Document(PageSize.A4.rotate());
        String fileName = "My first Test";
        File file = new File("C:\\Temp\\my.pdf");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 12, Font.NORMAL);
            Paragraph title = new Paragraph(fileName, font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph blankRow = new Paragraph();
            blankRow.add(Chunk.NEWLINE);
            document.add(blankRow);

            boolean showWentFlag = false;
            PdfPTable table = new PdfPTable(showWentFlag ? 10 : 9);

            // 定义表格的宽度
            if(showWentFlag){
                int[] cellsWidth = {1, 1, 3, 1, 1, 1, 1, 1, 1, 1};
                table.setWidths(cellsWidth);
            }else{
                int[] cellsWidth = {1, 1, 3, 1, 1, 1, 1, 1, 1};
                table.setWidths(cellsWidth);
            }
            table.setWidthPercentage(100);// 表格的宽度百分比

            table.addCell(new PdfPCell(new Paragraph("影厅", font)));
            table.addCell(new PdfPCell(new Paragraph("放映日期", font)));
            table.addCell(new PdfPCell(new Paragraph("影片", font)));
            table.addCell(new PdfPCell(new Paragraph("语言", font)));
            table.addCell(new PdfPCell(new Paragraph("版本", font)));
            table.addCell(new PdfPCell(new Paragraph("放映时间", font)));
            table.addCell(new PdfPCell(new Paragraph("结束时间", font)));
            if (showWentFlag) {
                table.addCell(new PdfPCell(new Paragraph("分账比例(片方)", font)));
            }
            table.addCell(new PdfPCell(new Paragraph("票价(元)", font)));
            table.addCell(new PdfPCell(new Paragraph("片长(分)", font)));
            table.setHeaderRows(1);

            table.addCell(new PdfPCell(new Paragraph("vo.getHallName()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getShowDate()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getFilmName()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getLanguageName()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getDimensionName()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getShowBeginTime()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getShowEndTime()", font)));
            if (showWentFlag) {
                table.addCell(new PdfPCell(
                    new Paragraph(String.format("%.2f", Double.parseDouble("0.89") * 100) + "%", font)));
            }
            table.addCell(new PdfPCell(new Paragraph("vo.getTicketPrice()", font)));
            table.addCell(new PdfPCell(new Paragraph("vo.getDuration()", font)));

            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {

        }
    }
}
