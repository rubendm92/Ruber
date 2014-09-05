    package ruber.signatureapp.signaturedevices.wacomstu500;

import java.awt.image.BufferedImage;

public class TabletBufferedImage extends BufferedImage {

    private final SignaturePanel signaturePanel;
    private final ButtonsPanel buttonsPanel;
    
    public TabletBufferedImage(int width, int height, int imageType) {
        super(width, height, imageType);
        signaturePanel = new SignaturePanel(getWidth(), getHeight() - 50);
        buttonsPanel = new ButtonsPanel(getWidth(), 50);
        generateScreenImage();
    }
    
    private void generateScreenImage() {
        createGraphics().drawImage(signaturePanel, 0, 0, null);
        createGraphics().drawImage(buttonsPanel, 0, getHeight() - 50, null);
    }
    
    public String getButton(double x, double y) {
        if (y <= getHeight() - 50) return "";
        return buttonsPanel.getButton(x);
    }
}
