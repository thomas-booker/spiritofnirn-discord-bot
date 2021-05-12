package uk.co.thomasbooker.spritofnirn;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageListener extends ListenerAdapter {

    @Autowired
    MessageController messageController;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            messageController.handleMessage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
