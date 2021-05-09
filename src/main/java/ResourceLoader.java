import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class ResourceLoader {
    public static String getDiscordToken() throws IOException {
        return getResourceFile(URIs.SPIRITOFNIRN_PROPERTIES).getProperty("TOKEN");
    }

    public static BufferedReader getAuthFile() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(URIs.AUTH));

        return bufferedReader;
    }

    private static Properties getResourceFile(String propertiesFileLocation) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
