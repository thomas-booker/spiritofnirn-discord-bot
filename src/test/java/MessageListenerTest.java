import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.thomasbooker.spritofnirn.MessageController;
import uk.co.thomasbooker.spritofnirn.MessageListener;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MessageListenerTest {

    @Mock
    MessageController messageController;

    @Mock
    MessageReceivedEvent event;

    @InjectMocks
    MessageListener testSubject = new MessageListener();

    @Test
    public void shouldSendEventToMessageHandler() throws IOException {
        // Given

        // When
        testSubject.onMessageReceived(event);

        // Then
        verify(messageController, times(1)).handleMessage(event);
    }
}
