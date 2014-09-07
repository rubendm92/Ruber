package ruber.signatureapp.signaturedevices.wacomstu500;

import com.WacomGSS.STU.ITabletHandler;
import com.WacomGSS.STU.Protocol.Capability;
import com.WacomGSS.STU.Protocol.EncodingMode;
import com.WacomGSS.STU.Protocol.PenData;
import com.WacomGSS.STU.Protocol.ProtocolHelper;
import com.WacomGSS.STU.STUException;
import com.WacomGSS.STU.Tablet;
import com.WacomGSS.STU.UsbDevice;
import ruber.signatureapp.signaturedevices.SignatureViewModel;
import ruber.signatureapp.signaturedevices.utils.DataPoint;
import ruber.signatureapp.viewmodels.utils.Listener;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class WacomSTU500 implements SignatureViewModel {

    private static final String PATH_TO_DLL = "C:\\Program Files\\Wacom STU SDK\\Java\\jar\\Win32";

    private Tablet tablet;
    private Capability capability;
    private TabletBufferedImage tabletImage;
    private final List<DataPoint> points = new ArrayList<>();
    private boolean isDown = false;
    private String currentButton;

    private Listener onSignatureChangedListener;
    private Listener onSignatureFinishedListener;

    public WacomSTU500() {
        try {
            loadDll();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadDll() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.setProperty("java.library.path", PATH_TO_DLL);
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
    }

    @Override
    public void enable() {
        if (tablet != null) return;
        tablet = new Tablet();
        if ((UsbDevice.getUsbDevices() == null) || UsbDevice.getUsbDevices().length <= 0)
            throw new RuntimeException("No devices present");
        if (tablet.usbConnect(UsbDevice.getUsbDevices()[0], true) != 0)
            throw new RuntimeException("Connect failed");
        initTablet();
    }

    private void initTablet() {
        initCapability();
        loadImage();
        tablet.addTabletHandler(handler());
    }

    private ITabletHandler handler() {
        return new TabletHandler() {

            @Override
            public void onPenData(PenData penData) {
                onNewPoint(penDataToDataPoint(penData));
            }
        };
    }

    private DataPoint penDataToDataPoint(PenData penData) {
        return new DataPoint(getScaleX(penData), getScaleY(penData), penData.getPressure(), capability.getTabletMaxPressure(), penData.getSw() != 0);
    }

    private double getScaleX(PenData penData) {
        return ((double) capability.getScreenWidth() / capability.getTabletMaxX()) * penData.getX();
    }

    private double getScaleY(PenData penData) {
        return ((double) capability.getScreenHeight() / capability.getTabletMaxY()) * penData.getY();
    }

    private void initCapability() {
        try {
            capability = tablet.getCapability();
        } catch (STUException e) {
            e.printStackTrace();
        }
    }

    private void loadImage() {
        tabletImage = new TabletBufferedImage(capability.getScreenWidth(), capability.getScreenHeight(), BufferedImage.TYPE_INT_RGB);
        try {
            tablet.writeImage(EncodingMode.EncodingMode_1bit, ProtocolHelper.flatten(tabletImage, tabletImage.getWidth(), tabletImage.getHeight(), false));
        } catch (STUException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disable() {
        if (tablet == null) return;
        try {
            tablet.setClearScreen();
        } catch (STUException ex) {
        }
        tablet.disconnect();
        points.clear();
        tablet = null;
    }

    @Override
    public List<DataPoint> getPoints() {
        return points;
    }

    @Override
    public void setOnSignatureChangedListener(Listener onSignatureChangedListener) {
        this.onSignatureChangedListener = onSignatureChangedListener;
    }

    @Override
    public void setOnSignatureFinishedListener(Listener onSignatureFinishedListener) {
        this.onSignatureFinishedListener = onSignatureFinishedListener;
    }

    private void onNewPoint(DataPoint point) {
        if ((point.isTouchingScreen()) && (!isDown))
            startedDrawing(point);
        else if (!(point.isTouchingScreen()) && (isDown))
            stoppedDrawing(point);
        else if (isDrawing(point))
            draw(point);
    }

    private void startedDrawing(DataPoint point) {
        isDown = true;
        currentButton = tabletImage.getButton(point.getX(), point.getY());
    }

    private void stoppedDrawing(DataPoint point) {
        isDown = false;
        performAction();
        points.add(point);
    }

    private void draw(DataPoint point) {
        points.add(point);
        if (onSignatureChangedListener != null)
            onSignatureChangedListener.execute();
    }

    private void performAction() {
        if (currentButton == ButtonsPanel.END)
            OnEndPressed();
        else if (currentButton == ButtonsPanel.CLEAR)
            clear();
    }

    private void OnEndPressed() {
        if (isWritten() && onSignatureFinishedListener != null)
            onSignatureFinishedListener.execute();
        else
            clear();
    }

    @Override
    public void clear() {
        points.clear();
        loadImage();
        if (onSignatureChangedListener != null)
            onSignatureChangedListener.execute();
    }

    private boolean isDrawing(DataPoint point) {
        return ("".equals(tabletImage.getButton(point.getX(), point.getY()))) && (point.isTouchingScreen());
    }

    @Override
    public boolean isConnected() {
        return tablet != null;
    }

    @Override
    public boolean isWritten() {
        return getPoints().size() > 1;
    }
}
