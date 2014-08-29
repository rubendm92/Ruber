package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import ruber.core.model.Professor;
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
    private Node professorToReplace;
    @FXML
    private ProfessorInfoViewController professorToReplaceController;
    @FXML
    private Node replacementPanel;
    private SessionViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeSessionPanel.setOnMouseClicked((event) -> viewModel.close());
    }

    public void setViewModel(SessionViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setProfessor(Professor professor) {
        professorInfoViewController.setProfessor(professor);
        professorInfoView.setVisible(true);
    }

    public void clear() {
        professorInfoView.setVisible(false);
        professorInfoViewController.clear();
    }
}
