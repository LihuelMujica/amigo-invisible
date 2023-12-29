package com.lihuel.discordbot.discord.utils.embeds;

import com.lihuel.discordbot.entity.GameStatus;
import com.lihuel.discordbot.entity.InvisibleFriendGame;
import com.lihuel.discordbot.entity.InvisibleFriendUser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InvisibleFriendEmbed {

    public static @NotNull MessageEmbed GameInfoEmbed(InvisibleFriendGame game) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Amigo invisible");
        embedBuilder.setDescription("Partida de amigo invisible");
        embedBuilder.addField("Estado", mapStatus(game.getStatus()), false);
        List<List<InvisibleFriendUser>> groups = game.getGroups();
        if (groups != null && !groups.isEmpty()) {
            for (int i = 0; i < groups.size(); i++) {
                List<InvisibleFriendUser> group = groups.get(i);
                StringBuilder groupString = new StringBuilder();
                for (InvisibleFriendUser user : group) {
                    groupString.append("<@").append(user.getUserId()).append(">").append("\n");
                }
                embedBuilder.addField("Grupo " + (i), groupString.toString(), false);
            }
        }
        else {
            embedBuilder.addField("Grupos", "No hay grupos", false);
        }
        return embedBuilder.build();
    }

    private static String mapStatus(GameStatus status) {
        return switch (status) {
            case CREATED -> "Creada";
            case STARTED -> "Iniciada";
            case FINISHED -> "Finalizada";
            default -> "Desconocido";
        };
    }
}
