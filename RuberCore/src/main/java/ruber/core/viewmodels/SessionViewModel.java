package ruber.core.viewmodels;

import ruber.core.listeners.OnDniCompletedListener;
import ruber.core.model.Observer;
import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;

public class SessionViewModel implements Observer {

    private final DniInputViewModel dniInput;
    private final ProfessorList professors;
    private Professor professor;

    public SessionViewModel(DniInputViewModel dniInput, ProfessorList professors) {
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

    public void close() {
        professor = null;
        dniInput.clear();
    }

    public void addOnDniCompletedListener(OnDniCompletedListener listener) {
        listener.onDniCompleted();
    }
}
