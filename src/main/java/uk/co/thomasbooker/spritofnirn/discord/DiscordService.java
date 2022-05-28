package uk.co.thomasbooker.spritofnirn.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscordService {

    @Autowired
    private JDA jda;

    public void sendMessage(MessageChannel channel, String message) {
        jda.getTextChannelById(channel.getId()).sendMessage(message).queue();
    }

}
