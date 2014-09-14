package ruber.signaturesheet.sheet_itext;

import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import ruber.signaturesheet.sheet.SignatureSheetGenerator;
import ruber.signaturesheet.sheet_itext.util.PageHeader;
import ruber.signaturesheet.sheet_itext.util.TeachingsTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItextSignatureSheetGenerator implements SignatureSheetGenerator{

    private String degree;
    private TeachingList teachings;
    private ProfessorList professors;

    public ItextSignatureSheetGenerator() {
    }

    @Override
    public List<File> generate(TeachingList list, ProfessorList professors) {
        this.teachings = list;
        this.professors = professors;
        List<File> files = new ArrayList<>();
        Map<String, TeachingList> teachingsByDegree = list.getByDegree();
        teachingsByDegree.entrySet().forEach(entry -> {
            files.add(generateSheet(entry.getKey(), entry.getValue(), professors));
        });
        return files;
    }

    private File generateSheet(String degree, TeachingList teachings, ProfessorList professors) {
        this.degree = degree;
        this.teachings = teachings;
        this.professors = professors;
        Document document = new Document(PageSize.A4, 36, 36, 150, 36);
        File file = null;
        try {
            file = generate(document);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
        return file;
    }

    private File generate(Document document) throws DocumentException, FileNotFoundException {
        String fileName = "Firmas - " + LocalDate.now().toString() + " " + degree + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        writer.setPageEvent(new PageHeader(degree));
        document.open();
        document.add(new TeachingsTable(teachings, professors));
        return new File(fileName);
    }
}
