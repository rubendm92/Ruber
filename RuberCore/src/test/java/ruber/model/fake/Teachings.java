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
        final Teaching teaching = new Teaching(subjectFso(), schedule(), "Pr. Laboratorio 01.00.04");
        teaching.addProfessor(Professors.ruben());
        return teaching;
    }

    private static Subject subjectFso() {
        return new Subject("40814-FUNDAMENTOS DE LOS SISTEMAS OPERATIVOS (6 Cr.)", "4008 - Grado en Ingeniería Informática");
    }

    private static Schedule schedule() {
        return new Schedule(LocalTime.of(8, 30), LocalTime.of(10, 30), "LABORATORIO 2-2 ( SISTEMAS OPERATIVOS )");
    }

    public static Teaching is2() {
        final Teaching teaching = new Teaching(subjectIs2(), scheduleIs2(), "Pr. Aula 01.01");
        teaching.addProfessor(Professors.replacement());
        return teaching;
    }

    private static Subject subjectIs2() {
        return new Subject("40822-INGENIERÍA DEL SOFTWARE II (6 Cr.)", "4008 - Grado en Ingeniería Informática");
    }

    private static Schedule scheduleIs2() {
        return new Schedule(LocalTime.of(10, 30), LocalTime.of(12, 30), "AULA 1-2");
    }
}
