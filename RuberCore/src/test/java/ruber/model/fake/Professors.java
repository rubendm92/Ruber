package ruber.model.fake;

import ruber.model.Professor;

public class Professors {

    public static Professor ruben() {
        return new Professor("44739382", "DÍAZ MARTÍNEZ RUBÉN", "ruben.diaz103@estudiantes.ulpgc.es");
    }

    public static Professor replacement() {
        return new Professor("44774477", "OTRO PROFESOR", "ruben.diaz103@estudiantes.ulpgc.es");
    }

}
