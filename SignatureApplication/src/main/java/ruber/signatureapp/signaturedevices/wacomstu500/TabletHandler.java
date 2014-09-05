package ruber.signatureapp.signaturedevices.wacomstu500;

import com.WacomGSS.STU.ITabletHandler;
import com.WacomGSS.STU.Protocol.*;
import com.WacomGSS.STU.STUException;

public abstract class TabletHandler implements ITabletHandler {

    @Override
    public void onGetReportException(STUException stue) {
    }

    @Override
    public void onUnhandledReportData(byte[] bytes) {
    }

    @Override
    public abstract void onPenData(PenData pd);

    @Override
    public void onPenDataOption(PenDataOption pdo) {
    }

    @Override
    public void onPenDataEncrypted(PenDataEncrypted pde) {
    }

    @Override
    public void onPenDataEncryptedOption(PenDataEncryptedOption pdeo) {
    }

    @Override
    public void onPenDataTimeCountSequence(PenDataTimeCountSequence penDataTimeCountSequence) {
    }

    @Override
    public void onPenDataTimeCountSequenceEncrypted(PenDataTimeCountSequenceEncrypted penDataTimeCountSequenceEncrypted) {
    }

    @Override
    public void onDevicePublicKey(DevicePublicKey devicePublicKey) {

    }

    @Override
    public void onEncryptionStatus(EncryptionStatus encryptionStatus) {
    }
}
