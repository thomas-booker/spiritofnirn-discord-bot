import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.thomasbooker.spritofnirn.DiscordModel;
import uk.co.thomasbooker.spritofnirn.Insults;
import uk.co.thomasbooker.spritofnirn.Interactions;
import uk.co.thomasbooker.spritofnirn.JsonFileService;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsultsTest {

    @Mock
    JsonFileService jsonFileService;

    @Mock
    Interactions interactions;

    @InjectMocks
    Insults testSubject = new Insults();

    @Test
    public void shouldSendInsult() throws FileNotFoundException {
        // Given
        when(jsonFileService.getRandomInsult()).thenReturn("Insult");

        // When
        testSubject.insultHandler(new DiscordModel());

        // Then
        verify(interactions, times(1)).sendInteraction(any(), any());
    }
}
