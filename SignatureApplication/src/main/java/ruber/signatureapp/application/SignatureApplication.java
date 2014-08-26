package ruber.signatureapp.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.viewmodels.HeaderViewModel;

import java.io.IOException;

public class SignatureApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ruber/signatureapp/views/RuberFrameView.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 1200, 800));
        RuberFrameViewController frame = loader.getController();
        frame.setHeader(new HeaderViewModel("Edificio de Informática y Matemáticas"));
        primaryStage.show();
    }
}
