package ruber.core.persistence_db.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class DayOfWeekToStringTranslator {
    
    private static final DayOfWeekToStringTranslator INSTANCE = new DayOfWeekToStringTranslator();
    private final HashMap<DayOfWeek, String> days;
    
    private DayOfWeekToStringTranslator() {
        this.days = new HashMap<>();
        initDays();
    }
    
    private void initDays() {
        days.put(DayOfWeek.MONDAY, "1.Lunes");
        days.put(DayOfWeek.TUESDAY, "2.Martes");
        days.put(DayOfWeek.WEDNESDAY, "3.Miércoles");
        days.put(DayOfWeek.THURSDAY, "4.Jueves");
        days.put(DayOfWeek.FRIDAY, "5.Viernes");
    }
    
    public static DayOfWeekToStringTranslator instance() {
        return INSTANCE;
    }
    
    public String dateToDayString(LocalDate date) {
        return days.get(date.getDayOfWeek());
    }
}
