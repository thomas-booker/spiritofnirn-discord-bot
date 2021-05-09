import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class AuthService {

    private static final String OWNER = "owner";
    private static final String ADMIN = "admin";

    public boolean isOwner(String user) throws FileNotFoundException {
        return getAuthJson(OWNER).contains(user);
    }

    public boolean isAdmin(String user) throws FileNotFoundException {
        return getAuthJson(ADMIN).contains(user);
    }

    private String getAuthJson(String memberType) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(getAuthFile(), JsonObject.class).getAsJsonArray(memberType).toString();
    }

    private BufferedReader getAuthFile() throws FileNotFoundException {
        return ResourceLoader.getAuthFile();
    }
}
