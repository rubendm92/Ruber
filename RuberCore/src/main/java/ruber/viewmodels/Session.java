package ruber.viewmodels;

import ruber.model.Observer;
import ruber.model.Professor;
import ruber.model.ProfessorList;

public class Session implements Observer {

    private final DniInput dniInput;
    private final ProfessorList professors;
    private Professor professor;

    public Session(DniInput dniInput, ProfessorList professors) {
        this.dniInput = dniInput;
        this.dniInput.addObserver(this);
        this.professors = professors;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public void changed() {
        professor = professors.getByDni(dniInput.getInput());
    }
}
