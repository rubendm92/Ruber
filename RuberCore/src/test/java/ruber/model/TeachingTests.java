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

    private byte[] fakeSignature = new byte[]{};

    @Test
    public void teachingWasReplaced() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.replacement(), LocalTime.of(8, 30), fakeSignature));
        for (Map.Entry<Professor,Signature> entry : teaching.getSignatures().entrySet())
            assertFalse(entry.getKey().equals(entry.getValue().getProfessor()));
    }

    @Test
    public void teachingWasGivenByOneOfHisProfessors() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.ruben(), LocalTime.of(8, 30), fakeSignature));
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
        Teaching fso = Teachings.fso();
        Teaching is2 = Teachings.is2();
        assertTrue(fso.canBeSigned(LocalTime.of(8, 15)));
        assertFalse(is2.canBeSigned(LocalTime.of(8, 15)));
    }

    @Test
    public void getProfessorsWithTeachingsAtAGivenTime() {
        ProfessorList resultList = Teachings.longList().getProfessorsWithTeachingsForTime(LocalTime.of(8, 15));
        List<Professor> expectedList = Arrays.asList(Professors.ruben());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void getProfessorsWithTeachingsAtAGivenTimeWhenThereAreTeachingsSigned() {
        ProfessorList resultList = Teachings.halfSignedList().getProfessorsWithTeachingsForTime(LocalTime.of(10, 15));
        List<Professor> expectedList = Arrays.asList(Professors.replacement());
        assertEquals(expectedList, resultList);
    }
}
