package ruber.core.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Log {

    private static final Log INSTANCE = new Log();

    private Log() {
    }

    public static Log getInstance() {
        return INSTANCE;
    }

    public synchronized void add(Throwable ex) {
        String filename = "logs/log-" + LocalDate.now().toString() + ".txt";
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(filename), true));
            ex.printStackTrace(writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
