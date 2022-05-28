package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.discord.TrialCommandDetails;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;
import uk.co.thomasbooker.spritofnirn.trial.model.TrialMember;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialMemberRepository;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialRepository;

@Component
public class JoinTrialCommandHandler extends TrialCommandHandler {

    @Autowired
    private TrialRepository trialRepository;

    @Autowired
    private TrialMemberRepository trialMemberRepository;

    @Override
    String getCommandName() {
        return "joinTrial";
    }

    @Override
    int getNumberOfExpectedArguments() {
        return 2;
    }

    @Override
    void handleCommand(TrialCommandDetails trialCommandDetails) {
        String trialName = trialCommandDetails.getCommandArgument(0);
        Trial trialToJoin = trialRepository.findByName(trialName);
        if (trialToJoin == null) {
            discord.sendMessage(trialCommandDetails.getEvent().getChannel(), "Could not find trial " + trialName);
            return;
        }

        TrialMember joiningTrialMember = new TrialMember()
                .withName(trialCommandDetails.getEvent().getAuthor().getName())
                .withRole(trialCommandDetails.getCommandArgument(1))
                .withTrailId(trialToJoin.getId());
        trialMemberRepository.save(joiningTrialMember);

        discord.sendMessage(trialCommandDetails.getEvent().getChannel(),
                joiningTrialMember.getName() + " has joined the " + trialToJoin.getName() + " trial as a " + joiningTrialMember.getRole());
    }

}