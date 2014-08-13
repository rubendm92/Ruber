package ruber.core.persistence_db.util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DayOfWeekToStringTranslatorTests {

    private static final LocalDate MONDAY = LocalDate.of(2014, Month.AUGUST, 4);
    private static final LocalDate TUESDAY = LocalDate.of(2014, Month.AUGUST, 5);
    private static final LocalDate WEDNESDAY = LocalDate.of(2014, Month.AUGUST, 6);
    private static final LocalDate THURSDAY = LocalDate.of(2014, Month.AUGUST, 7);
    private static final LocalDate FRIDAY = LocalDate.of(2014, Month.AUGUST, 8);

    @Test
    public void getDayWithStringGivingDayOfWeek() {
        assertThat(DayOfWeekToStringTranslator.instance().dateToDayString(MONDAY), is("1.Lunes"));
        assertThat(DayOfWeekToStringTranslator.instance().dateToDayString(TUESDAY), is("2.Martes"));
        assertThat(DayOfWeekToStringTranslator.instance().dateToDayString(WEDNESDAY), is("3.Miércoles"));
        assertThat(DayOfWeekToStringTranslator.instance().dateToDayString(THURSDAY), is("4.Jueves"));
        assertThat(DayOfWeekToStringTranslator.instance().dateToDayString(FRIDAY), is("5.Viernes"));
    }
}
