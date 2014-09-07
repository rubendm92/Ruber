package ruber.signatureapp.viewmodels;

import ruber.core.model.Notification;
import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.TeachingList;
import ruber.signatureapp.viewmodels.utils.Listener;
import ruber.signatureapp.views.Command;

import java.time.LocalTime;

public class RuberFrameViewModel {

    private final HeaderViewModel header;
    private final SessionViewModel session;
    private final SelectionViewModel selection;

    private Listener onTeachingsSignedListener;
    private Listener onNotificationWrittenListener;

    public RuberFrameViewModel(HeaderViewModel header, SessionViewModel session, SelectionViewModel selection) {
        this.header = header;
        this.session = session;
        this.selection = selection;
        addListeners();
    }

    private void addListeners() {
        session.addOnSessionStartedListener(() -> showTeachingsFor(session.getProfessor().getModel()));
        session.setOnWriteNotificationListener(() -> selection.writeNotification());
        selection.setOnProfessorSelectedListener(() -> showProfessorToReplace());
    }

    public void clear() {
        session.close();
        selection.clear();
    }

    public void showTeachingsFor(Professor professor) {
        selection.showTeachingsFor(professor);
    }

    public void showProfessors() {
        selection.showProfessorsToReplace();
    }

    public void showProfessorToReplace() {
        session.setProfessorToReplace(selection.getProfessorToReplace());
        selection.showTeachingsFor(selection.getProfessorToReplace().getModel());
    }

    public Professor getProfessorFromSession() {
        return session.getProfessor().getModel();
    }

    public TeachingList getSelectedTeachings() {
        return selection.getSelectedTeachings();
    }

    public HeaderViewModel getHeader() {
        return header;
    }

    public SessionViewModel getSession() {
        return session;
    }

    public SelectionViewModel getSelection() {
        return selection;
    }

    public void setOnNotificationWritten(Command onNotificationWritten) {
        selection.setOnNotificationWritten(onNotificationWritten);
    }

    public void setOnTeachingsSigned(Command onTeachingsSigned) {
        selection.setOnTeachingsSigned(onTeachingsSigned);
    }

    public void setOnTeachingsSignedListener(Listener onTeachingsSignedListener) {
        this.onTeachingsSignedListener = onTeachingsSignedListener;
    }

    public void setOnNotificationWrittenListener(Listener onNotificationWrittenListener) {
        this.onNotificationWrittenListener = onNotificationWrittenListener;
    }

    public Professor getSelectedProfessor() {
        return (session.getProfessorToReplace() == null ? session.getProfessor().getModel() : session.getProfessorToReplace().getModel());
    }

    public void teachingsSigned() {
        if (onTeachingsSignedListener != null)
            onTeachingsSignedListener.execute();
    }

    public void notificationWritten() {
        if (onNotificationWrittenListener != null)
            onNotificationWrittenListener.execute();
    }

    public void addOnSessionClosedListener(Listener listener) {
        session.setOnSessionClosedListener(listener);
    }
}
