package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Interactions {

    @Autowired
    MessageService messageService;

    public void sendInteraction(DiscordModel discordModel, String message) {
        if (!hasTarget(discordModel)) {
            messageService.sendMessage(discordModel, getTarget(discordModel) + ", " + message);
        } else {
            messageService.sendMessage(discordModel, message);
        }
    }

    protected boolean hasTarget(DiscordModel discordModel) {
        return discordModel.getMentionedUsers().isEmpty();
    }

    protected String getTarget(DiscordModel discordModel) {
        return discordModel.getMentionedUsers().get(0).getName();
    }
}
