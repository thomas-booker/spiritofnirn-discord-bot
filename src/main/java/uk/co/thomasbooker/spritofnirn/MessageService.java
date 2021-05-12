package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Autowired
    SpiritOfNirnBot spiritOfNirnBot;

    public void sendMessage(DiscordModel discordModel, String message) {
        discordModel.getMessageChannel().sendMessage(message).queue();
    }

    public void sendMessage(Long channelID, String message) {
        spiritOfNirnBot.jda.getTextChannelById(channelID).sendMessage(message).queue();
    }
}
