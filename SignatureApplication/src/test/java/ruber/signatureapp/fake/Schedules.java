package ruber.signatureapp.fake;

import ruber.core.model.Schedule;

import java.time.LocalTime;

public class Schedules {

    public static Schedule fso() {
        return new Schedule(LocalTime.of(8, 30), LocalTime.of(10, 30), "LABORATORIO 2-2 ( SISTEMAS OPERATIVOS )");
    }

    public static Schedule is2() {
        return new Schedule(LocalTime.of(10, 30), LocalTime.of(12, 30), "AULA 1-2");
    }
    public static Schedule now() {
        return new Schedule(start(), end(), "AULA 1-2");
    }

    private static LocalTime start() {
        LocalTime time = LocalTime.now().plusMinutes(30);
        return LocalTime.of(time.getHour(), time.getMinute());
    }

    private static LocalTime end() {
        LocalTime time = LocalTime.now().plusMinutes(150);
        return LocalTime.of(time.getHour(), time.getMinute());
    }
}
