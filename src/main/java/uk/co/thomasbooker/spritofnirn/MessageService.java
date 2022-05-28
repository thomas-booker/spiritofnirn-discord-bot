package uk.co.thomasbooker.spritofnirn;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

    public void sendMessage(DiscordModel discordModel, String message) {
        discordModel.getMessageChannel().sendMessage(message).queue();
    }

    public void sendMessage(Long channelID, String message) {
//        jda.getTextChannelById(channelID).sendMessage(message).queue();
    }
}
