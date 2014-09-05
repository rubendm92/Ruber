package ruber.core.mail;

import ruber.core.model.Professor;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;

import java.time.LocalDate;

public class TeachingsSignedMail extends MailSender {

    protected TeachingList teachings;
    protected Professor professor;

    public TeachingsSignedMail() {
    }

    public void send(TeachingList teachings, Professor professor) {
        if (professor.getEmail().equals("")) return;
        this.teachings = teachings;
        this.professor = professor;
        super.send(mail());
    }

    protected Mail mail() {
        return new Mail(recipient(), subject(), message());
    }

    protected String recipient() {
        return professor.getEmail();
    }

    protected String subject() {
        return "Docencia firmada " + LocalDate.now().toString();
    }

    private String message() {
        return heading() + content() + footer();
    }

    protected String heading() {
        return "Ha firmado la docencia de las siguientes asignaturas: \n\n";
    }

    protected String content() {
        String message = "";
        for(Teaching teaching : teachings)
            message += teaching.getSubjectName() + " " + teaching.getStringSchedule() + " " + teaching.getClassroom() + " " + teaching.getGroup() + "\n";
        return message;
    }

    protected String footer() {
        return "\n\n\nSi desea dejar de recibir estas notificaciones o cambiar su dirección de contacto, responda a este correo indicando su petición.";
    }
}
