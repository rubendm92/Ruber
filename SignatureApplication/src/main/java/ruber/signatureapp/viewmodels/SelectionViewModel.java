package ruber.signatureapp.viewmodels;

import ruber.core.model.Professor;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;
import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.viewmodels.notification.NotificationsViewModel;
import ruber.signatureapp.viewmodels.professor.ProfessorListViewModel;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;
import ruber.signatureapp.viewmodels.teaching.TeachingListViewModel;
import ruber.signatureapp.viewmodels.teaching.TeachingViewModel;
import ruber.signatureapp.viewmodels.utils.Listener;
import ruber.signatureapp.views.Command;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectionViewModel {

    private final NotificationsViewModel notificationsViewModel;
    private final TeachingListViewModel teachingsViewModel;
    private final ProfessorListViewModel professors;
    private final TeachingList teachings;
    private final SignatureViewModel signatureViewModel;

    private Listener onTeachingsChangedListener;
    private Listener onProfessorsChangedListener;
    private Listener onProfessorSelectedListener;
    private Listener onClearListener;

    private Professor currentProfessor;
    private Listener onWriteNotificationListener;
    private Command onTeachingsSigned;
    private Command onNotificationWritten;

    public SelectionViewModel(TeachingList teachings, List<String> notificationTypes, SignatureViewModel signatureViewModel) {
        this.teachings = teachings;
        this.signatureViewModel = signatureViewModel;
        teachingsViewModel = new TeachingListViewModel(signatureViewModel);
        professors = new ProfessorListViewModel();
        notificationsViewModel = new NotificationsViewModel(notificationTypes, signatureViewModel);
        teachingsViewModel.setOnShowProfessorListener(() -> showProfessorsToReplace());
    }

    public void showTeachingsFor(Professor professor) {
        teachingsViewModel.setOnTeachingsSignedListener(() -> onTeachingsSigned.execute());
        currentProfessor = professor;
        teachingsViewModel.clear();
        unsignedTeachings(professor).forEach(teaching -> teachingsViewModel.add(new TeachingViewModel(teaching)));
        if (onTeachingsChangedListener != null)
            onTeachingsChangedListener.execute();
    }

    private Stream<Teaching> unsignedTeachings(Professor professor) {
        return teachings.getCurrentTeachingsForProfessor(professor).stream().filter(teaching -> !teaching.isSignedFor(professor));
    }

    public void setOnTeachingsChangedListener(Listener onTeachingsChangedListener) {
        this.onTeachingsChangedListener = onTeachingsChangedListener;
    }

    public TeachingList getSelectedTeachings() {
        return teachingsViewModel.stream().filter(TeachingViewModel::isSelected).map(TeachingViewModel::getModel).collect(Collectors.toCollection(TeachingList::new));
    }

    public void showProfessorsToReplace() {
        professors.clear();
        teachings.getProfessorsWithTeachingsForTime(LocalTime.now()).forEach(professor -> professors.add(new ProfessorViewModel(professor)));
        if (onProfessorsChangedListener != null)
            onProfessorsChangedListener.execute();
    }

    public ProfessorViewModel getProfessorToReplace() {
        return professors.getSelected();
    }

    public void setOnClearListener(Listener onClearListener) {
        this.onClearListener = onClearListener;
    }

    public void clear() {
        if (onClearListener != null)
            onClearListener.execute();
    }

    public TeachingListViewModel getTeachings() {
        return teachingsViewModel;
    }

    public boolean noTeachingsToSign() {
        return teachings.getCurrentTeachingsForProfessor(currentProfessor).isEmpty();
    }

    public boolean noTeachingUnsigned() {
        return unsignedTeachings(currentProfessor).count() == 0;
    }

    public ProfessorListViewModel getProfessors() {
        return professors;
    }

    public void setOnProfessorsChangedListener(Listener onProfessorsChangedListener) {
        this.onProfessorsChangedListener = onProfessorsChangedListener;
    }

    public void writeNotification() {
        signatureViewModel.setOnSignatureFinishedListener(onNotificationWritten::execute);
        if (onWriteNotificationListener != null)
            onWriteNotificationListener.execute();
    }

    public void showProfessorToReplace() {
        if (onProfessorSelectedListener != null)
            onProfessorSelectedListener.execute();
    }

    public void setOnProfessorSelectedListener(Listener onProfessorSelectedListener) {
        this.onProfessorSelectedListener = onProfessorSelectedListener;
    }

    public void setOnWriteNotificationListener(Listener onWriteNotificationListener) {
        this.onWriteNotificationListener = onWriteNotificationListener;
    }

    public NotificationsViewModel getNotificationViewModel() {
        return notificationsViewModel;
    }

    public void setOnTeachingsSigned(Command onTeachingsSigned) {
        this.onTeachingsSigned = onTeachingsSigned;
    }

    public void setOnNotificationWritten(Command onNotificationWritten) {
        this.onNotificationWritten = onNotificationWritten;
    }
}
