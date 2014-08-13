package ruber.core.model;

import org.junit.Test;
import ruber.core.model.fake.Professors;
import ruber.core.model.fake.Teachings;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TeachingTests {

    private final Signature rubenSignature = new Signature(Professors.ruben(), LocalTime.of(8, 30), new byte[]{});
    private final Signature replacementSignature = new Signature(Professors.replacement(), LocalTime.of(8, 30), new byte[]{});

    @Test
    public void teachingWasGivenByItsProfessor() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), rubenSignature);
        assertThat(teaching.getProfessorForWhomSigned(Professors.ruben()), is(Professors.ruben()));
    }

    @Test
    public void teachingWasReplaced() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), replacementSignature);
        assertThat(teaching.getProfessorForWhomSigned(Professors.replacement()), is(Professors.ruben()));
    }

    @Test
    public void getTeachingsGivenByProfessor() {
        TeachingList resultList = Teachings.longList().getTeachingsForProfessor(Professors.ruben());
        List<Teaching> expectedList = Arrays.asList(Teachings.fso());
        assertThat(resultList, is(expectedList));
    }

    @Test
    public void getFormattedNameFromSubject() {
        assertThat(Teachings.fso().getFormattedSubjectName(), is("FUNDAMENTOS DE LOS SISTEMAS OPERATIVOS"));
        assertThat(Teachings.is2().getFormattedSubjectName(), is("INGENIERÍA DEL SOFTWARE II"));
    }

    @Test
    public void teachingCanBeSignedIfTimeIsTwoHoursBeforeOrAfterStartTime() {
        assertTrue(Teachings.fso().canBeSigned(LocalTime.of(8, 15)));
        assertFalse(Teachings.is2().canBeSigned(LocalTime.of(8, 15)));
    }

    @Test
    public void getProfessorsWithTeachingsAtAGivenTimeWhenThereAreTeachingsSigned() {
        ProfessorList resultList = Teachings.halfSignedList().getProfessorsWithTeachingsForTime(LocalTime.of(10, 15));
        List<Professor> expectedList = Arrays.asList(Professors.replacement());
        assertThat(resultList, is(expectedList));
    }
}
