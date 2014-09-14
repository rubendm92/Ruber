package ruber.signaturesheet.application;

import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;
import ruber.core.persistence.PersistenceProvider;
import ruber.core.persistence_db.DatabasePersistenceProvider;
import ruber.signaturesheet.mail.TeachingsReportMail;
import ruber.signaturesheet.sheet_itext.ItextSignatureSheetGenerator;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SheetGenerator {

    private TeachingList teachings;
    private ProfessorList professors;

    public static void main(String[] args) {
        new SheetGenerator().start(Arrays.asList(args));
    }

    private void start(List<String> recipientAddress) {
        PersistenceProvider provider = new DatabasePersistenceProvider("config/database.xml");
        teachings = provider.getTeachingsLoader().load(LocalDate.now());
        professors = provider.getProfessorsLoader().load();
        sendReportWithPdfs(generatePdfsWithSignaturesByDegrees(), recipientAddress);
    }

    private void sendReportWithPdfs(List<File> files, List<String> recipientAddress) {
        recipientAddress.forEach(address -> new TeachingsReportMail(files).sendReport(teachings, address));
        files.forEach(File::delete);
    }

    private List<File> generatePdfsWithSignaturesByDegrees() {
        return new ItextSignatureSheetGenerator().generate(teachings, professors);
    }
}
