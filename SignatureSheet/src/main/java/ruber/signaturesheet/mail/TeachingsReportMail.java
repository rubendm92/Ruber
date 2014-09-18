package ruber.signaturesheet.mail;

import ruber.core.mail.Mail;
import ruber.core.mail.MailSender;
import ruber.core.model.TeachingList;
import ruber.signaturesheet.report.TeachingsReporter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class TeachingsReportMail extends MailSender{

    private final List<File> files;
    private final LocalDate date;

    public TeachingsReportMail(List<File> files, LocalDate date) {
        super();
        this.files = files;
        this.date = date;
    }

    public void sendReport(TeachingList teachings, String recipientAddress) {
        send(new Mail(recipientAddress, "Informe de docencia del " + today(), content(teachings)));
    }

    private String content(TeachingList teachings) {
        return new TeachingsReporter().generate(teachings);
    }

    @Override
    protected Message message(Mail mail) throws MessagingException {
        Message message = super.message(mail);
        message.setText("");
        message.setContent(content(mail));
        return message;
    }

    private MimeMultipart content(Mail mail) throws MessagingException {
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(text(mail));
        for (File file : files)
            multipart.addBodyPart(attachment(file));
        return multipart;
    }

    private MimeBodyPart text(final Mail mail) throws MessagingException {
        return new MimeBodyPart() {{
            setText(mail.getMessage());
        }};
    }

    private MimeBodyPart attachment(final File file) throws MessagingException {
        return new MimeBodyPart(){{
            FileDataSource fileDataSource = new FileDataSource(file.getAbsolutePath());
            setDataHandler(new DataHandler(fileDataSource));
            setFileName(fileDataSource.getName());
        }};
    }

    private String today() {
        String[] dateSplit = date.toString().split("-");
        return dateSplit[2] + " de " + month(dateSplit[1]) + " del " + dateSplit[0];
    }

    private String month(String monthNumber) {
        switch (monthNumber) {
            case "01": return "enero";
            case "02": return "febrero";
            case "03": return "marzo";
            case "04": return "abril";
            case "05": return "mayo";
            case "06": return "junio";
            case "07": return "julio";
            case "08": return "agosto";
            case "09": return "septiembre";
            case "10": return "octubre";
            case "11": return "noviembre";
            default  : return "diciembre";
        }
    }
}
