package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TrialCommandHandler {

    public void handleCommandIfTriggered(MessageReceivedEvent event) {
        if (commandNotTriggered(event)) {
            return;
        }

        if (hasUnexpectedNumberOfArguments(event)) {
            event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage("Expected " + getNumberOfExpectedArguments() + " arguments").queue();
            return;
        }

        handleCommand(event, parseCommandArguments(event));
    }

    private boolean commandNotTriggered(MessageReceivedEvent event) {
        return !event.getMessage().getContentRaw().startsWith("!" + getCommandName());
    }

    abstract String getCommandName();

    private boolean hasUnexpectedNumberOfArguments(MessageReceivedEvent event) {
        return getNumberOfExpectedArguments() == parseCommandArguments(event).size();
    }

    abstract int getNumberOfExpectedArguments();

    List<String> parseCommandArguments(MessageReceivedEvent event) {
        return Arrays.asList(event.getMessage().getContentRaw()
                        .replace("!" + getCommandName(), "")
                        .strip()
                        .split(","))
                .stream()
                .map(String::strip)
                .collect(Collectors.toList());
    }

    abstract void handleCommand(MessageReceivedEvent event, List<String> commandArguments);

}
