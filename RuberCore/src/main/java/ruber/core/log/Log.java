package ruber.core.log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Log {

    private static final Log INSTANCE = new Log();

    private PrintWriter file;

    private Log() {
    }

    public static Log getInstance() {
        return INSTANCE;
    }

    public synchronized void add(Exception ex) {
        try {
            file = new PrintWriter("logs/log-" + LocalDate.now().toString() + "-" + LocalTime.now().toString() +".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ex.printStackTrace(file);
        file.close();
    }
}
