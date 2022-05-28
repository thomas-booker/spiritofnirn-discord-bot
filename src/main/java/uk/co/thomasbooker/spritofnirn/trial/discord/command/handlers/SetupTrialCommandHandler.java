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
            discord.sendMessage(trialCommandDetails.getEvent().getChannel(),
                    "Could not read date" + trialCommandDetails.getCommandArgument(1) + " " + trialCommandDetails.getCommandArgument(2));
            return;
        }

        Trial trialToSetup = new Trial()
                .withName(trialCommandDetails.getCommandArgument(0))
                .withStartDateTime(trialDateAndTime)
                .withOwner(trialCommandDetails.getEvent().getAuthor().getName());
        trialRepository.save(trialToSetup);

        discord.sendMessage(trialCommandDetails.getEvent().getChannel(),
                trialToSetup.getName() + " is set to start at "
                        + TrialDateTimeFormatter.parseLocalTime(trialToSetup.getStartDateTime()) + " on the "
                        + TrialDateTimeFormatter.parseLocalDate(trialToSetup.getStartDateTime()));
    }

}
