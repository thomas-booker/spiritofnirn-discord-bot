import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageService {

    public void sendMessage(MessageReceivedEvent event, String message) {
        event.getChannel().sendMessage(message).queue();
    }
}
