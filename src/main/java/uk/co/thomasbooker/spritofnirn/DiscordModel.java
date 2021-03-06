package uk.co.thomasbooker.spritofnirn;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscordModel {

    // Message event
    String author;
    Member member;

    // Actual message
    String rawContent;
    MessageChannel messageChannel;

    // Users
    List<User> mentionedUsers;

    public void setupDiscordModel(Message message) {
        this.author = message.getAuthor().getName();
        this.member = message.getMember();

        this.rawContent = message.getContentRaw();
        this.messageChannel = message.getChannel();

        this.mentionedUsers = message.getMentionedUsers();
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

    public List<User> getMentionedUsers() {
        return mentionedUsers;
    }
}
