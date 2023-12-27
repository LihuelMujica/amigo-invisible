package com.lihuel.discordbot.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvisibleFriendGame {
    private String gameId;
    private String guildId;
    private List<List<InvisibleFriendUser>> groups;
}
