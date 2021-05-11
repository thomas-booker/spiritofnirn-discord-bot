import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageService {

    public void sendMessage(DiscordModel discordModel, String message) {
        discordModel.getMessageChannel().sendMessage(message).queue();
    }
}
