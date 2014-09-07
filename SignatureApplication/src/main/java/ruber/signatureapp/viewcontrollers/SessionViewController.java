package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import ruber.signatureapp.viewcontrollers.session.DniInputViewController;
import ruber.signatureapp.viewcontrollers.session.ProfessorViewController;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;
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
        professorViewController.setSessionViewModel(viewModel);
        viewModel.addOnSessionStartedListener(() -> startSession());
        viewModel.setOnProfessorNotFoundListener(() -> dniInputViewController.showError());
        viewModel.setOnProfessorToReplaceChangedListener(() -> showProfessorToReplace(viewModel.getProfessorToReplace()));
    }

    private void showProfessorToReplace(ProfessorViewModel professor) {
        professorViewController.showProfessorToReplace(professor);
    }

    private void startSession() {
        dniInputView.setVisible(false);
        professorView.setVisible(true);
        professorViewController.setProfessor(viewModel.getProfessor());
    }

    public void closeSession() {
        dniInputView.setVisible(true);
        dniInputViewController.clear();
        professorView.setVisible(false);
        professorViewController.clear();
    }
}
