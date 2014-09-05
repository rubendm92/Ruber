package ruber.signatureapp.viewcontrollers.selection;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import ruber.core.model.Notification;
import ruber.signatureapp.viewmodels.notification.NotificationsViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsViewController implements Initializable {

    @FXML
    private VBox types;
    @FXML
    private SignatureViewController signatureViewController;
    @FXML
    private Node notificationWritten;
    private String selectedType;
    private NotificationsViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(NotificationsViewModel viewModel) {
        this.viewModel = viewModel;
        this.signatureViewController.setViewModel(viewModel.getSignatureViewModel());
        final ToggleGroup group = new ToggleGroup();

        viewModel.getNotificationTypes().forEach(type -> types.getChildren().add(createButton(group, type)));
        initNotifications();

    }

    private void initNotifications() {
        ((RadioButton) types.getChildren().get(types.getChildren().size() - 1)).setSelected(true);
        selectedType = viewModel.getNotificationTypes().get(viewModel.getNotificationTypes().size() - 1);
    }

    private Node createButton(ToggleGroup group, String type) {
        final RadioButton button = new RadioButton(type);
        button.setToggleGroup(group);
        button.setOnAction((event) -> {
            if (button.isSelected())
                selectedType = type;
        });
        return button;
    }

    public void write() {
        notificationWritten.setVisible(false);
        signatureViewController.write();
    }

    public Notification getNotification() {
        return new Notification(selectedType, signatureViewController.getSignatureImage());
    }

    public void written() {
        notificationWritten.setVisible(true);
    }

    public boolean hasNotification() {
        return signatureViewController.isWritten();
    }

    public void clear() {
        if (signatureViewController != null)
            signatureViewController.disconnect();
        initNotifications();
    }
}
