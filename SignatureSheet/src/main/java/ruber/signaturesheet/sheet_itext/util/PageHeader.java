package ruber.signaturesheet.sheet_itext.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.time.LocalDate;

public class PageHeader extends PdfPageEventHelper {

    private static final String HEADER_IMAGE = "images/header.png";
    private final String degree;
    private PdfTemplate total;

    public PageHeader(String degree) {
        this.degree = degree;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.setTotalWidth(527);
        table.getDefaultCell().setFixedHeight(90);
        try {
            table.setWidths(new int[]{24, 24});
            table.addCell(Image.getInstance(HEADER_IMAGE));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(rightHeader(writer.getPageNumber()));
            table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch (DocumentException | IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }

    private PdfPTable rightHeader(int pageNumber) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.addCell("HOJA DE FIRMAS\n" + degree);
        table.addCell(date());
        table.addCell(currentPage(pageNumber));
        return table;
    }

    private PdfPTable currentPage(int pageNumber) throws DocumentException {
        PdfPTable currentPage = new PdfPTable(2);
        currentPage.setWidths(new int[]{24, 1});
        currentPage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        currentPage.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        currentPage.addCell("Página " + pageNumber + " de ");
        currentPage.addCell(totalNumberOfPages());
        return currentPage;
    }

    private PdfPCell totalNumberOfPages() throws BadElementException {
        PdfPCell cell = new PdfPCell(Image.getInstance(total));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private String date() {
        String[] date = LocalDate.now().toString().split("-");
        return date[2] + "/" + date[1] + "/" + date[0];
    }
}
