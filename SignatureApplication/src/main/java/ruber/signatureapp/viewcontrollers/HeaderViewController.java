package ruber.signatureapp.viewcontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.HeaderViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderViewController implements Initializable, Observer {

    private HeaderViewModel viewModel;

    @FXML
    private Label school;
    @FXML
    private Label date;
    @FXML
    private Label time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setViewModel(HeaderViewModel viewModel) {
        this.viewModel = viewModel;
        school.setText(viewModel.getSchool());
        viewModel.addObserver(this);
        refresh();
    }

    @Override
    public void changed() {
        Platform.runLater(this::refresh);
    }

    private void refresh() {
        date.setText(viewModel.getCurrentDate());
        time.setText(viewModel.getCurrentTime());
    }
}
