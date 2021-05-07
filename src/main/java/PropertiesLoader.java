import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    public static String getDiscordToken() throws IOException {
        return getPropertiesFile(URIs.SPIRITOFNIRN_PROPERTIES).getProperty("TOKEN");
    }

    public static Properties getPropertiesFile(String propertiesFileLocation) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
