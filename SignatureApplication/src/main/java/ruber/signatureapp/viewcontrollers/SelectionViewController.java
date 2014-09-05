package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import ruber.core.model.Notification;
import ruber.core.model.Signature;
import ruber.signatureapp.viewcontrollers.selection.NotificationsViewController;
import ruber.signatureapp.viewcontrollers.selection.ProfessorListPanelViewController;
import ruber.signatureapp.viewcontrollers.selection.TeachingListViewController;
import ruber.signatureapp.viewmodels.professor.ProfessorListViewModel;
import ruber.signatureapp.viewmodels.SelectionViewModel;
import ruber.signatureapp.viewmodels.teaching.TeachingListViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectionViewController implements Initializable {

    private SelectionViewModel viewModel;
    @FXML
    private TeachingListViewController teachingListViewController;
    @FXML
    private ProfessorListPanelViewController professorListViewController;
    @FXML
    private NotificationsViewController notificationsViewController;
    @FXML
    private Node teachingListView;
    @FXML
    private Node professorListView;
    @FXML
    private Node notificationsView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(SelectionViewModel viewModel) {
        this.viewModel = viewModel;
        notificationsViewController.setViewModel(viewModel.getNotificationViewModel());
        setListeners();
    }

    private void setListeners() {
        viewModel.setOnTeachingsChangedListener(() -> showTeachings(viewModel.getTeachings()));
        viewModel.setOnProfessorsChangedListener(() -> showProfessors(viewModel.getProfessors()));
        viewModel.setOnWriteNotificationListener(() -> writeNotification());
        viewModel.setOnClearListener(() -> clear());
    }

    private void writeNotification() {
        clear();
        notificationsView.setVisible(true);
        notificationsViewController.write();
    }

    private void showProfessors(ProfessorListViewModel professors) {
        teachingListView.setVisible(false);
        professorListView.setVisible(true);
        professorListViewController.setViewModel(professors);
        professorListViewController.showProfessors();
        professors.setOnProfessorSelectedListener(() -> viewModel.showProfessorToReplace());
    }

    private void clear() {
        teachingListView.setVisible(false);
        professorListView.setVisible(false);
        notificationsView.setVisible(false);
        teachingListViewController.clear();
        notificationsViewController.clear();
    }

    private void showTeachings(TeachingListViewModel teachings) {
        clear();
        teachingListView.setVisible(true);
        teachingListViewController.setViewModel(teachings);
        if (viewModel.noTeachingsToSign())
            teachingListViewController.noTeachingsToSign();
        else if (viewModel.noTeachingUnsigned())
            teachingListViewController.noTeachingsUnsigned();
        else
            teachingListViewController.showTeachings();
    }

    public byte[] getSignatureImage() {
        return teachingListViewController.getSignatureImage();
    }

    public Notification getNotification() {
        return notificationsViewController.getNotification();
    }

    public void teachingsSigned() {
        teachingListViewController.teachingsSigned();
    }

    public void notificationWritten() {
        notificationsViewController.written();
    }

    public boolean hasSignatureUnsaved() {
        return (teachingListView.isVisible() && teachingListViewController.hasSignature());
    }

    public boolean hasNotificationUnsaved() {
        return (notificationsView.isVisible() && notificationsViewController.hasNotification());
    }
}
