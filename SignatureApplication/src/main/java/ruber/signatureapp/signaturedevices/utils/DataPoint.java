package ruber.signatureapp.signaturedevices.utils;

public class DataPoint {

    private final double x;
    private final double y;
    private final int pressure;
    private final boolean isTouchingScreen;
    private final int maxPressure;

    public DataPoint(double x, double y, int pressure, int maxPressure, boolean isTouchingScreen) {
        this.x = x;
        this.y = y;
        this.pressure = pressure;
        this.maxPressure = maxPressure;
        this.isTouchingScreen = isTouchingScreen;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getPressure() {
        return (int) Math.round(Math.sqrt((float) pressure / (((float) maxPressure) / 4)));
    }

    public boolean isTouchingScreen() {
        return isTouchingScreen;
    }
}
