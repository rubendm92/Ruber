package ruber.core.persistence_file;

import ruber.core.log.Log;
import ruber.core.model.Professor;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;
import ruber.core.persistence.SignedTeachingsSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileSignedTeachingsSaver implements SignedTeachingsSaver {

    private static final FileSignedTeachingsSaver INSTANCE = new FileSignedTeachingsSaver();
    private final String filename;
    private String text;
    private Professor professor;

    private FileSignedTeachingsSaver() {
        this.filename = "signatures/" + LocalDate.now().toString() + ".txt";
    }

    public static FileSignedTeachingsSaver getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Professor professor, TeachingList teachings) {
        text = "";
        this.professor = professor;
        teachings.forEach(this::save);
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(filename), true));
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void save(Teaching teaching) {
        text += getIp() + ";";
        text += Date.valueOf(LocalDate.now()) + ";";
        text += teaching.getStringSchedule() + ";";
        text += teaching.getProfessorForWhomSigned(professor).getName() + ";";
        text += teaching.getDegree() + ";";
        text += teaching.getSubjectName() + ";";
        text += teaching.getGroup() + ";";
        text += teaching.getClassroom() + ";";
        text += Time.valueOf(LocalTime.now()) + ";";
        text += professor.getName()+ "\n";
    }

    private static String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Log.getInstance().add(ex);
            return "IPException";
        }
    }
}
