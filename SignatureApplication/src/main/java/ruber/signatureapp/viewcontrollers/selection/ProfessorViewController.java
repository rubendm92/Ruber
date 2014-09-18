package ruber.signatureapp.viewcontrollers.selection;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorViewController implements Initializable {

    @FXML
    private Node professorView;
    @FXML
    private ImageView professorImage;
    @FXML
    private Label professorName;
    private ProfessorViewModel professorViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        professorView.setOnMouseClicked((event) -> professorViewModel.select());
    }

    public void setViewModel(ProfessorViewModel professorViewModel) {
        this.professorViewModel = professorViewModel;
        initView();
    }

    private void initView() {
        professorImage.setImage(new Image("file:" + professorViewModel.getImagePath()));
        professorName.setText(professorViewModel.getName());
    }
}
