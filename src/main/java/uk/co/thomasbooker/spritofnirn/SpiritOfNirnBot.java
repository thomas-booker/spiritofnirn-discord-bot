package uk.co.thomasbooker.spritofnirn;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.io.IOException;

@Component
public class SpiritOfNirnBot extends ListenerAdapter {

    @Autowired
    MessageListener messageListener;

    JDA jda;

    @PostConstruct
    private void startBot() throws IOException, LoginException {
        this.jda = JDABuilder.createDefault(ResourceLoader.getDiscordToken())
                .addEventListeners(messageListener)
                .build();
    }
}
