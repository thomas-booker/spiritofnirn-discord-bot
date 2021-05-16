import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.thomasbooker.spritofnirn.Compliments;
import uk.co.thomasbooker.spritofnirn.DiscordModel;
import uk.co.thomasbooker.spritofnirn.Interactions;
import uk.co.thomasbooker.spritofnirn.JsonFileService;

import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComplimentsTest {

    @Mock
    JsonFileService jsonFileService;

    @Mock
    Interactions interactions;

    @InjectMocks
    Compliments testSubject = new Compliments();

    @Test
    public void shouldSendCompliment() throws FileNotFoundException {
        // Given
        when(jsonFileService.getRandomCompliment()).thenReturn("Compliment");

        // When
        testSubject.complimentHandler(new DiscordModel());

        // Then
        verify(interactions, times(1)).sendInteraction(any(), any());
    }
}
