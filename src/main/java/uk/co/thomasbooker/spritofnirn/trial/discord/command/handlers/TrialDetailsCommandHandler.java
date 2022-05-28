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
    void handleCommand(MessageReceivedEvent event, List<String> commandArguments) {
        Trial trial = trialRepository.findByName(commandArguments.get(0));
        if (trial == null) {
            event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage("Could not find trial " + commandArguments.get(0)).queue();
            return;
        }
        event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage(trial.toString()).queue();

        List<TrialMember> trialMembers = trialMemberRepository.findByTrialId(trial.getId());
        trialMembers.forEach(trialMember ->
                event.getJDA().getTextChannelById(event.getChannel().getId()).sendMessage(trialMember.toString()).queue());
    }
}
