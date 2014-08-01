package ruber.persistence_db.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DatabaseProvider {

    private static Element element;

    public static Database getDatabase(String pathToConfigFile) {
        element = configData(pathToConfigFile);
        Configuration configuration = loadConfiguration();
        return new Database(configuration.database, configuration.username, configuration.password);
    }

    private static Configuration loadConfiguration() {
        Configuration configuration = new Configuration();
        configuration.database = getStringFromElement("host") + ":" + getStringFromElement("port") + "/" + getStringFromElement("database-name");
        configuration.username = getStringFromElement("username");
        configuration.password = getStringFromElement("password");
        return configuration;
    }

    private static Element configData(String pathToConfigFile) {
        try {
            return (Element) configFile(pathToConfigFile).getElementsByTagName("database-configuration").item(0);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("Exception while loading database configuration");
        }
    }

    private static Document configFile(String pathToConfigFile) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(pathToConfigFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    private static String getStringFromElement(String string) {
        return element.getElementsByTagName(string).item(0).getTextContent();
    }

    private static class Configuration {

        private String database;
        private String username;
        private String password;
    }
}
