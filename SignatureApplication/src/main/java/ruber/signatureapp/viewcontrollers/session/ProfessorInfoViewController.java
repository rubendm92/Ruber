package ruber.signatureapp.viewcontrollers.session;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorInfoViewController implements Initializable {

    @FXML
    private HBox imagePanel;
    @FXML
    private ImageView imageView;
    @FXML
    private Text professorName;

    private ProfessorViewModel professorViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setProfessorViewModel(ProfessorViewModel professorViewModel) {
        this.professorViewModel = professorViewModel;
        refreshView();
    }

    private void refreshView() {
        imageView.setImage(new Image("file:" + professorViewModel.getImagePath()));
        imagePanel.setVisible(true);
        professorName.setText(professorViewModel.getName());
    }

    public void clear() {
        professorViewModel = null;
        professorName.setText("");
        imageView.setImage(null);
        imagePanel.setVisible(false);
    }
}
