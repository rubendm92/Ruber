package ruber.persistence_db.util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class DayOfWeekToStringTranslatorTests {

    private static final LocalDate MONDAY = LocalDate.of(2014, Month.AUGUST, 4);
    private static final LocalDate TUESDAY = LocalDate.of(2014, Month.AUGUST, 5);
    private static final LocalDate WEDNESDAY = LocalDate.of(2014, Month.AUGUST, 6);
    private static final LocalDate THURSDAY = LocalDate.of(2014, Month.AUGUST, 7);
    private static final LocalDate FRIDAY = LocalDate.of(2014, Month.AUGUST, 8);

    @Test
    public void getDayWithStringGivingDayOfWeek() {
        assertEquals("1.Lunes", DayOfWeekToStringTranslator.instance().dateToDayString(MONDAY));
        assertEquals("2.Martes", DayOfWeekToStringTranslator.instance().dateToDayString(TUESDAY));
        assertEquals("3.Miércoles", DayOfWeekToStringTranslator.instance().dateToDayString(WEDNESDAY));
        assertEquals("4.Jueves", DayOfWeekToStringTranslator.instance().dateToDayString(THURSDAY));
        assertEquals("5.Viernes", DayOfWeekToStringTranslator.instance().dateToDayString(FRIDAY));
    }
}
