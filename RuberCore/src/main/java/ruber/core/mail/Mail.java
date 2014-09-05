package ruber.core.mail;

public class Mail {

    private final String recipient;
    private final String subject;
    private final String message;

    public Mail(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
