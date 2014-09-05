package ruber.signatureapp.signaturedevices.wacomstu500;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class SignaturePanel extends BufferedImage{

    public SignaturePanel(int width, int height) {
        super(width, height, TYPE_INT_RGB);
        generateScreenImage();
    }
    
    private void generateScreenImage() {
        Graphics2D g2d = createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
    }
}
