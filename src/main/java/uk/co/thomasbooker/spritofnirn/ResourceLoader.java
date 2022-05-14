package uk.co.thomasbooker.spritofnirn;

import java.io.*;
import java.util.Properties;

public class ResourceLoader {
    public static String getDiscordToken() {
        return System.getenv("DISCORD_TOKEN");
    }

    public static BufferedReader getAuthFile() throws FileNotFoundException {
        return getFile(URIs.AUTH);
    }

    public static BufferedReader getTreasureHuntFile() throws FileNotFoundException {
        return getFile(URIs.TREASURE_HUNT);
    }

    public static BufferedReader getInsultsFile() throws FileNotFoundException {
        return getFile(URIs.INSULTS);
    }

    public static BufferedReader getComplimentsFile() throws FileNotFoundException {
        return getFile(URIs.COMPLIMENTS);
    }

    public static BufferedReader getFile(String URI) throws FileNotFoundException {
        return new BufferedReader(new FileReader(URI));
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
