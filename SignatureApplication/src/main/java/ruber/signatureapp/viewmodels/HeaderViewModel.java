package ruber.signatureapp.viewmodels;

import ruber.core.model.Observable;
import ruber.signatureapp.model.Timer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class HeaderViewModel extends Observable {

    private static final int ONE_MINUTE = 60000;
    private static final int ONE_DAY = ONE_MINUTE * 60 * 24;
    private static final Map<Integer, String> dictionary = numberOfMonthToNameDictionary();

    private final String school;

    public HeaderViewModel(String school) {
        this.school = school;
        new Timer(ONE_MINUTE, this::notifyChanges).start();
        new Timer(ONE_DAY, this::notifyChanges).start();
    }

    public String getSchool() {
        return school;
    }

    public String getCurrentDate() {
        return LocalDate.now().getDayOfMonth() + " de " + numberOfMonthToNameOfMonth(LocalDate.now().getMonthValue());
    }

    private String numberOfMonthToNameOfMonth(int month) {
        return dictionary.get(month);
    }

    private static Map<Integer, String> numberOfMonthToNameDictionary() {
        Map<Integer, String> map = new HashMap<>();
        String[] months = new String[]{"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        for (Integer i = 0; i < months.length; i++) {
            map.put(i + 1, months[i]);
        }
        return map;
    }

    public String getCurrentTime() {
        return timeToString().substring(0, timeToString().lastIndexOf(":"));
    }

    private String timeToString() {
        return LocalTime.now().toString();
    }
}
