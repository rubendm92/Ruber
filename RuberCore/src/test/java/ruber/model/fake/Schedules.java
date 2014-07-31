package ruber.model.fake;

import ruber.model.Schedule;

import java.time.LocalTime;

public class Schedules {

    public static Schedule fso() {
        return new Schedule(LocalTime.of(8, 30), LocalTime.of(10, 30), "LABORATORIO 2-2 ( SISTEMAS OPERATIVOS )");
    }

    public static Schedule is2() {
        return new Schedule(LocalTime.of(10, 30), LocalTime.of(12, 30), "AULA 1-2");
    }
}
