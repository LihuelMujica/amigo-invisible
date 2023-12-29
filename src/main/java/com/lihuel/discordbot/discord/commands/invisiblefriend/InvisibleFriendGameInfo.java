package com.lihuel.discordbot.discord.commands.invisiblefriend;

import com.lihuel.discordbot.discord.commands.Command;
import com.lihuel.discordbot.discord.utils.embeds.InvisibleFriendEmbed;
import com.lihuel.discordbot.entity.InvisibleFriendGame;
import com.lihuel.discordbot.exception.InvisibleFriendGameNotFoundException;
import com.lihuel.discordbot.service.InvisibleFriend.InvisibleFriendGameService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvisibleFriendGameInfo implements Command {
    private final InvisibleFriendGameService invisibleFriendGameService;

    public InvisibleFriendGameInfo(InvisibleFriendGameService invisibleFriendGameService) {
        this.invisibleFriendGameService = invisibleFriendGameService;
    }

    @Override
    public String getName() {
        return "amigo-invisible-info";
    }

    @Override
    public String getDescription() {
        return "Información sobre la partida de amigo invisible";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws InvisibleFriendGameNotFoundException {
        InvisibleFriendGame game = invisibleFriendGameService.getGame(event.getGuild().getId());
        event.replyEmbeds(InvisibleFriendEmbed.GameInfoEmbed(game)).queue();

    }
}
