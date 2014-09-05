package ruber.signatureapp.signaturedevices.wacomstu500;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

class ButtonsPanel extends BufferedImage {

    public static final String END = "Terminar";
    public static final String CLEAR = "Borrar";
    private static final int NUMBER_OF_BUTTONS = 2;
    
    private Rectangle endButton;
    private Rectangle clearButton;
    
    public ButtonsPanel(int width,int height) {
        super(width, height, TYPE_INT_RGB);
        generateImage();
    }

    private void generateImage() {
        Graphics2D g2d = createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        generateModel(g2d);
    }

    private void generateModel(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        endButton = new Rectangle(2, 0, getWidth() / NUMBER_OF_BUTTONS, getHeight());
        clearButton = new Rectangle((getWidth() / NUMBER_OF_BUTTONS) + 2, 0, getWidth() / NUMBER_OF_BUTTONS, getHeight());
        g2d.drawRect(endButton.x, endButton.y, endButton.width, endButton.height);
        g2d.setFont(new Font("Arial", Font.PLAIN, 50));
        g2d.drawString(END, endButton.x + (endButton.width / 2) - (getTextDimension(END, g2d, new Font("Arial", Font.PLAIN, 50)).width / 2), endButton.y + (endButton.height / 2) + (getTextDimension(END, g2d, new Font("Arial", Font.PLAIN, 50)).height / 2));
        g2d.drawRect(clearButton.x, clearButton.y, clearButton.width, clearButton.height);
        g2d.drawString(CLEAR, clearButton.x + (clearButton.width / 2) - (getTextDimension(CLEAR, g2d, new Font("Arial", Font.PLAIN, 50)).width / 2), clearButton.y + (clearButton.height / 2) + (getTextDimension(CLEAR, g2d, new Font("Arial", Font.PLAIN, 50)).height / 2));
    }
    
    private Dimension getTextDimension(String text, Graphics2D g2d, Font font) {
        FontRenderContext fr = g2d.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(fr, text);
        double maxDescent = 0, maxHeight = 0;
        for (int i = 0; i < text.length(); i++) {
            Rectangle2D bounds = gv.getGlyphMetrics(i).getBounds2D();
            maxHeight = Math.max(bounds.getHeight(), maxHeight);
            maxDescent = Math.max(bounds.getY() + bounds.getHeight(), maxDescent);
        }
        return new Dimension(g2d.getFontMetrics().stringWidth(text), (int) (maxHeight - maxDescent));
    }

    public String getButton(double x) {
        return (x < (getWidth()/ 2)) ? END : CLEAR;
    }
    
}
