package ruber.core.mail;

import ruber.core.model.Professor;
import ruber.core.model.ProfessorNotFoundException;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;

import java.time.LocalDate;

public class TeachingsReplacedMail extends TeachingsSignedMail {

    public TeachingsReplacedMail() {
    }

    public void send(TeachingList teachings, Professor professor) {
        this.teachings = teachings;
        this.professor = professor;
        if (professorReplaced().equals(professor)) return;
        super.send(mail());
    }

    @Override
    protected String recipient() {
        return professorReplaced().getEmail();
    }

    private Professor professorReplaced() {
        for (Teaching teaching : teachings)
            return teaching.getProfessorForWhomSigned(professor);
        throw new ProfessorNotFoundException();
    }

    @Override
    protected String subject() {
        return "Docencia sustituida " + LocalDate.now().toString();
    }

    @Override
    protected String heading() {
        return professor.getName() + " firmó la docencia que usted tenía asignada en las siguientes asignaturas: \n\n";
    }
}
