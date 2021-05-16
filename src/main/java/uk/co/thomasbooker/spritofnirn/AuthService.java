package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class AuthService {

    @Autowired
    MessageService messageService;

    @Autowired
    JsonFileService jsonFileService;

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
