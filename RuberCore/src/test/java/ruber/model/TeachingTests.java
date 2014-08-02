package ruber.model;

import org.junit.Test;
import ruber.model.fake.Professors;
import ruber.model.fake.Teachings;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TeachingTests {

    private final Signature rubenSignature = new Signature(Professors.ruben(), LocalTime.of(8, 30), new byte[]{});
    private final Signature replacementSignature = new Signature(Professors.replacement(), LocalTime.of(8, 30), new byte[]{});

    @Test
    public void teachingWasGivenByOneOfHisProfessors() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), rubenSignature);
        assertTeachingWasGivenByItsProfessor(teaching);
    }

    private void assertTeachingWasGivenByItsProfessor(Teaching teaching) {
        assertEquals(Professors.ruben(), teaching.getProfessorForWhomSigned(Professors.ruben()));
    }

    @Test
    public void teachingWasReplaced() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), replacementSignature);
        assertTeachingWasReplaced(teaching);
    }

    private void assertTeachingWasReplaced(Teaching teaching) {
        assertEquals(Professors.ruben(), teaching.getProfessorForWhomSigned(Professors.replacement()));
    }

    @Test
    public void getTeachingsGivenByProfessor() {
        TeachingList resultList = Teachings.longList().getTeachingsForProfessor(Professors.ruben());
        List<Teaching> expectedList = Arrays.asList(Teachings.fso());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void getFormattedNameFromSubject() {
        assertEquals("FUNDAMENTOS DE LOS SISTEMAS OPERATIVOS", Teachings.fso().getFormattedSubjectName());
        assertEquals("INGENIERÍA DEL SOFTWARE II", Teachings.is2().getFormattedSubjectName());
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
        assertEquals(expectedList, resultList);
    }
}
