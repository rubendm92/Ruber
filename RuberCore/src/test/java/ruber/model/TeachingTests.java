package ruber.model;

import org.junit.Test;
import ruber.model.fake.Professors;
import ruber.model.fake.Teachings;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TeachingTests {

    private final byte[] fakeSignature = new byte[]{};
    private final Signature rubenSignature = new Signature(Professors.ruben(), LocalTime.of(8, 30), fakeSignature);
    private final Signature replacementSignature = new Signature(Professors.replacement(), LocalTime.of(8, 30), fakeSignature);

    @Test
    public void teachingWasReplaced() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), replacementSignature);
        assertTeachingWasReplaced(teaching);
    }

    private void assertTeachingWasReplaced(Teaching teaching) {
        for (Map.Entry<Professor,Signature> entry : teaching.getSignatures().entrySet())
            assertFalse(entry.getKey().equals(entry.getValue().getProfessor()));
    }

    @Test
    public void teachingWasGivenByOneOfHisProfessors() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), rubenSignature);
        assertTeachingWasGivenByItsProfessor(teaching);
    }

    private void assertTeachingWasGivenByItsProfessor(Teaching teaching) {
        for (Map.Entry<Professor,Signature> entry : teaching.getSignatures().entrySet())
            assertTrue(entry.getKey().equals(entry.getValue().getProfessor()));
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
