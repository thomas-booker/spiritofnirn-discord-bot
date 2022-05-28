package uk.co.thomasbooker.spritofnirn.trial.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrialCommandDetails {

    private MessageReceivedEvent event;

    private List<String> commandArguments;

    public TrialCommandDetails(MessageReceivedEvent event) {
        this.event = event;
        this.commandArguments = parseCommandArguments(getEvent().getMessage().getContentRaw());
    }

    private List<String> parseCommandArguments(String command) {
        command = command.substring(command.indexOf(" ") + 1); // Remove command name.
        command = command.strip();
        return Arrays
                .stream(command.split(" "))
                .collect(Collectors.toList());
    }

    public MessageReceivedEvent getEvent() {
        return event;
    }

    public List<String> getCommandArguments() {
        return commandArguments;
    }

    public String getCommandArgument(int index) {
        return getCommandArguments().get(index);
    }

    public boolean hasUnexpectedNumberOfArguments(int expectedNumberOfArguments) {
        return expectedNumberOfArguments != getCommandArguments().size();
    }

    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    public void setCommandArguments(List<String> commandArguments) {
        this.commandArguments = commandArguments;
    }

    public TrialCommandDetails withEvent(MessageReceivedEvent event) {
        this.event = event;
        return this;
    }

    public TrialCommandDetails withCommandArguments(List<String> commandArguments) {
        this.commandArguments = commandArguments;
        return this;
    }

}
