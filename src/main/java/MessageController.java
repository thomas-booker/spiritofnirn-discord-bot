import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MessageController extends ListenerAdapter {

    AuthService authService = new AuthService();

        @Override
        public void onMessageReceived(@NotNull MessageReceivedEvent event) {
            DiscordModel discordModel = new DiscordModel(event.getMessage());
            try {
                handleMessage(event, discordModel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void handleMessage(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
            if (event.getMessage().getContentRaw().equals("!ding")) {
                ownerTest(event, discordModel.getAuthor());
            }

            if (event.getMessage().getContentRaw().equals("!admin")) {
                adminTest(event, discordModel);
            }

            if (event.getMessage().getContentRaw().equals("!treasurehunt")) {
                treasureHuntTest(event);
            }
        }

    private void treasureHuntTest(MessageReceivedEvent event) {
        try {
            TreasureHunt treasureHunt = new TreasureHunt();
            event.getChannel().sendMessage(treasureHunt.firstClue()).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adminTest(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
        if (authService.isAdmin(discordModel.getAuthor())) {
            event.getChannel().sendMessage("Success!").queue();
        } else {
            event.getChannel().sendMessage("Rekt").queue();
        }
    }

    private void ownerTest(MessageReceivedEvent event, String author) throws FileNotFoundException {
        if (authService.isOwner(author)) {
            event.getChannel().sendMessage("Lovelocke is my master, and he has a big Dong!").queue();
        } else {
            event.getChannel().sendMessage("Get fucked " + author + ", you nobhead").queue();
        }
    }
}
