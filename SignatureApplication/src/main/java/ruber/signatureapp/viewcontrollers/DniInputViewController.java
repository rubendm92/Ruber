package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.DniInputViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class DniInputViewController implements Initializable, Observer {

    private DniInputViewModel viewModel;

    private static final char REMOVE_CHAR = '⌫';
    private static final char SPACE_CHAR = '␣';

    @FXML
    private HBox keyboard;
    @FXML
    private Label display;

    private void addListeners(VBox box) {
        box.getChildren().stream().forEach((node) -> addListener((Button) node));
    }

    private void addListener(final Button button) {
        button.setOnAction((event) -> performAction(getCharAtButton(button)));
    }

    private char getCharAtButton(final Button button) {
        return button.getText().charAt(0);
    }

    private void performAction(char character) {
        if (character == SPACE_CHAR) return;
        if (character == REMOVE_CHAR) viewModel.delete();
        else viewModel.type(character);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyboard.getChildren().stream().forEach((node) -> addListeners((VBox) node));
    }

    public void setViewModel(DniInputViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addObserver(this);
    }

    @Override
    public void changed() {
        display.setText(viewModel.getInput());
    }
}
