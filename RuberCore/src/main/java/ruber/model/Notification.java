package ruber.model;

public class Notification {

    private final String type;
    private final byte[] description;

    public Notification(String type, byte[] description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public byte[] getDescription() {
        return description;
    }
}
