package uk.co.thomasbooker.spritofnirn;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class TreasureHunt {

    @Autowired
    MessageService messageService;

    DiscordModel discordModel;

    @Autowired
    JsonFileService jsonFileService;

    public void getFirstClueWithPermissionsCheck(DiscordModel discordModel) throws FileNotFoundException {
        messageService.sendMessage(discordModel, jsonFileService.getFirstClueWithPermissionsCheck(discordModel));
    }

    public void getClue(String clue) throws FileNotFoundException {
        messageService.sendMessage(discordModel,clue + " clue: " + jsonFileService.getClue(clue.toLowerCase()));
    }

    public void getAllClues(DiscordModel discordModel) throws FileNotFoundException {
        ArrayList<String> allClues = jsonFileService.getAllClues();

        messageService.sendMessage(discordModel, "First clue: " + allClues.get(0));
        messageService.sendMessage(discordModel, "Second clue: " + allClues.get(1));
        messageService.sendMessage(discordModel, "Third clue: " + allClues.get(2));
    }

    public void addFirstClue(DiscordModel discordModel) throws IOException {
        jsonFileService.addFirstClue(discordModel);
        messageService.sendMessage(discordModel, discordModel.getAuthor() + " , first clue added!");
    }

    public void removeFirstClue() throws IOException {
        jsonFileService.removeFirstClue();
    }

    public void addSecondClue(DiscordModel discordModel) throws IOException {
        jsonFileService.addSecondClue(discordModel);
        messageService.sendMessage(discordModel, discordModel.getAuthor() + " , second clue added!");
    }

    public void removeSecondClue() throws IOException {
        jsonFileService.removeSecondClue();
    }

    public void addThirdClue(DiscordModel discordModel) throws IOException {
        jsonFileService.addThirdClue(discordModel);
        messageService.sendMessage(discordModel, discordModel.getAuthor() + " , third clue added!");
    }

    public void removeThirdClue() throws IOException {
        jsonFileService.removeThirdClue();
    }

    public void reset() throws IOException {
        jsonFileService.resetAllClues();
        messageService.sendMessage(discordModel, discordModel.getAuthor() + " , all clues reset!");
    }

    public String firstClue() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(URIs.TREASURE_HUNT));
        Gson gson = new Gson();
        HashMap json = gson.fromJson(bufferedReader, HashMap.class);

        return json.get("mainClue").toString();
    }
}
