package uk.co.thomasbooker.spritofnirn;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class MessageController {

    @Autowired
    AuthService authService;

    @Autowired
    MessageService messageService;

    @Autowired
    TreasureHunt treasureHunt;

    @Autowired
    DiscordModel discordModel;

        public void handleMessage(MessageReceivedEvent event) throws IOException {
            discordModel.setupDiscordModel(event.getMessage());
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
                    if (getCommand(event).contains("!th-getallclues")) {
                        treasureHunt.getAllClues(discordModel);
                    }

                    if (getCommand(event).contains("!th-getfirstclue")) {
                        treasureHunt.getClue("First");
                    }

                    if (getCommand(event).contains("!th-addfirstclue")) {
                        treasureHunt.addFirstClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-removefirstclue")) {
                        treasureHunt.removeFirstClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-getsecondclue")) {
                        treasureHunt.getClue("Second");
                    }

                    if (getCommand(event).contains("!th-addsecondclue")) {
                        treasureHunt.addSecondClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-removesecondclue")) {
                        treasureHunt.removeSecondClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-getthirdclue")) {
                        treasureHunt.getClue("Third");
                    }

                    if (getCommand(event).contains("!th-addthirdclue")) {
                        treasureHunt.addThirdClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-removethirdclue")) {
                        treasureHunt.removeThirdClue(discordModel);
                    }

                    if (getCommand(event).contains("!th-reset")) {
                        treasureHunt.reset();
                    }
                } else {
                    messageService.sendMessage(discordModel, discordModel.getAuthor()
                            + " , you are not authorised to amend the Treasure Hunt");
                }
            }

            // TH Everyone
            if (getCommand(event).equals("!treasurehunt")) {
                treasureHunt.getFirstClueWithPermissionsCheck(discordModel);
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
