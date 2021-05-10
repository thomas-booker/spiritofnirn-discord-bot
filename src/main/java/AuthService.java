import com.google.gson.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AuthService {
    private static final String OWNER = "owner";
    private static final String ADMIN = "admin";

    MessageService messageService = new MessageService();

    Gson gson = new Gson();

    public boolean isOwner(String user) throws FileNotFoundException {
        return getAuthJsonMembers(OWNER).contains(user);
    }

    public boolean isAdmin(String user) throws FileNotFoundException {
        return getAuthJsonMembers(ADMIN).contains(user);
    }

    public void addAdmin(MessageReceivedEvent event) throws IOException {
        String user = getFirstMentionedUser(event);
        if (!isAdmin(user)) {
            JsonArray admins = getAuthJson("admin");
            admins.add(user);

            JsonObject jsonObject = gson.fromJson(getAuthFile(), JsonObject.class);
            jsonObject.add("admin", admins);

            writeJsonObjectToAuthFile(jsonObject);

            messageService.sendMessage(event, user + " added as admin!");
        } else {
            messageService.sendMessage(event,user + " is already an admin!");
        }
    }

    public void removeAdmin(MessageReceivedEvent event) throws IOException {
        String user = getFirstMentionedUser(event);
        if (isAdmin(user)) {
            JsonElement element = new JsonPrimitive(user);

            JsonArray admins = getAuthJson("admin");
            admins.remove(element);

            JsonObject jsonObject = gson.fromJson(getAuthFile(), JsonObject.class);
            jsonObject.add("admin", admins);

            writeJsonObjectToAuthFile(jsonObject);

            messageService.sendMessage(event, user + " removed as admin!");
        } else {
            messageService.sendMessage(event, user + " isn't an admin!");
        }
    }

    private void writeJsonObjectToAuthFile(JsonObject jsonObject) throws IOException {
        FileWriter fileWriter = new FileWriter(URIs.AUTH);
        gson.toJson(jsonObject, fileWriter);
        fileWriter.close();
    }

    private String getAuthJsonMembers(String memberType) throws FileNotFoundException {
        return getAuthJson(memberType).toString();
    }

    private JsonArray getAuthJson(String memberType) throws FileNotFoundException {
        return gson.fromJson(getAuthFile(), JsonObject.class).getAsJsonArray(memberType);
    }

    private BufferedReader getAuthFile() throws FileNotFoundException {
        return ResourceLoader.getAuthFile();
    }

    private String getFirstMentionedUser(MessageReceivedEvent event) {
        return event.getMessage().getMentionedUsers().get(0).getName();
    }
}
