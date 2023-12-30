package com.lihuel.discordbot.discord.commands.invisiblefriend;

import com.lihuel.discordbot.discord.commands.Command;
import com.lihuel.discordbot.discord.utils.embeds.InvisibleFriendEmbed;
import com.lihuel.discordbot.service.InvisibleFriend.InvisibleFriendGameService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvisibleFriendShowGifts implements Command {
    private final InvisibleFriendGameService invisibleFriendGameService;

    public InvisibleFriendShowGifts(InvisibleFriendGameService invisibleFriendGameService) {
        this.invisibleFriendGameService = invisibleFriendGameService;
    }

    @Override
    public String getName() {
        return "amigo-invisible-mostrar-regalos";
    }

    @Override
    public String getDescription() {
        return "Muestra los regalos de la partida";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws Exception {
        event.reply("Los regalos son:").queue();
        List<MessageEmbed> gifts = invisibleFriendGameService.getGifts(event.getGuild().getId()).stream()
                        .map(gift -> {
                            return InvisibleFriendEmbed.giftEmbed(gift.getTitle(), gift.getBody(), gift.getImgURL(), gift.getReceiverId());
                        }).toList();

        event.getChannel().sendMessageEmbeds(gifts).queue();
        invisibleFriendGameService.resetGifts(event.getGuild().getId());
    }
}
