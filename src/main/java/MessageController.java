import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MessageController extends ListenerAdapter {

    AuthService authService = new AuthService();

    MessageService messageService = new MessageService();

    TreasureHunt treasureHunt;

        @Override
        public void onMessageReceived(@NotNull MessageReceivedEvent event) {
            DiscordModel discordModel = new DiscordModel(event.getMessage());
            this.treasureHunt = new TreasureHunt(discordModel);
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
                addAdmin(discordModel);
            }

            if (getCommand(event).contains("!removeadmin")) {
                removeAdmin(discordModel);
            }

            // Admin commands
            if (getCommand(event).equals("!admin")) {
                adminTest(event, discordModel);
            }

            // Treasure Hunt commands
            // TH Admin commands
            if (getCommand(event).contains("!th")) {
                if (authService.isAdmin(discordModel.getAuthor()) || authService.isOwner(discordModel.getAuthor())) {
                    if (getCommand(event).contains("addfirstclue")) {
                        treasureHunt.addFirstClue(discordModel);
                    }

                    if (getCommand(event).contains("removefirstclue")) {
                        treasureHunt.removeFirstClue(discordModel);
                    }

                    if (getCommand(event).contains("addsecondclue")) {
                        treasureHunt.addSecondClue(discordModel);
                    }

                    if (getCommand(event).contains("removesecondclue")) {
                        treasureHunt.removeSecondClue(discordModel);
                    }

                    if (getCommand(event).contains("addthirdclue")) {
                        treasureHunt.addThirdClue(discordModel);
                    }

                    if (getCommand(event).contains("removethirdclue")) {
                        treasureHunt.removeThirdClue(discordModel);
                    }

                    if (getCommand(event).contains("reset")) {
                        treasureHunt.reset(discordModel);
                    }
                } else {
                    messageService.sendMessage(discordModel, discordModel.getAuthor()
                            + " , you are not authorised to amend the Treasure Hunt");
                }
            }

            // TH Everyone
            if (getCommand(event).equals("!treasurehunt")) {

            }
        }

    private void addAdmin(DiscordModel discordModel) throws IOException {
            String author = discordModel.getAuthor();
            if (authService.isOwner(author)) {
                authService.addAdmin(discordModel);
            } else {
                messageService.sendMessage(discordModel, author + " , you are not authorised to add admins!");
            }
    }

    private void removeAdmin(DiscordModel discordModel) throws IOException {
            String author = discordModel.getAuthor();
            if (authService.isOwner(author)) {
                authService.removeAdmin(discordModel);
            } else {
                messageService.sendMessage(discordModel, author + " , you are not authorised to remove admins!");
            }
    }



    private void adminTest(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
        if (authService.isAdmin(discordModel.getAuthor())) {
            messageService.sendMessage(discordModel,"Success!");
        } else {
            messageService.sendMessage(discordModel,"Rekt");
        }
    }

    private void ownerTest(MessageReceivedEvent event, DiscordModel discordModel) throws FileNotFoundException {
            String author = discordModel.getAuthor();
        if (authService.isOwner(author)) {
            messageService.sendMessage(discordModel, "Owner test successful, " + author + "!");
        } else {
            messageService.sendMessage(discordModel,"You are not the owner, " + author + "!");
        }
    }

    private String getCommand(MessageReceivedEvent event) {
            return event.getMessage().getContentRaw();
    }
}
