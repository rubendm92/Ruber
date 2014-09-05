package ruber.signatureapp.viewcontrollers.selection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ruber.signatureapp.viewcontrollers.selection.utils.Index;
import ruber.signatureapp.viewmodels.professor.ProfessorListViewModel;
import ruber.signatureapp.viewmodels.professor.ProfessorViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfessorListViewController implements Initializable {

    private static final int PROFESSOR_PER_COLUMN = 6;
    @FXML
    private HBox professorListView;
    private ProfessorListViewModel professorViewModels;
    private List<Node> professorViews;
    private List<ProfessorViewController> professorViewControllers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setViewModel(ProfessorListViewModel professorViewModels) {
        this.professorViewModels = professorViewModels;
        professorListView.getChildren().removeAll(professorListView.getChildren());
        createViews();
    }

    private void createViews() {
        professorViews = new ArrayList<>();
        professorViewControllers = new ArrayList<>();
        professorViewModels.forEach(professor -> addProfessor(professor));
    }

    private void addProfessor(ProfessorViewModel professor) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ruber/signatureapp/views/selection/ProfessorView.fxml"));
        addView(loader);
        addController(professor, loader.getController());
    }

    private void addView(FXMLLoader loader) {
        try {
            professorViews.add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addController(ProfessorViewModel professor, ProfessorViewController controller) {
        professorViewControllers.add(controller);
        controller.setViewModel(professor);
    }

    public void showProfessors(Index index) {
        professorListView.getChildren().removeAll(professorListView.getChildren());
        int count = 0;
        VBox column = createColumn();
        for (int i = index.getStart(); i <= Math.min(index.getEnd(), professorViews.size() - 1); i++) {
            if (count == 0) column = createColumn();
            column.getChildren().add(professorViews.get(i));
            if (++count == PROFESSOR_PER_COLUMN) {
                professorListView.getChildren().add(column);
                count = 0;
            }
        }
        if (count != 0) professorListView.getChildren().add(column);
    }

    private VBox createColumn() {
        final VBox column = new VBox();
        column.setPrefWidth(professorListView.getWidth() / 2);
        return column;
    }
}
