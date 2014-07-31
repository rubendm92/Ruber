package ruber.model;

import org.junit.Test;
import ruber.model.fake.Professors;
import ruber.model.fake.Schedules;
import ruber.model.fake.Subjects;
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
    public void teachingListByProfessorName() {
        assertEquals(Teachings.shortList(), teachingList().getTeachingsForProfessor(Professors.ruben()));
    }

    private TeachingList teachingList() {
        TeachingList list = new TeachingList();
        list.add(Teachings.fso());
        list.add(Teachings.is2());
        Teaching teaching = new Teaching(Subjects.is2(), Schedules.is2(), "Pr. Aula 01.01");
        teaching.addProfessor(Professors.replacement());
        list.add(teaching);
        return list;
    }
}
