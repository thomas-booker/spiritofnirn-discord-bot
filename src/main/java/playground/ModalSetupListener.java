package playground;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class ModalSetupListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        event.getInteraction().getName();
        if (event.getGuild().getCategoriesByName("Raids", true).isEmpty()) {
            event.getGuild().createCategory("Raids").queue();
        }
        event.replyModal(createRaidModal()).queue();

    }

    private Modal createRaidModal() {
        TextInput name = TextInput.create("raid-name", "Name", TextInputStyle.SHORT)
                .setPlaceholder("The kings lair")
                .setMinLength(1)
                .setMaxLength(100)
                .build();

        TextInput description = TextInput.create("raid-description", "Name", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Gonna be really really good")
                .setMinLength(1)
                .setMaxLength(4000)
                .build();

        TextInput date = TextInput.create("raid-date", "Date (dd/mm/yyyy)", TextInputStyle.SHORT)
                .setPlaceholder("04/20/2069")
                .setMinLength(1)
                .setMaxLength(100)
                .build();

        TextInput time = TextInput.create("raid-time", "Time (hh:mm)", TextInputStyle.SHORT)
                .setPlaceholder("17:30")
                .setMinLength(1)
                .setMaxLength(100)
                .build();

        TextInput timezone = TextInput.create("raid-timezone", "Timezone", TextInputStyle.SHORT)
                .setPlaceholder("GMT")
                .setMinLength(1)
                .setMaxLength(3)
                .build();

        return Modal.create("create-raid", "Create Raid")
                .addActionRows(ActionRow.of(name), ActionRow.of(description), ActionRow.of(time), ActionRow.of(date), ActionRow.of(timezone))
                .build();
    }

    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if (event.getModalId().equals("create-raid")) {
            String name = event.getValue("raid-name").getAsString();
            String description = event.getValue("raid-description").getAsString();
            String date = event.getValue("raid-date").getAsString();
            String time = event.getValue("raid-time").getAsString();
            String timezone = event.getValue("raid-timezone").getAsString();

            event.getGuild().getCategoriesByName("Raids", true).get(0).createTextChannel(name + date).queue(textChannel -> {
//                createScheduledEvent(textChannel, event, name, description, date, time, timezone);
                createEmbededRaidMessage(textChannel);
            });

            event.reply("Raid created!").setEphemeral(true).queue();
        }
    }

    private void createEmbededRaidMessage(TextChannel textChannel) {
        String title = "xyz";
        textChannel.sendMessageEmbeds(new EmbedBuilder()
                        .setTitle(title)
                        .setDescription("Members:\n")
                        .setColor(1)
                        .setFooter("This is a footer")
                        .setImage("https://alcasthq.com/wp-content/uploads/2018/08/aetherian-archive-guide-banner.jpg")
                        .setThumbnail("https://alcasthq.com/wp-content/uploads/2018/08/aetherian-archive-guide-banner.jpg")
                        .addField("Tank", "Tank1 \n tank2", true)
                        .addField("DPS", "DPS1 \n DPS3", true)
                        .addField("Healer (2/2)", "hALER \n tank3 \n tank3", true)
                        .addField("URL", "https://alcasthq.com/eso-aetherian-archive-guide/", false)
                        .build()
                )
                .queue(message -> message.editMessageEmbeds().setActionRow(Button.secondary(message.getId() + "red", Emoji.fromUnicode("U+1F534")), Button.secondary(message.getId() + "blue", Emoji.fromUnicode("U+1F535"))).queue());
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (event.getButton().getId().equals("red")) {
            event.getGuild().getTextChannelById(event.getTextChannel().getId()).getHistory().retrievePast(20).queue(history -> {
                        history.get(history.size() - 1).editMessageEmbeds(new EmbedBuilder().setDescription(history.get(history.size() - 1).getEmbeds().get(0).getDescription() + "\n:red_circle:" + event.getJDA().getUserById(event.getUser().getId()).getName()).build()).queue();
                    }
            );
        }
        if (event.getButton().getId().equals("blue")) {
            event.getGuild().getTextChannelById(event.getTextChannel().getId()).getHistory().retrievePast(20).queue(history -> {
                        history.get(history.size() - 1).editMessageEmbeds(new EmbedBuilder().setDescription(history.get(history.size() - 1).getEmbeds().get(0).getDescription() + "\n:blue_circle:" + event.getJDA().getUserById(event.getUser().getId()).getName()).build()).queue();
                    }
            );
        }
        event.reply("Added to raid!").setEphemeral(true).queue();
    }

    // needs features not in JDA releases yet.

//    private void createScheduledEvent(TextChannel textChannel, @NotNull ModalInteractionEvent event, String name, String description, String date, String time, String timezone) {
//        event.getGuild().createScheduledEvent()
//                .setName("Raid - " + name)
//                .setDescription(description)
//                .setStartTime(parseTimeStringAndDateString(date, time, timezone))
//                .setEndTime(parseTimeStringAndDateString(date, time, timezone).plusHours(1))
//                .setLocation("https://discord.com/channels/" + event.getGuild().getId() + "/" + textChannel.getId())
//                .queue();
//    }

//    private static final String DATE_TIME_FORMAT = "HH:mmdd/MM/yyyyz";
//    public static LocalDateTime parseTimeStringAndDateString(String timeString, String dateString, String timezoneString){
//        return OffsetDateTime.of(LocalDateTime.parse(dateString + timeString, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)), ZoneOffset.getAvailableZoneIds());
//    }
//


//    @Override
//    public void onGuildScheduledEventUserAdd(@NotNull GuildScheduledEventUserAddEvent event) {
//        event.getGuild().retrieveMemberById(event.getUserId()).queue();
//        String textChannelIdForRaid = event.getGuildScheduledEvent().getExternalLocation().substring(event.getGuildScheduledEvent().getExternalLocation().lastIndexOf("/") + 1);
//        event.getGuild().getTextChannelById(event.getGuildScheduledEvent().getExternalLocation().substring(event.getGuildScheduledEvent().getExternalLocation().lastIndexOf("/") + 1)).getHistory().retrievePast(20).queue(history -> {
//                    history.get(1).editMessageEmbeds(new EmbedBuilder().setDescription(history.get(history.size() - 1).getEmbeds().get(0).getDescription() + "\n:white_circle:" + event.getJDA().getUserById(event.getUserId()).getName()).build()).queue();
//                }
//        );
//    }
//
//    @Override
//    public void onGuildScheduledEventUserRemove(@NotNull GuildScheduledEventUserRemoveEvent event) {
//        event.getGuild().retrieveMemberById(event.getUserId()).queue();
//        String textChannelIdForRaid = event.getGuildScheduledEvent().getExternalLocation().substring(event.getGuildScheduledEvent().getExternalLocation().lastIndexOf("/") + 1);
//        event.getGuild().getTextChannelById(event.getGuildScheduledEvent().getExternalLocation().substring(event.getGuildScheduledEvent().getExternalLocation().lastIndexOf("/") + 1)).getHistory().retrievePast(20).queue(history -> {
//                    history.get(1).editMessageEmbeds(new EmbedBuilder().setDescription(history.get(history.size() - 1).getEmbeds().get(0).getDescription().replace("\n:white_circle:" + event.getJDA().getUserById(event.getUserId()).getName(), "")).build()).queue();
//                }
//        );
//
//    }

}