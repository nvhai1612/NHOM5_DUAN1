/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views.PDF;

import DomainModel.SPCT;
import ViewModel.HoaDonDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PDFGenerator {

    public void exportInvoiceToPDF(String filePath, HoaDonDTO hoaDon, ArrayList<SPCT> products) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Đường dẫn đến font 'Roboto'
            String fontPath = "E:\\PRO1041.02\\NHOM5_DUAN1\\Roboto\\Roboto-Regular.ttf";

            // Tạo BaseFont và Font
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            Font titleFont = new Font(baseFont, 18, Font.BOLD);

            // Add title
            Paragraph title = new Paragraph("Hóa Đơn Bán Hàng", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            Paragraph footer = new Paragraph("Cảm ơn quý khách!", titleFont);

            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Add invoice details
            
            if (hoaDon.getMaHD() != null) {
                document.add(new Paragraph("Mã HĐ: " + hoaDon.getMaHD(), font));
            }

            if (hoaDon.getTenNV() != null) {
                document.add(new Paragraph("Tên NV: " + hoaDon.getTenNV(), font));
            }

            if (hoaDon.getTenKH() != null) {
                document.add(new Paragraph("Tên KH: " + hoaDon.getTenKH(), font));
            }

            if (hoaDon.getSDT() != null) {
                document.add(new Paragraph("SĐT: " + hoaDon.getSDT(), font));
            }

            if (hoaDon.getDiachi() != null) {
                document.add(new Paragraph("Địa chỉ: " + hoaDon.getDiachi(), font));
            }

            if (hoaDon.getNgayTao() != null) {
                document.add(new Paragraph("Ngày tạo: " + hoaDon.getNgayTao(), font));
            }

            document.add(new Paragraph("Trạng thái: " + (hoaDon.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"), font));

            document.add(new Paragraph("Tổng tiền: " + hoaDon.getDonGia(), font));

            document.add(new Paragraph(" "));

            // Add table
            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100); // Width 100%
            table.setSpacingBefore(10f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Set Column widths
            float[] columnWidths = {1f, 3f, 2f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Phrase("Mã SP", font));
            PdfPCell cell2 = new PdfPCell(new Phrase("Tên SP", font));
            PdfPCell cell3 = new PdfPCell(new Phrase("Số lượng", font));
            PdfPCell cell4 = new PdfPCell(new Phrase("Đơn giá", font));
            PdfPCell cell5 = new PdfPCell(new Phrase("Thành tiền", font));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);

            for (SPCT spct : products) {
                table.addCell(new Phrase(spct.getMaSPCT(), font));
                table.addCell(new Phrase(spct.getTenSP(), font));
                table.addCell(new Phrase(String.valueOf(spct.getSoLuongTon()), font));
                table.addCell(new Phrase(String.valueOf(spct.getDonGia()), font));
                table.addCell(new Phrase(String.valueOf(spct.getSoLuongTon() * spct.getDonGia()), font));
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            document.add(new Paragraph(" "));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
