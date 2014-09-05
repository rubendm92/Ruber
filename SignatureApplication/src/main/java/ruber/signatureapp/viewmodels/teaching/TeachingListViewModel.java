package ruber.signatureapp.viewmodels.teaching;

import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.viewmodels.utils.Listener;

import java.util.ArrayList;

public class TeachingListViewModel extends ArrayList<TeachingViewModel> {

    private final SignatureViewModel signatureViewModel;
    private Listener onShowProfessorListener;

    public TeachingListViewModel(SignatureViewModel signatureViewModel) {
        this.signatureViewModel = signatureViewModel;
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
        return stream().anyMatch(teaching -> teaching.isSelected());
    }
}
