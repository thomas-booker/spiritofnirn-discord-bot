package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class SetupTrialCommandHandler extends TrialCommandHandler {

    @Autowired
    private TrialRepository trialRepository;

    @Override
    String getCommandName() {
        return "setupTrial";
    }

    @Override
    int getNumberOfExpectedArguments() {
        return 2;
    }

    @Override
    void handleCommand(MessageReceivedEvent event, List<String> commandArguments) {
        LocalDateTime trialDateAndTime;
        try {
            trialDateAndTime = LocalDateTime.parse(commandArguments.get(1), DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
        } catch (DateTimeParseException dateTimeParseException){
            event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage("Could not parse date" + commandArguments.get(1)).queue();
            return;
        }

        Trial trialToSetup = new Trial()
                .withName(commandArguments.get(0))
                .withStartDateTime(trialDateAndTime)
                .withOwner(event.getAuthor().getName());
        trialRepository.save(trialToSetup);

        event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage(trialToSetup.getName() + " is set to start on " + trialToSetup.getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))).queue();

    }

}
