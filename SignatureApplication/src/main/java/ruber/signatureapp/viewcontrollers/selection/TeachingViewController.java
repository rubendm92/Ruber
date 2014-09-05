package ruber.signatureapp.viewcontrollers.selection;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import ruber.signatureapp.viewmodels.teaching.TeachingViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachingViewController implements Initializable {

    private static final String SELECTED = "selected";
    private static final String UNSELECTED = "unselected";
    @FXML
    private Node teachingView;
    @FXML
    protected Label subjectName;
    @FXML
    private Label schedule;
    @FXML
    private Label classroom;
    @FXML
    private CheckBox selected;
    private TeachingViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teachingView.setOnMouseClicked((event) -> toggle());
        selected.setOnAction((event) -> toggle());
    }

    private void toggle() {
        viewModel.toggle();
        selected.setSelected(viewModel.isSelected());
        refreshStyle();
    }

    public void setViewModel(TeachingViewModel viewModel) {
        this.viewModel = viewModel;
        refreshView();
    }

    private void refreshView() {
        refreshContent();
        refreshStyle();
    }

    private void refreshContent() {
        selected.setSelected(viewModel.isSelected());
        subjectName.setText(viewModel.getFormattedSubjectName());
        schedule.setText(viewModel.getStringSchedule());
        classroom.setText(viewModel.getClassroom());
    }

    private void refreshStyle() {
        if (viewModel.isSelected())
            select();
        else
            unselect();
    }

    private void select() {
        teachingView.getStyleClass().remove(UNSELECTED);
        teachingView.getStyleClass().add(SELECTED);
    }

    private void unselect() {
        teachingView.getStyleClass().remove(SELECTED);
        teachingView.getStyleClass().add(UNSELECTED);
    }
}
