package ruber.signatureapp.viewcontrollers.session;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;
import ruber.signatureapp.viewmodels.SessionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorViewController implements Initializable {

    @FXML
    private Node professorInfoView;
    @FXML
    private ProfessorInfoViewController professorInfoViewController;
    @FXML
    private Node closeSessionPanel;
    @FXML
    private Node sendNotificationPanel;
    @FXML
    private ProfessorInfoViewController professorToReplaceViewController;
    @FXML
    private Node replacementPanel;
    private SessionViewModel sessionViewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendNotificationPanel.setOnMouseClicked((event) -> sessionViewModel.writeNotification());
        closeSessionPanel.setOnMouseClicked((event) -> sessionViewModel.close());
    }

    public void setSessionViewModel(SessionViewModel sessionViewModel) {
        this.sessionViewModel = sessionViewModel;
    }

    public void setProfessor(ProfessorViewModel professorViewModel) {
        professorInfoViewController.setProfessorViewModel(professorViewModel);
        professorInfoView.setVisible(true);
        replacementPanel.setVisible(false);
    }

    public void clear() {
        professorInfoView.setVisible(false);
        professorInfoViewController.clear();
    }

    public void showProfessorToReplace(ProfessorViewModel professor) {
        professorToReplaceViewController.setProfessorViewModel(professor);
        replacementPanel.setVisible(true);
    }
}
