package com.lihuel.discordbot.discord.commands.invisiblefriend;

import com.lihuel.discordbot.discord.commands.Command;
import com.lihuel.discordbot.service.InvisibleFriend.InvisibleFriendGameService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvisibleFriendEndGame implements Command {
    private final InvisibleFriendGameService invisibleFriendGameService;

    public InvisibleFriendEndGame(InvisibleFriendGameService invisibleFriendGameService) {
        this.invisibleFriendGameService = invisibleFriendGameService;
    }

    @Override
    public String getName() {
        return "amigo-invisible-finalizar";
    }

    @Override
    public String getDescription() {
        return "Finaliza la partida de amigo invisible";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws Exception {
        invisibleFriendGameService.endGame(event.getGuild().getId());
        event.reply("Partida de amigo invisible finalizada").queue();
    }
}
