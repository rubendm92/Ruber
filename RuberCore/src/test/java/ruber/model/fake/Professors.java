package ruber.model.fake;

import ruber.model.Professor;
import ruber.model.ProfessorList;

public class Professors {

    public static ProfessorList list() {
        ProfessorList list = new ProfessorList();
        list.add(ruben());
        list.add(replacement());
        return list;
    }

    public static Professor ruben() {
        return new Professor("44739382", "DÍAZ MARTÍNEZ RUBÉN", "ruben.diaz103@estudiantes.ulpgc.es");
    }

    public static Professor replacement() {
        return new Professor("44774477", "OTRO PROFESOR", "ruben.diaz103@estudiantes.ulpgc.es");
    }

}
