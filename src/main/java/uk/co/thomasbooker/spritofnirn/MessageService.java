package uk.co.thomasbooker.spritofnirn;

public class MessageService {

    public void sendMessage(DiscordModel discordModel, String message) {
        discordModel.getMessageChannel().sendMessage(message).queue();
    }
}
