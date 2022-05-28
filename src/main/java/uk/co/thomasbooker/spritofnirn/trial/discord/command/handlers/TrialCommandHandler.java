package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.thomasbooker.spritofnirn.discord.DiscordService;
import uk.co.thomasbooker.spritofnirn.trial.discord.TrialCommandDetails;

public abstract class TrialCommandHandler {

    @Autowired
    DiscordService discord;

    public void handleCommandIfTriggered(MessageReceivedEvent event) {
        if (commandNotTriggered(event)) {
            return;
        }

        TrialCommandDetails trialCommandDetails = new TrialCommandDetails(event);
        if (trialCommandDetails.hasUnexpectedNumberOfArguments(getNumberOfExpectedArguments())) {
            discord.sendMessage(event.getChannel(), "Expected " + getNumberOfExpectedArguments() + " arguments");
            return;
        }

        handleCommand(trialCommandDetails);
    }

    private boolean commandNotTriggered(MessageReceivedEvent event) {
        return !event.getMessage().getContentRaw().startsWith("!" + getCommandName());
    }

    abstract String getCommandName();

    abstract int getNumberOfExpectedArguments();

    abstract void handleCommand(TrialCommandDetails trialCommandDetails) ;

}
