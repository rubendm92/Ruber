package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ruber.signatureapp.viewmodels.HeaderViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RuberFrameViewController implements Initializable {

    @FXML
    private HeaderViewController headerViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setHeader(HeaderViewModel header) {
        headerViewController.setViewModel(header);
    }
}
