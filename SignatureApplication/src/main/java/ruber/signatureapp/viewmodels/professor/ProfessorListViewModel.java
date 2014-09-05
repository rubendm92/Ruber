package ruber.signatureapp.viewmodels.professor;

import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.utils.Listener;

import java.util.ArrayList;

public class ProfessorListViewModel extends ArrayList<ProfessorViewModel> implements Observer {

    private Listener onProfessorSelectedListener;

    @Override
    public boolean add(ProfessorViewModel professorViewModel) {
        professorViewModel.addObserver(this);
        return super.add(professorViewModel);
    }

    @Override
    public void changed() {
        if (onProfessorSelectedListener != null)
            onProfessorSelectedListener.execute();
    }

    public ProfessorViewModel getSelected() {
        for (ProfessorViewModel professor : this)
            if (professor.isSelected()) return professor;
        return null;
    }

    public void setOnProfessorSelectedListener(Listener onProfessorSelectedListener) {
        this.onProfessorSelectedListener = onProfessorSelectedListener;
    }
}
