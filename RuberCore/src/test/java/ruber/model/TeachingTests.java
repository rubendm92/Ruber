package ruber.model;

import org.junit.Test;
import ruber.model.fake.Professors;
import ruber.model.fake.Teachings;

import java.time.LocalTime;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeachingTests {

    private byte[] fakeSignature = new byte[]{};

    @Test
    public void teachingWasReplaced() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.replacement(), LocalTime.of(8, 30), fakeSignature));
        Map<Professor, Signature> signatures = teaching.getSignatures();
        for (Map.Entry<Professor,Signature> entry : signatures.entrySet())
            assertFalse(entry.getKey().equals(entry.getValue().getProfessor()));
    }

    @Test
    public void teachingWasGivenByOneOfHisProfessors() {
        Teaching teaching = Teachings.fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.ruben(), LocalTime.of(8, 30), fakeSignature));
        Map<Professor, Signature> signatures = teaching.getSignatures();
        for (Map.Entry<Professor,Signature> entry : signatures.entrySet())
            assertTrue(entry.getKey().equals(entry.getValue().getProfessor()));
    }

    @Test
    public void getTeachingsGivenByProfessor() {
        TeachingList resultList = Teachings.longList().getTeachingsForProfessor(Professors.ruben());
        assertEquals(1, resultList.size());
        assertEquals(Teachings.fso(), resultList.get(0));
    }
}
