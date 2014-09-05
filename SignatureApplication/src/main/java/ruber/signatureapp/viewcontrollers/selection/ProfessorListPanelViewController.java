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
        setNumberOfPages();
    }

    private void setNumberOfPages() {
        indexes = new HashMap<>();
        pages.getChildren().removeAll(pages.getChildren());
        for(int pageNumber = 0; pageNumber < numberOfPages(); pageNumber++)
            addPage(pageNumber);
    }

    private int numberOfPages() {
        return Math.round((float) viewModel.size() / (float) PROFESSORS_PER_PAGE);
    }

    private void addPage(int page) {
        int last = last(page * PROFESSORS_PER_PAGE);
        String index = viewModel.get(first(page)).getName().charAt(0) + " - " + viewModel.get(last).getName().charAt(0);
        indexes.put(index, new Index(first(page), last));
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

    private int first(int pageNumber) {
        return pageNumber * PROFESSORS_PER_PAGE;
    }

    private int last(int pageNumber) {
        return (viewModel.size() - pageNumber < PROFESSORS_PER_PAGE) ? viewModel.size() - 1 : pageNumber + PROFESSORS_PER_PAGE - 1;
    }

    public void showProfessors() {
        professorListViewController.showProfessors(new Index(0, 11));
    }
}
