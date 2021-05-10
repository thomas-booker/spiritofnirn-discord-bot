import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MessageController extends ListenerAdapter {

    AuthService authService = new AuthService();

    MessageService messageService = new MessageService();

        @Override
        public void onMessageReceived(@NotNull MessageReceivedEvent event) {
            DiscordModel discordModel = new DiscordModel(event.getMessage());
            try {
                handleMessage(event, discordModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleMessage(MessageReceivedEvent event, DiscordModel discordModel) throws IOException {
            // Owner commands
            if (getCommand(event).equals("!owner")) {
                ownerTest(event, discordModel);
            }

            if (getCommand(event).contains("!addadmin")) {
                addAdmin(event, discordModel);
            }

            if (getCommand(event).contains("!removeadmin")) {
                removeAdmin(event, discordModel);
            }

            // Admin commands
            if (getCommand(event).equals("!admin")) {
                adminTest(event, discordModel);
            }

            // Everyone commands
            if (getCommand(event).equals("!treasurehunt")) {
                treasureHuntTest(event);
            }
        }

    private void addAdmin(MessageReceivedEvent event, DiscordModel discordModel) throws IOException {
            String author = discordModel.getAuthor();
            if (authService.isOwner(author)) {
                authService.addAdmin(event);
            } else {
                messageService.sendMessage(event, author + " , you are not authorised to add admins!");
            }
    }

    private void removeAdmin(MessageReceivedEvent event, DiscordModel discordModel) throws IOException {
            String author = discordModel.getAuthor();
            if (authService.isOwner(author)) {
                authService.removeAdmin(event);
            } else {
                messageService.sendMessage(event, author + " , you are not authorised to remove admins!");
            }
    }

    private void treasureHuntTest(MessageReceivedEvent event) {
        try {
            TreasureHunt treasureHunt = new TreasureHunt();
            messageService.sendMessage(event, treasureHunt.firstClue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adminTest(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
        if (authService.isAdmin(discordModel.getAuthor())) {
            messageService.sendMessage(event,"Success!");
        } else {
            messageService.sendMessage(event,"Rekt");
        }
    }

    private void ownerTest(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
            String author = discordModel.getAuthor();
        if (authService.isOwner(author)) {
            messageService.sendMessage(event, "Owner test successful, " + author + "!");
        } else {
            messageService.sendMessage(event,"You are not the owner, " + author + "!");
        }
    }

    private String getCommand(MessageReceivedEvent event) {
            return event.getMessage().getContentRaw();
    }
}
