package ruber.signatureapp.viewcontrollers.selection;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.signaturedevices.utils.DataPoint;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignatureViewController implements Initializable {

    private static final double SCALE_SIGNATURE = 2.56;

    @FXML
    private Canvas canvas;
    @FXML
    private ImageView tabletImage;
    @FXML
    private Label tabletLabel;

    private SignatureViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(SignatureViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void sign() {
        initialize("Firme en la tableta");
    }

    public void write() {
        initialize("Escriba en la tableta");
    }

    private void initialize(String message) {
        tabletLabel.setVisible(true);
        tabletLabel.setText(message);
        new Thread(() -> viewModel.enable()).start();
        viewModel.setOnSignatureChangedListener(() -> Platform.runLater(this::draw));
    }

    public void disconnect() {
        if (viewModel == null || !viewModel.isConnected()) return;
        viewModel.clear();
        viewModel.disable();
        clear();
    }

    private void clear() {
        graphics().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tabletImage.setVisible(viewModel.getPoints().isEmpty());
        canvas.setVisible(viewModel.isWritten());
    }

    private void draw() {
        clear();
        graphics().setStroke(Color.BLACK);
        for (int i = 1; i < viewModel.getPoints().size(); i++)
            drawLine(viewModel.getPoints().get(i - 1), viewModel.getPoints().get(i));
    }

    private byte[] signature() {
        byte[] signature = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null, null), null), "png", baos);
            baos.flush();
            signature = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewModel.getPoints().clear();
        return signature;
    }

    private void drawLine(DataPoint p1, DataPoint p2) {
        if (!p1.isTouchingScreen()) return;
        graphics().setLineWidth(p1.getPressure());
        graphics().strokeLine(p1.getX() / SCALE_SIGNATURE, p1.getY() / SCALE_SIGNATURE,
                p2.getX() / SCALE_SIGNATURE, p2.getY() / SCALE_SIGNATURE);
    }

    private GraphicsContext graphics() {
        return canvas.getGraphicsContext2D();
    }

    public byte[] getSignatureImage() {
        return signature();
    }

    public boolean isWritten() {
        return viewModel.isWritten();
    }

}
