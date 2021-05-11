import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TreasureHunt {

    MessageService messageService = new MessageService();

    DiscordModel discordModel;

    public TreasureHunt(DiscordModel discordModel) {
        this.discordModel = discordModel;
    }


    public void addFirstClue(DiscordModel discordModel) {
        messageService.sendMessage(discordModel, discordModel.getAuthor() + " , first clue added!");
    }

    public void removeFirstClue(DiscordModel discordModel) {

    }

    public void addSecondClue(DiscordModel discordModel) {

    }

    public void removeSecondClue(DiscordModel discordModel) {

    }

    public void addThirdClue(DiscordModel discordModel) {

    }

    public void removeThirdClue(DiscordModel discordModel) {

    }

    public void reset(DiscordModel discordModel) {

    }

    public String firstClue() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(URIs.TREASURE_HUNT));
        Gson gson = new Gson();
        HashMap json = gson.fromJson(bufferedReader, HashMap.class);

        return json.get("mainClue").toString();
    }
}
