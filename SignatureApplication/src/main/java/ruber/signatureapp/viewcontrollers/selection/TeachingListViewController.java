package ruber.signatureapp.viewcontrollers.selection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ruber.core.log.Log;
import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.teaching.TeachingListViewModel;
import ruber.signatureapp.viewmodels.teaching.TeachingViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeachingListViewController implements Initializable, Observer {

    private static final String NO_TEACHING_TO_SIGN = "No tiene docencia asignada en esta franja horaria.";
    private static final String TEACHING_SIGNED = "Ya se ha firmado su docencia para esta franja horaria.";

    @FXML
    private VBox teachingListView;
    @FXML
    private Label teachingMessage;
    @FXML
    private Button replacementButton;
    @FXML
    private Node signatureView;
    @FXML
    private SignatureViewController signatureViewController;

    private TeachingListViewModel teachings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replacementButton.setOnAction((event) -> teachings.showProfessors());
    }

    public void setViewModel(TeachingListViewModel teachings) {
        this.teachings = teachings;
        signatureViewController.setViewModel(teachings.getSignatureViewModel());
    }

    public void showTeachings() {
        restartComponents();
        teachings.forEach(teaching -> addTeaching(teaching));
        replacementButton.setVisible(true);
        signatureEnabled();
    }

    private void addTeaching(TeachingViewModel teachingViewModel) {
        try {
            add(teachingViewModel);
        } catch (IOException ex) {
            Log.getInstance().add(ex);
        }
    }

    private void add(TeachingViewModel teaching) throws IOException {
        teaching.addObserver(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ruber/signatureapp/views/selection/TeachingView.fxml"));
        teachingListView.getChildren().add(loader.load());
        loader.<TeachingViewController>getController().setViewModel(teaching);
    }

    public void noTeachingsUnsigned() {
        restartComponents();
        teachingMessage.setVisible(true);
        teachingMessage.setText(TEACHING_SIGNED);
        replacementButton.setVisible(true);
    }

    public void noTeachingsToSign() {
        restartComponents();
        teachingMessage.setVisible(true);
        teachingMessage.setText(NO_TEACHING_TO_SIGN);
        replacementButton.setVisible(true);
    }

    private void restartComponents() {
        teachingListView.getChildren().clear();
        teachingMessage.setVisible(false);
        replacementButton.setVisible(false);
        signatureDisabled();
    }

    @Override
    public void changed() {
        if (teachings.areThereTeachingsSelected())
            signatureEnabled();
        else
            signatureDisabled();
    }

    private void signatureEnabled() {
        signatureView.setVisible(true);
        signatureViewController.sign();
    }

    private void signatureDisabled() {
        signatureView.setVisible(false);
        signatureViewController.disconnect();
    }

    public byte[] getSignatureImage() {
        return signatureViewController.getSignatureImage();
    }

    public void teachingsSigned() {
        teachingMessage.setVisible(true);
        teachingMessage.setText("La docencia ha sido firmada");
    }

    public boolean hasSignature() {
        return signatureViewController.isWritten();
    }

    public void clear() {
        if (signatureViewController != null)
            signatureViewController.disconnect();
    }
}
