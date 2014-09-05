package ruber.signaturesheet.sheet_itext.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeachingsTable extends PdfPTable {

    private static final int numberOfHeaders = 5;
    private final List<String> headers;

    public TeachingsTable(TeachingList teachingList, ProfessorList professors) {
        super(numberOfHeaders);
        headers = new ArrayList<>();
        headers.add("Nombre");
        headers.add("Horario");
        headers.add("Ubicación");
        headers.add("Grupo");
        headers.add("Firma");
        createTable(teachingList, professors);
    }

    private void createTable(TeachingList teachingList, ProfessorList professors) {
        setUp();
        addHeader();
        professors.forEach((professor) -> addTeachings(teachingList.getAllTeachingsForProfessor(professor), professor));
    }

    private void setUp() {
        setWidthPercentage(100);
        try {
            setWidths(new int[]{20, 15, 25, 20, 20});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    }

    private void addHeader() {
        getDefaultCell().setBackgroundColor(new BaseColor(4, 70, 130));
        getDefaultCell().setPaddingBottom(5);
        headers.forEach((text) -> addCell(header(text)));
        getDefaultCell().setBackgroundColor(null);
    }

    private Paragraph header(String text) {
        Font font = FontFactory.getFont(BaseFont.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        return new Paragraph(text, font);
    }

    private void addTeachings(TeachingList list, Professor professor) {
        list.forEach((teaching) -> addTeaching(professor, teaching));
    }

    private void addTeaching(Professor professor, Teaching teaching) {
        addCell(new Paragraph(professor.getName()));
        addCell(new Paragraph(teaching.getStringSchedule()));
        addCell(new Paragraph(teaching.getSubjectName()));
        addCell(new Paragraph(teaching.getGroup()));
        try {
            addCell(signatureCell(professor, teaching));
        } catch (BadElementException | IOException e) {
            e.printStackTrace();
        }
    }

    private PdfPTable signatureCell(Professor professor, Teaching teaching) throws BadElementException, IOException {
        PdfPTable signature = new PdfPTable(1);
        signature.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        signature.getDefaultCell().setHorizontalAlignment(ALIGN_CENTER);
        if (teaching.getProfessorSignature(professor) != null) {
            signature.addCell(Image.getInstance(teaching.getProfessorSignature(professor).getSignature()));
            signature.addCell(content(teaching.getProfessorSignature(professor).getProfessor().getName()));
            signature.addCell(content(teaching.getProfessorSignature(professor).getSignTime().toString()));
        }
        else
            signature.addCell("");
        return signature;
    }

    private Paragraph content(String text) {
        Font font = FontFactory.getFont(BaseFont.HELVETICA, 6);
        return new Paragraph(text, font);
    }
}
