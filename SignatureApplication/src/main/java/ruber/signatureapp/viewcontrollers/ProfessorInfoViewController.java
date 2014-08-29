package ruber.signatureapp.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ruber.core.model.Professor;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorInfoViewController implements Initializable {

    private static final String[] imageExtensions = new String[]{".png", ".jpg", ".JPG"};
    private static final String PATH_TO_IMAGES = "SignatureApplication/src/main/resources/ruber/signatureapp/professors_image/";

    @FXML
    private HBox imagePanel;
    @FXML
    private ImageView imageView;
    @FXML
    private Text professorName;

    private Professor professor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        refreshView();
    }

    private void refreshView() {
        imageView.setImage(image());
        imagePanel.setVisible(true);
        professorName.setText(professor.getName());
    }

    private Image image() {
        for (String extension : imageExtensions)
            if (exists(extension)) return new Image("file:" + PATH_TO_IMAGES + professor.getDni() + extension);
        return new Image("file:" + PATH_TO_IMAGES + "unknown.png");
    }

    private boolean exists(String extension) {
        return new File(PATH_TO_IMAGES + professor.getDni() + extension).isFile();
    }

    public void clear() {
        professor = null;
        professorName.setText("");
        imageView.setImage(null);
        imagePanel.setVisible(false);
    }
}
