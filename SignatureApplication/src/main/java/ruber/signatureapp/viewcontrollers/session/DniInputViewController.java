package ruber.signatureapp.viewcontrollers.session;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.session.DniInputViewModel;

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
    @FXML
    private Node clearPanel;
    @FXML
    private Button clearButton;
    @FXML
    private Label dniError;

    private void addListeners(VBox box) {
        box.getChildren().stream().forEach((node) -> addListener((Button) node));
    }

    private void addListener(final Button button) {
        button.setOnAction((event) -> performAction(getCharAtButton(button)));
        clearButton.setOnAction((event) -> clear());
    }

    private char getCharAtButton(final Button button) {
        return button.getText().charAt(0);
    }

    private void performAction(char character) {
        if (character == SPACE_CHAR) return;
        if (character == REMOVE_CHAR) delete();
        else type(character);
    }

    private void delete() {
        viewModel.delete();
        dniError.setVisible(false);
        if (viewModel.isEmpty())
            clearPanel.setVisible(false);
    }

    private void type(char character) {
        viewModel.type(character);
        if (!viewModel.isEmpty())
            clearPanel.setVisible(true);
    }


    public void clear() {
        viewModel.clear();
        clearPanel.setVisible(false);
        dniError.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyboard.getChildren().stream().forEach((node) -> addListeners((VBox) node));
    }

    public void setViewModel(DniInputViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addObserver(this);
        changed();
    }

    @Override
    public void changed() {
        display.setText(viewModel.getInput());
    }

    public void showError() {
        dniError.setVisible(true);
    }
}
