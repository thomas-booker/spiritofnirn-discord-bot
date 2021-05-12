package uk.co.thomasbooker.spritofnirn;

import java.io.*;

public class AuthService {
    MessageService messageService = new MessageService();

    JsonFileService jsonFileService = new JsonFileService();

    public boolean isOwner(String user) throws FileNotFoundException {
        return jsonFileService.isOwner(user);
    }

    public boolean isAdmin(String user) throws FileNotFoundException {
        return jsonFileService.isAdmin(user);
    }

    public void addAdmin(DiscordModel discordModel) throws IOException {
        String user = getFirstMentionedUser(discordModel);
        if (!isAdmin(user)) {
            jsonFileService.addAdminToFile(user);

            messageService.sendMessage(discordModel, user + " added as admin!");
        } else {
            messageService.sendMessage(discordModel,user + " is already an admin!");
        }
    }

    public void removeAdmin(DiscordModel discordModel) throws IOException {
        String user = getFirstMentionedUser(discordModel);
        if (isAdmin(user)) {
            jsonFileService.removeAdminFromFile(user);

            messageService.sendMessage(discordModel, user + " removed as admin!");
        } else {
            messageService.sendMessage(discordModel, user + " isn't an admin!");
        }
    }

    private String getFirstMentionedUser(DiscordModel discordModel) {
        return discordModel.getMentionedUsers().get(0).getName();
    }
}
