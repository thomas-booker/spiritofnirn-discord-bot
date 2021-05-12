package uk.co.thomasbooker.spritofnirn;

import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JsonFileService {
    private static final String OWNER = "owner";
    private static final String ADMIN = "admin";

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Auth file
    public boolean isOwner(String user) throws FileNotFoundException {
        return getAuthJsonMembers(OWNER).contains(user);
    }

    public boolean isAdmin(String user) throws FileNotFoundException {
        return getAuthJsonMembers(ADMIN).contains(user);
    }

    public void addAdminToFile(String user) throws IOException {
        JsonArray admins = getAuthJson("admin");
        admins.add(user);

        JsonObject jsonObject = gson.fromJson(ResourceLoader.getAuthFile(), JsonObject.class);
        jsonObject.add("admin", admins);

        writeJsonObjectToAuthFile(jsonObject);
    }

    public void removeAdminFromFile(String user) throws IOException {
        JsonElement element = new JsonPrimitive(user);

        JsonArray admins = getAuthJson("admin");
        admins.remove(element);

        JsonObject jsonObject = gson.fromJson(ResourceLoader.getAuthFile(), JsonObject.class);
        jsonObject.add("admin", admins);

        writeJsonObjectToAuthFile(jsonObject);
    }

    private void writeJsonObjectToAuthFile(JsonObject jsonObject) throws IOException {
        writeJsonObjectToFile(jsonObject, URIs.AUTH);
    }

    private String getAuthJsonMembers(String memberType) throws FileNotFoundException {
        return getAuthJson(memberType).toString();
    }

    private JsonArray getAuthJson(String memberType) throws FileNotFoundException {
        return gson.fromJson(ResourceLoader.getAuthFile(), JsonObject.class).getAsJsonArray(memberType);
    }

    // Treasure Hunt file
    public String getFirstClueWithPermissionsCheck(DiscordModel discordModel) throws FileNotFoundException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();
        boolean permissions = fullTreasureHuntFile.getAsJsonObject("firstClue").get("permission").toString().contains("admins");
        String author = discordModel.getAuthor();

        if (isOwner(author) || isAdmin(author) || !permissions) {
            return getClue("first");
        } else {
            return "The first Treasure Hunt clue is currently only accessible to admins";
        }
    }

    public ArrayList<String> getAllClues() throws FileNotFoundException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();

        String firstClue = fullTreasureHuntFile.getAsJsonObject("firstClue").get("message").toString();
        String secondClue = fullTreasureHuntFile.getAsJsonPrimitive("secondClue").toString();
        String thirdClue = fullTreasureHuntFile.getAsJsonPrimitive("thirdClue").toString();

        ArrayList<String> allClues = new ArrayList<>();
        allClues.add(firstClue);
        allClues.add(secondClue);
        allClues.add(thirdClue);

        return allClues;
    }

    public void resetAllClues() throws IOException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();
        JsonObject firstClueElement = fullTreasureHuntFile.getAsJsonObject("firstClue");

        firstClueElement.addProperty("message", "");
        firstClueElement.addProperty("permission", "admins");

        fullTreasureHuntFile.add("firstClue", firstClueElement);
        fullTreasureHuntFile.addProperty("secondClue", "");
        fullTreasureHuntFile.addProperty("thirdClue", "");

        writeJsonObjectToTreasureHuntFile(fullTreasureHuntFile);
    }

    public String getClue(String clue) throws FileNotFoundException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();
        if (clue.contains("first")) {
            return fullTreasureHuntFile.getAsJsonObject("firstClue").get("message").toString();
        } else if (clue.contains("second")) {
            return fullTreasureHuntFile.get("secondClue").toString();
        } else if (clue.contains("third")) {
            return fullTreasureHuntFile.get("thirdClue").toString();
        } else {
            return "Clue not found for: " + clue;
        }
    }

    public void addFirstClue(DiscordModel discordModel) throws IOException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();
        JsonObject firstClueElement = fullTreasureHuntFile.getAsJsonObject("firstClue");

        String[] message = discordModel.getRawContent().split("!th-addfirstclue");

        firstClueElement.addProperty("message", message[1]);
        firstClueElement.addProperty("permissions", "everyone");

        fullTreasureHuntFile.add("firstClue", firstClueElement);

        writeJsonObjectToTreasureHuntFile(fullTreasureHuntFile);
    }

    public void addSecondClue(DiscordModel discordModel) throws IOException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();

        String[] message = discordModel.getRawContent().split("!th-addsecondclue");

        fullTreasureHuntFile.addProperty("secondClue", message[1]);

        writeJsonObjectToTreasureHuntFile(fullTreasureHuntFile);
    }

    public void addThirdClue(DiscordModel discordModel) throws IOException {
        JsonObject fullTreasureHuntFile = getTreasureHuntFile();

        String[] message = discordModel.getRawContent().split("!th-addthirdclue");

        fullTreasureHuntFile.addProperty("thirdClue", message[1]);

        writeJsonObjectToTreasureHuntFile(fullTreasureHuntFile);
    }

    private JsonObject getTreasureHuntFile() throws FileNotFoundException {
        return gson.fromJson(ResourceLoader.getTreasureHuntFile(), JsonObject.class);
    }

    public void writeJsonObjectToTreasureHuntFile(JsonObject jsonObject) throws IOException {
        writeJsonObjectToFile(jsonObject, URIs.TREASURE_HUNT);
    }

    // Generic
    private void writeJsonObjectToFile(JsonObject jsonObject, String URI) throws IOException {
        FileWriter fileWriter = new FileWriter(URI);
        gson.toJson(jsonObject, fileWriter);
        fileWriter.close();
    }
}
