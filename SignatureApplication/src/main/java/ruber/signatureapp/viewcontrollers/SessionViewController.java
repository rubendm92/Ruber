package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ruber.signatureapp.viewmodels.SessionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionViewController implements Initializable {

    private SessionViewModel viewModel;
    @FXML
    private DniInputViewController dniInputViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setViewModel(SessionViewModel viewModel) {
        this.viewModel = viewModel;
        dniInputViewController.setViewModel(viewModel.getDniInputViewModel());
    }
}
