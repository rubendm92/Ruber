package ruber.signatureapp.viewcontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ruber.core.model.Notification;
import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.TeachingList;
import ruber.signatureapp.model.Timer;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RuberFrameViewController implements Initializable {

    private static final int THREE_SECONDS = 3 * 1000;
    @FXML
    private HeaderViewController headerViewController;
    @FXML
    private SessionViewController sessionViewController;
    @FXML
    private SelectionViewController selectionViewController;
    private RuberFrameViewModel viewModel;
    private Timer timer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setViewModel(RuberFrameViewModel viewModel) {
        this.viewModel = viewModel;
        headerViewController.setViewModel(viewModel.getHeader());
        sessionViewController.setViewModel(viewModel.getSession());
        selectionViewController.setViewModel(viewModel.getSelection());
        this.viewModel.setOnTeachingsSignedListener(this::signed);
        this.viewModel.setOnNotificationWrittenListener(this::written);
    }

    private void signed() {
        selectionViewController.teachingsSigned();
    }

    private void written() {
        selectionViewController.notificationWritten();
    }

    public TeachingList getSelectedTeachings() {
        return viewModel.getSelectedTeachings();
    }

    public Professor getSelectedProfessor() {
        return viewModel.getSelectedProfessor();
    }

    public void teachingsSigned() {
        viewModel.teachingsSigned();
        waitToClear();
    }

    private void waitToClear() {
        timer = new Timer(THREE_SECONDS, this::refresh);
        timer.start();
    }

    private void refresh() {
        timer.stop();
        Platform.runLater(this::clear);
    }

    public void clear() {
        viewModel.clear();
        sessionViewController.closeSession();
    }

    public Professor getProfessorFromSession() {
        return viewModel.getProfessorFromSession();
    }

    public Signature getSignature() {
        return new Signature(getProfessorFromSession(), LocalTime.now(), selectionViewController.getSignatureImage());
    }

    public Notification getNotification() {
        return selectionViewController.getNotification();
    }

    public void notificationWritten() {
        viewModel.notificationWritten();
        waitToClear();
    }

    public boolean hasSignatureUnsaved() {
        return selectionViewController.hasSignatureUnsaved();
    }

    public boolean hasNotificationUnsaved() {
        return selectionViewController.hasNotificationUnsaved();
    }

    public void signing() {
        selectionViewController.signing();
    }
}
