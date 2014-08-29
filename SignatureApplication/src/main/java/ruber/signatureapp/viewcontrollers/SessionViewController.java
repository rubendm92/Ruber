package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import ruber.signatureapp.viewmodels.SessionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionViewController implements Initializable {

    private SessionViewModel viewModel;
    @FXML
    private DniInputViewController dniInputViewController;
    @FXML
    private Node dniInputView;
    @FXML
    private ProfessorViewController professorViewController;
    @FXML
    private Node professorView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setViewModel(SessionViewModel viewModel) {
        this.viewModel = viewModel;
        dniInputViewController.setViewModel(viewModel.getDniInputViewModel());
        professorViewController.setViewModel(viewModel);
        viewModel.setOnDniCompletedListener(() -> startSession());
        viewModel.setOnSessionClosedListener(() -> closeSession());
    }

    private void startSession() {
        dniInputView.setVisible(false);
        professorView.setVisible(true);
        professorViewController.setProfessor(viewModel.getProfessor());
    }

    private void closeSession() {
        dniInputView.setVisible(true);
        dniInputViewController.clear();
        professorView.setVisible(false);
        professorViewController.clear();
    }
}
