package ruber.signatureapp.signaturedevices;

import ruber.signatureapp.signaturedevices.utils.DataPoint;
import ruber.signatureapp.viewmodels.utils.Listener;

import java.util.List;

public interface SignatureViewModel {

    public void enable();

    public void disable();

    public List<DataPoint> getPoints();

    public void clear();

    public void setOnSignatureChangedListener(Listener onSignatureChangedListener);

    public void setOnSignatureFinishedListener(Listener onSignatureFinishedListener);

    public boolean isConnected();

    boolean isWritten();
}
