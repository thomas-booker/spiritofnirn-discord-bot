package uk.co.thomasbooker.spritofnirn;

import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Controller {

    public static void main(String[] args) throws IOException, LoginException {
        startBot();
    }
    
    private static void startBot() throws IOException, LoginException {
        JDABuilder.createDefault(ResourceLoader.getDiscordToken())
                .addEventListeners(new MessageController())
                .build();
    }
}
