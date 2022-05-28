package uk.co.thomasbooker.spritofnirn.trial.discord.command.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;
import uk.co.thomasbooker.spritofnirn.trial.model.TrialMember;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialMemberRepository;
import uk.co.thomasbooker.spritofnirn.trial.repository.TrialRepository;

import java.util.List;

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
    void handleCommand(MessageReceivedEvent event, List<String> commandArguments) {
        Trial trialToJoin = trialRepository.findByName(commandArguments.get(0));
        if (trialToJoin == null) {
            event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage("Could not find trial " + commandArguments.get(0)).queue();
            return;
        }

        TrialMember joiningTrialMember = new TrialMember()
                .withName(event.getAuthor().getName())
                .withRole(commandArguments.get(1))
                .withTrailId(trialToJoin.getId());
        trialMemberRepository.save(joiningTrialMember);

        event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage(joiningTrialMember.getName() + " has joined the " + joiningTrialMember.getName() + " trial as a " + joiningTrialMember.getRole()).queue();
    }

}