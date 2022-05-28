package uk.co.thomasbooker.spritofnirn.trial.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers.TrialCommandHandler;

import java.util.List;

@Component
public class TrialCommandListener extends ListenerAdapter {

    @Autowired
    private List<TrialCommandHandler> trialCommandHandlers;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        trialCommandHandlers.forEach(trialCommandHandler -> trialCommandHandler.handleCommandIfTriggered(event));
    }

}