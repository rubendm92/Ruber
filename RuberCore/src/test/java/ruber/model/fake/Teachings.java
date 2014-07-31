package ruber.model.fake;

import ruber.model.*;

import java.time.LocalTime;

public class Teachings {

    public static TeachingList shortList() {
        final TeachingList teachings = new TeachingList();
        teachings.add(fso());
        return teachings;
    }

    public static TeachingList longList() {
        final TeachingList teachings = new TeachingList();
        teachings.add(fso());
        teachings.add(is2());
        return teachings;
    }

    public static TeachingList signedList() {
        final TeachingList teachings = new TeachingList();
        Teaching teaching = fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.ruben(), LocalTime.now(), new byte[]{}));
        teachings.add(teaching);
        teaching = is2();
        teaching.sign(Professors.replacement(), new Signature(Professors.replacement(), LocalTime.now(), new byte[]{}));
        teachings.add(teaching);
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

    public static TeachingList oneReplacementList() {
        final TeachingList teachings = new TeachingList();
        Teaching teaching = fso();
        teaching.sign(Professors.ruben(), new Signature(Professors.replacement(), LocalTime.now(), new byte[]{}));
        teachings.add(teaching);
        teaching = is2();
        teaching.sign(Professors.replacement(), new Signature(Professors.replacement(), LocalTime.now(), new byte[]{}));
        teachings.add(teaching);
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
}
