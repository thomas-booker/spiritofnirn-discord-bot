package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uk.co.thomasbooker.spritofnirn.trial.discord.TrialCommandDetails;

public abstract class TrialCommandHandler {

    public void handleCommandIfTriggered(MessageReceivedEvent event) {
        if (commandNotTriggered(event)) {
            return;
        }

        TrialCommandDetails trialCommandDetails = new TrialCommandDetails(event);
        if (trialCommandDetails.hasUnexpectedNumberOfArguments(getNumberOfExpectedArguments())) {
            event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage("Expected " + getNumberOfExpectedArguments() + " arguments").queue();
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
