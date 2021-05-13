package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class Insults extends Interactions {

    @Autowired
    JsonFileService jsonFileService;

    public void insultHandler(DiscordModel discordModel) throws FileNotFoundException {
        sendInteraction(discordModel, jsonFileService.getRandomInsult().replaceAll("\"", ""));
    }
}
