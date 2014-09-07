package ruber.signatureapp.viewcontrollers.selection;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ruber.signatureapp.viewcontrollers.selection.utils.Index;
import ruber.signatureapp.viewmodels.professor.ProfessorListViewModel;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ProfessorListPanelViewController implements Initializable {

    private static final int PROFESSORS_PER_PAGE = 12;

    private ProfessorListViewModel viewModel;
    private Map<String, Index> indexes;

    @FXML
    private HBox pages;
    @FXML
    private ProfessorListViewController professorListViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(ProfessorListViewModel viewModel) {
        this.viewModel = viewModel;
        professorListViewController.setViewModel(viewModel);
        indexes = new HashMap<>();
        pages.getChildren().removeAll(pages.getChildren());
        setNumberOfPages();
    }

    private void setNumberOfPages() {
        for (int i = 0; i < viewModel.size(); i+=PROFESSORS_PER_PAGE)
            addPage(i);
    }

    private void addPage(int first) {
        int last = (viewModel.size() - first < PROFESSORS_PER_PAGE) ? viewModel.size() - 1 : first + PROFESSORS_PER_PAGE - 1;
        String index = viewModel.get(first).getName().charAt(0) + " - " + viewModel.get(last).getName().charAt(0);
        indexes.put(index, new Index(first, last));
        pages.getChildren().add(createLink(index));
    }

    private Node createLink(String index) {
        final Button link = new Button(index);
        link.setOnAction((event) -> refreshProfessorList(indexes.get(index)));
        link.getStyleClass().add("link");
        return link;
    }

    private void refreshProfessorList(Index index) {
        professorListViewController.showProfessors(index);
    }

    public void showProfessors() {
        professorListViewController.showProfessors(new Index(0, PROFESSORS_PER_PAGE - 1));
    }
}
