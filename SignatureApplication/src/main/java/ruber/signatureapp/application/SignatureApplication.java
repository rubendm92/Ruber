package ruber.signatureapp.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ruber.core.log.Log;
import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;
import ruber.core.persistence.PersistenceProvider;
import ruber.core.persistence_db.DatabasePersistenceProvider;
import ruber.signatureapp.commands.ClearFrameCommand;
import ruber.signatureapp.commands.SaveNotificationCommand;
import ruber.signatureapp.commands.SignTeachingsCommand;
import ruber.signatureapp.model.Timer;
import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.signaturedevices.wacomstu500.WacomSTU500;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.viewmodels.HeaderViewModel;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;
import ruber.signatureapp.viewmodels.SelectionViewModel;
import ruber.signatureapp.viewmodels.SessionViewModel;
import ruber.signatureapp.viewmodels.session.DniInputViewModel;
import ruber.signatureapp.views.Command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SignatureApplication extends Application {

    private static final int HALF_MINUTE = 30 * 1000;
    private static final String CONFIG_FILE = "config/database.xml";
    private static final String FRAME_VIEW = "/ruber/signatureapp/views/RuberFrameView.fxml";

    private PersistenceProvider provider;
    private RuberFrameViewController frameViewController;
    private RuberFrameViewModel frameViewModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            startApplication(primaryStage);
        } catch (Throwable ex) {
            Log.getInstance().add(ex);
        }
    }

    private void startApplication(Stage primaryStage) throws IOException {
        provider = new DatabasePersistenceProvider(CONFIG_FILE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FRAME_VIEW));
        Scene scene = new Scene(loader.load(), 1280, 1024);
        frameViewController = loader.getController();
        createFrameViewModel();
        frameViewController.setViewModel(frameViewModel);
        setUpStage(primaryStage, scene);
    }

    private void createFrameViewModel() {
        frameViewModel = new RuberFrameViewModel(header(), session(), selection(new WacomSTU500()));
        frameViewModel.setOnTeachingsSigned(new SignTeachingsCommand(frameViewController, provider.getSignedTeachingsSaver()));
        frameViewModel.setOnNotificationWritten(new SaveNotificationCommand(frameViewController, provider.getNotificationSaver()));
    }

    private HeaderViewModel header() {
        return new HeaderViewModel("Edificio de Informática y Matemáticas");
    }

    private SessionViewModel session() {
        return new SessionViewModel(new DniInputViewModel(), professors());
    }

    private ProfessorList professors() {
        return provider.getProfessorsLoader().load();
    }

    private SelectionViewModel selection(SignatureViewModel device) {
        return new SelectionViewModel(teachings(), notificationTypes(), device);
    }

    private TeachingList teachings() {
        return provider.getTeachingsLoader().load(LocalDate.now());
    }

    private List<String> notificationTypes() {
        return provider.getNotificationTypesLoader().load();
    }

    private void setUpStage(Stage primaryStage, Scene scene) {
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        Command command = new ClearFrameCommand(frameViewController, provider);
        frameViewModel.addOnSessionClosedListener(command::execute);
        final Timer timer = new Timer(HALF_MINUTE, () -> Platform.runLater(command::execute));
        primaryStage.addEventFilter(Event.ANY, event -> timer.restart());
        primaryStage.setOnCloseRequest(event -> closeApplication());
        primaryStage.show();
    }

    private void closeApplication() {
        frameViewModel.clear();
        Platform.exit();
        System.exit(0);
    }
}
