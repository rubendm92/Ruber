package ruber.core.mail;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ruber.core.log.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public abstract class MailSender {

    private String username;
    private String password;
    private final Session session;

    public MailSender() {
        readUserAndPassword();
        session = Session.getInstance(properties(), authenticator());
    }

    private void readUserAndPassword() {
        Element element = configData("config/mail.xml");
        username = getStringFromElement("username", element);
        password = getStringFromElement("password", element);
    }

    private Element configData(String pathToConfigFile) {
        try {
            return (Element) configFile(pathToConfigFile).getElementsByTagName("session").item(0);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Log.getInstance().add(ex);
            throw new RuntimeException("Exception while loading mail configuration");
        }
    }

    private Document configFile(String pathToConfigFile) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(pathToConfigFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    private String getStringFromElement(String string, Element element) {
        return element.getElementsByTagName(string).item(0).getTextContent();
    }

    private Properties properties() {
        return new Properties() {{
            put("mail.smtp.auth", "true");
            put("mail.smtp.starttls.enable", "true");
            put("mail.smtp.host", "smtp.gmail.com");
            put("mail.smtp.port", "587");
        }};
    }

    private Authenticator authenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
    }

    protected void send(Mail mail) {
        try {
            Transport.send(message(mail));
        } catch (MessagingException ex) {
            Log.getInstance().add(ex);
        }
    }

    protected Message message(Mail mail) throws MessagingException {
        return new MimeMessage(session) { {
            setFrom(new InternetAddress(username));
            setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
            setSubject(mail.getSubject());
            setText(mail.getMessage());
        }};
    }
}
