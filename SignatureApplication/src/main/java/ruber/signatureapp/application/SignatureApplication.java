package ruber.signatureapp.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ruber.core.model.Professor;
import ruber.core.model.Teaching;
import ruber.core.persistence.PersistenceProvider;
import ruber.core.persistence_db.DatabasePersistenceProvider;
import ruber.signatureapp.commands.ClearFrameCommand;
import ruber.signatureapp.commands.SaveNotificationCommand;
import ruber.signatureapp.commands.SignTeachingsCommand;
import ruber.signatureapp.model.Timer;
import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.signaturedevices.wacomstu500.WacomSTU500;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.viewmodels.*;
import ruber.signatureapp.viewmodels.session.DniInputViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SignatureApplication extends Application {

    private static final int ONE_MINUTE = 60 * 1000;
    private static final String CONFIG_FILE = "config\\database.xml";
    private static final String FRAME_VIEW = "/ruber/signatureapp/views/RuberFrameView.fxml";
    private PersistenceProvider provider;
    private RuberFrameViewController frameViewController;
    private RuberFrameViewModel frameViewModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        provider = new DatabasePersistenceProvider(CONFIG_FILE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FRAME_VIEW));
        Scene scene = new Scene(loader.load());
        frameViewController = loader.getController();
        frameViewModel = frameViewModel();
        frameViewController.setViewModel(frameViewModel);
        setUpStage(primaryStage, scene, timer());
    }

    private Timer timer() {
        return new Timer(ONE_MINUTE, () -> Platform.runLater(() -> new ClearFrameCommand(frameViewModel, frameViewController, provider).execute()));
    }

    private void setUpStage(Stage primaryStage, Scene scene, Timer timer) throws IOException {
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.addEventFilter(Event.ANY, event -> timer.restart());
        primaryStage.setOnCloseRequest((event) -> closeApplication());
        primaryStage.show();
    }

    private void closeApplication() {
        frameViewModel.clear();
        Platform.exit();
        System.exit(0);
    }

    private RuberFrameViewModel frameViewModel() {
        frameViewModel = new RuberFrameViewModel(header(), session(), selection(new WacomSTU500()));
        frameViewModel.setOnTeachingsSigned(new SignTeachingsCommand(frameViewController, provider.getSignedTeachingsSaver()));
        frameViewModel.setOnNotificationWritten(new SaveNotificationCommand(frameViewController, provider.getNotificationSaver()));
        return frameViewModel;
    }

    private HeaderViewModel header() {
        return new HeaderViewModel("Edificio de Informática y Matemáticas");
    }

    private SessionViewModel session() {
        return new SessionViewModel(new DniInputViewModel(), provider.getProfessorsLoader().load());
    }

    private SelectionViewModel selection(SignatureViewModel device) {
        return new SelectionViewModel(provider.getTeachingsLoader().load(LocalDate.now()), provider.getNotificationTypesLoader().load(),device);
    }
}
