import net.dv8tion.jda.api.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.thomasbooker.spritofnirn.AuthService;
import uk.co.thomasbooker.spritofnirn.DiscordModel;
import uk.co.thomasbooker.spritofnirn.JsonFileService;
import uk.co.thomasbooker.spritofnirn.MessageService;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    JsonFileService jsonFileService;

    @Mock
    DiscordModel discordModel;

    @Mock
    MessageService messageService;

    @Mock
    User userModel;

    @Mock
    AuthService authService;

    @InjectMocks
    AuthService testSubject = new AuthService();

    @Test
    public void shouldCallJsonFileServiceIsOwner() throws FileNotFoundException {
        // Given
        String owner = "Test";
        String notOwner = "Test2";
        when(jsonFileService.isOwner(owner)).thenReturn(true);
        when(jsonFileService.isOwner(notOwner)).thenReturn(false);

        // When
        boolean isOwner = testSubject.isOwner(owner);
        boolean isNotOwner = testSubject.isOwner(notOwner);

        // Then
        assertTrue(isOwner);
        assertFalse(isNotOwner);
    }

    @Test
    public void shouldCallJsonFileServiceIsAdmin() throws FileNotFoundException {
        // Given
        String user = "Test";
        String notAdmin = "Test2";
        when(jsonFileService.isAdmin(user)).thenReturn(true);
        when(jsonFileService.isAdmin(notAdmin)).thenReturn(false);

        // When
        boolean isAdmin = testSubject.isAdmin(user);
        boolean isNotAdmin = testSubject.isAdmin(notAdmin);

        // Then
        assertTrue(isAdmin);
        assertFalse(isNotAdmin);
    }

    /*
    TODO Figure out how to test remaining methods in AuthService
    getMentionedUser calls discordModel.getMentionedUsers().get(0).getName()
    getMentionedUsers() returns a List<User> - User is Abstract, so I haven't
    been able to create a List of them to stub
     */
}
