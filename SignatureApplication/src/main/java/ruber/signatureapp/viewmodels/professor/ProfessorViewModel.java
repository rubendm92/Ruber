package ruber.signatureapp.viewmodels.professor;

import ruber.core.model.Professor;
import ruber.signatureapp.viewmodels.utils.SelectableItemViewModel;

import java.io.File;

public class ProfessorViewModel extends SelectableItemViewModel {

    private static final String[] imageExtensions = new String[]{".png", ".jpg", ".JPG"};
    private static final String PATH_TO_IMAGES = "professors_image/";

    private final Professor professor;

    public ProfessorViewModel(Professor professor) {
        super();
        unselect();
        this.professor = professor;
    }

    public Professor getModel() {
        return professor;
    }

    public String getName() {
        return professor.getName();
    }

    public String getImagePath() {
        for (String extension : imageExtensions)
            if (exists(extension)) return PATH_TO_IMAGES + professor.getDni() + extension;
        return PATH_TO_IMAGES + "unknown.png";
    }

    private boolean exists(String extension) {
        return new File(PATH_TO_IMAGES + professor.getDni() + extension).isFile();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) return false;
        return professor.equals(((ProfessorViewModel) object).getModel());
    }
}
