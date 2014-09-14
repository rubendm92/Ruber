package ruber.signatureapp.viewmodels;

import ruber.core.model.Observer;
import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;
import ruber.core.model.ProfessorNotFoundException;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;
import ruber.signatureapp.viewmodels.session.DniInputViewModel;
import ruber.signatureapp.viewmodels.utils.Listener;

import java.util.ArrayList;
import java.util.List;

public class SessionViewModel implements Observer {

    private final DniInputViewModel dniInput;
    private final ProfessorList professors;
    private ProfessorViewModel professor;
    private List<Listener> onSessionStartedListeners;
    private Listener onSessionClosedListener;
    private Listener onWriteNotificationListener;
    private Listener onProfessorNotFoundListener;
    private Listener onProfessorToReplaceChangedListener;
    private ProfessorViewModel professorToReplace;

    public SessionViewModel(DniInputViewModel dniInput, ProfessorList professors) {
        this.dniInput = dniInput;
        this.dniInput.addObserver(this);
        this.professors = professors;
        onSessionStartedListeners = new ArrayList<>();
    }

    public ProfessorViewModel getProfessor() {
        return professor;
    }

    @Override
    public void changed() {
        if (!dniInput.isCompleted()) return;
        startSession();
    }

    private void startSession() {
        try {
            startSessionForProfessor(professors.getByDni(dniInput.getInput()));
        } catch (ProfessorNotFoundException ex) {
            if (onProfessorNotFoundListener != null)
                onProfessorNotFoundListener.execute();
        }
    }

    private void startSessionForProfessor(Professor professor) {
        this.professor = new ProfessorViewModel(professor);
        onSessionStartedListeners.forEach(listener -> listener.execute());
    }

    public void close() {
        if (dniInput.isEmpty()) return;
        dniInput.clear();
        if (onSessionClosedListener != null)
            onSessionClosedListener.execute();
        professor = null;
    }

    public void setOnSessionClosedListener(Listener onSessionClosedListener) {
        this.onSessionClosedListener = onSessionClosedListener;
    }

    public void addOnSessionStartedListener(Listener onSessionStartedListener) {
        onSessionStartedListeners.add(onSessionStartedListener);
    }

    public DniInputViewModel getDniInputViewModel() {
        return dniInput;
    }

    public void writeNotification() {
        if (onWriteNotificationListener != null)
            onWriteNotificationListener.execute();
    }

    public void setOnProfessorNotFoundListener(Listener onProfessorNotFoundListener) {
        this.onProfessorNotFoundListener = onProfessorNotFoundListener;
    }

    public void setOnWriteNotificationListener(Listener onWriteNotificationListener) {
        this.onWriteNotificationListener = onWriteNotificationListener;
    }

    public void setProfessorToReplace(ProfessorViewModel professorToReplace) {
        this.professorToReplace = professorToReplace;
        if (onProfessorToReplaceChangedListener != null)
            onProfessorToReplaceChangedListener.execute();
    }

    public ProfessorViewModel getProfessorToReplace() {
        return professorToReplace;
    }

    public void setOnProfessorToReplaceChangedListener(Listener onProfessorToReplaceChangedListener) {
        this.onProfessorToReplaceChangedListener = onProfessorToReplaceChangedListener;
    }
}
