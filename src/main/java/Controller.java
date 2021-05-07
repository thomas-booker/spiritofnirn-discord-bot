import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Controller extends ListenerAdapter {

    public static void main(String[] args) throws IOException, LoginException {
        JDABuilder.createDefault(PropertiesLoader.getDiscordToken())
                .addEventListeners(new Controller())
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
