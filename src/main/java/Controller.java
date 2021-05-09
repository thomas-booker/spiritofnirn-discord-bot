import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Controller extends ListenerAdapter {

    public static void main(String[] args) throws IOException, LoginException {
        JDABuilder.createDefault(ResourceLoader.getDiscordToken())
                .addEventListeners(new MessageController())
                .build();
    }

}
