package ruber.signatureapp.viewmodels.teaching;

import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.viewmodels.utils.Listener;
import ruber.signatureapp.viewmodels.utils.SelectableItemViewModel;

import java.time.LocalTime;
import java.util.ArrayList;

public class TeachingListViewModel extends ArrayList<TeachingViewModel> {

    private final SignatureViewModel signatureViewModel;
    private Listener onShowProfessorListener;

    public TeachingListViewModel(SignatureViewModel signatureViewModel) {
        this.signatureViewModel = signatureViewModel;
    }

    @Override
    public boolean add(TeachingViewModel teachingViewModel) {
        boolean result = super.add(teachingViewModel);
        stream().filter(teaching -> teaching.getModel().canBeSigned(LocalTime.now())).forEach(SelectableItemViewModel::select);
        return result;
    }

    public SignatureViewModel getSignatureViewModel() {
        return signatureViewModel;
    }

    public void setOnShowProfessorListener(Listener onShowProfessorListener) {
        this.onShowProfessorListener = onShowProfessorListener;
    }

    public void setOnTeachingsSignedListener(Listener onTeachingsSignedListener) {
        signatureViewModel.setOnSignatureFinishedListener(onTeachingsSignedListener);
    }

    public void showProfessors() {
        if (onShowProfessorListener != null)
            onShowProfessorListener.execute();
    }

    public boolean areThereTeachingsSelected() {
        return stream().anyMatch(SelectableItemViewModel::isSelected);
    }
}
