package ruber.core.model.fake;

import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;

public class Professors {

    public static ProfessorList list() {
        ProfessorList professors = new ProfessorList();
        professors.add(ruben());
        professors.add(replacement());
        return professors;
    }

    public static ProfessorList rubenList() {
        final ProfessorList professors = new ProfessorList();
        professors.add(Professors.ruben());
        return professors;
    }

    public static Professor ruben() {
        return new Professor("12312323", "DÍAZ MARTÍNEZ RUBÉN", "ruben.diaz103@estudiantes.ulpgc.es");
    }

    public static Professor replacement() {
        return new Professor("11111111", "OTRO PROFESOR", "ruben.diaz103@estudiantes.ulpgc.es");
    }

}
