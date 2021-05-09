import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class DiscordModel {

    // Message event
    String author;
    Member member;

    // Actual message
    String rawContent;
    MessageChannel messageChannel;

    public DiscordModel(Message message) {
        this.author = message.getAuthor().getName();
        this.member = message.getMember();

        this.rawContent = message.getContentRaw();
        this.messageChannel = message.getChannel();

    }

    public String getAuthor() {
        return author;
    }

    public Member getMember() {
        return member;
    }

    public String getRawContent() {
        return rawContent;
    }

    public MessageChannel getMessageChannel() {
        return messageChannel;
    }
}
