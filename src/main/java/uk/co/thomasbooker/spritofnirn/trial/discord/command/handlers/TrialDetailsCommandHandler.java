package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.discord.TrialCommandDetails;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;
import uk.co.thomasbooker.spritofnirn.trial.model.TrialMember;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialMemberRepository;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialRepository;

import java.util.List;

@Component
public class TrialDetailsCommandHandler extends TrialCommandHandler {

    @Autowired
    private TrialRepository trialRepository;

    @Autowired
    private TrialMemberRepository trialMemberRepository;

    @Override
    String getCommandName() {
        return "trialDetails";
    }

    @Override
    int getNumberOfExpectedArguments() {
        return 1;
    }

    @Override
    void handleCommand(TrialCommandDetails trialCommandDetails) {
        Trial trial = trialRepository.findByName(trialCommandDetails.getCommandArgument(0));
        if (trial == null) {
            trialCommandDetails.getEvent().getJDA().getTextChannelById(trialCommandDetails.getEvent().getChannel().getId())
                    .sendMessage("Could not find trial " + trialCommandDetails.getCommandArgument(0)).queue();
            return;
        }

        discord.sendMessage(trialCommandDetails.getEvent().getChannel(), trial.toString());

        List<TrialMember> trialMembers = trialMemberRepository.findByTrialId(trial.getId());
        trialMembers.forEach(trialMember -> discord.sendMessage(trialCommandDetails.getEvent().getChannel(), trialMember.toString()));
    }

}
