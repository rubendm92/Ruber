package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RuberFrameViewController implements Initializable {

    @FXML
    private HeaderViewController headerViewController;
    @FXML
    private SessionViewController sessionViewController;
    private RuberFrameViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setViewModel(RuberFrameViewModel viewModel) {
        this.viewModel = viewModel;
        headerViewController.setViewModel(viewModel.getHeader());
        sessionViewController.setViewModel(viewModel.getSession());
    }
}
