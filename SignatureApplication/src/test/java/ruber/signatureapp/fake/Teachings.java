package ruber.signatureapp.fake;

import ruber.core.model.Signature;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;

import java.time.LocalTime;

public class Teachings {

    public static TeachingList longList() {
        final TeachingList teachings = new TeachingList();
        teachings.add(fso());
        teachings.add(is2());
        return teachings;
    }

    public static TeachingList halfSignedList() {
        final TeachingList teachings = new TeachingList();
        Teaching teaching = fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.ruben(), LocalTime.now(), new byte[]{}));
        teachings.add(teaching);
        teachings.add(is2());
        return teachings;
    }

    public static TeachingList fsoList() {
        final TeachingList teachings = new TeachingList();
        teachings.add(fso());
        return teachings;
    }

    public static TeachingList is2List() {
        final TeachingList teachings = new TeachingList();
        teachings.add(Teachings.is2());
        return teachings;
    }

    public static TeachingList nowList() {
        final TeachingList teachings = new TeachingList();
        teachings.add(Teachings.teachingNow());
        teachings.add(Teachings.teachingNow());
        teachings.add(Teachings.teachingNow());
        teachings.add(Teachings.teachingNow());
        teachings.add(Teachings.teachingNow());
        teachings.add(Teachings.teachingNow());
        return teachings;
    }

    public static Teaching fso() {
        final Teaching teaching = new Teaching(Subjects.fso(), Schedules.fso(), "Pr. Laboratorio 01.00.04");
        teaching.addProfessor(Professors.ruben());
        return teaching;
    }

    public static Teaching is2() {
        final Teaching teaching = new Teaching(Subjects.is2(), Schedules.is2(), "Pr. Aula 01.01");
        teaching.addProfessor(Professors.replacement());
        return teaching;
    }

    public static Teaching teachingNow() {
        final Teaching teaching = new Teaching(Subjects.fso(), Schedules.now(), "Pr. Laboratorio 01.00.04");
        teaching.addProfessor(Professors.ruben());
        return teaching;
    }
}
