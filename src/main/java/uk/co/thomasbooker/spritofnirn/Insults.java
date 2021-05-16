package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class Insults {

    @Autowired
    JsonFileService jsonFileService;

    @Autowired
    Interactions interaction;

    public void insultHandler(DiscordModel discordModel) throws FileNotFoundException {
        interaction.sendInteraction(discordModel, jsonFileService.getRandomInsult().replaceAll("\"", ""));
    }
}
