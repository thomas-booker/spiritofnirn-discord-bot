import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TreasureHunt {

    public String firstClue() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(URIs.TREASURE_HUNT));
        Gson gson = new Gson();
        HashMap json = gson.fromJson(bufferedReader, HashMap.class);

        return json.get("mainClue").toString();
    }
}
