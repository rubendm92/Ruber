package ruber.core.viewmodels;

import ruber.core.listeners.OnDniCompletedListener;
import ruber.core.listeners.OnSessionClosedListener;
import ruber.core.model.Observer;
import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;

public class SessionViewModel implements Observer {

    private final DniInputViewModel dniInput;
    private final ProfessorList professors;
    private Professor professor;
    private OnDniCompletedListener onDniCompletedListener;
    private OnSessionClosedListener onSessionClosedListener;

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
        if (onDniCompletedListener != null)
            onDniCompletedListener.execute();
    }

    public void close() {
        professor = null;
        dniInput.clear();
        if (onSessionClosedListener != null)
            onSessionClosedListener.execute();
    }

    public void setOnDniCompletedListener(OnDniCompletedListener listener) {
        this.onDniCompletedListener = listener;
    }

    public void setOnSessionClosedListener(OnSessionClosedListener onSessionClosedListener) {
        this.onSessionClosedListener = onSessionClosedListener;
    }
}
