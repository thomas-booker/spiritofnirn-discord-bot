package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.TrialDateTimeFormatter;
import uk.co.thomasbooker.spritofnirn.trial.discord.TrialCommandDetails;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
        return 3;
    }

    @Override
    void handleCommand(TrialCommandDetails trialCommandDetails) {
        LocalDateTime trialDateAndTime;
        try {
            trialDateAndTime = TrialDateTimeFormatter.parseTimeStringAndDateString(trialCommandDetails.getCommandArgument(1), trialCommandDetails.getCommandArgument(2));
        } catch (DateTimeParseException dateTimeParseException){
            trialCommandDetails.getEvent().getJDA().getTextChannelById(trialCommandDetails.getEvent().getChannel().getId())
                    .sendMessage("Could not read date" + trialCommandDetails.getCommandArgument(1) + " " + trialCommandDetails.getCommandArgument(2)).queue();
            return;
        }

        Trial trialToSetup = new Trial()
                .withName(trialCommandDetails.getCommandArgument(0))
                .withStartDateTime(trialDateAndTime)
                .withOwner(trialCommandDetails.getEvent().getAuthor().getName());
        trialRepository.save(trialToSetup);

        trialCommandDetails.getEvent().getJDA().getTextChannelById(trialCommandDetails.getEvent().getChannel().getId())
                .sendMessage(trialToSetup.getName() + " is set to start at "
                        + TrialDateTimeFormatter.parseLocalTime(trialToSetup.getStartDateTime()) + " on the "
                        + TrialDateTimeFormatter.parseLocalDate(trialToSetup.getStartDateTime())).queue();
    }

}
