package com.lihuel.discordbot.service.InvisibleFriend;

import com.lihuel.discordbot.entity.InvisibleFriendGame;
import com.lihuel.discordbot.entity.InvisibleFriendGift;
import com.lihuel.discordbot.entity.InvisibleFriendUser;

import java.util.List;
import java.util.Map;

public class InvisibleFriendGameService {
    public InvisibleFriendGame createGame(String guildId) {
        return null;
    }

    public InvisibleFriendGame getGame(String guildId) {
        return null;
    }

    public InvisibleFriendGame createGroup(String guildId) {
        return null;
    }

    public List<List<InvisibleFriendUser>> getGroups(String guildId) {
        return null;
    }

    public InvisibleFriendGame addGroup(String guildId, List<String> userIds) {
        return null;
    }

    public InvisibleFriendGame removeGroup(String guildId, int groupId) {
        return null;
    }

    public InvisibleFriendGame updateGroup(String guildId, int groupId, List<String> userIds) {
        return null;
    }

    public InvisibleFriendGame startGame(String guildId) {
        return null;
    }

    public InvisibleFriendGame endGame(String guildId) {
        return null;
    }

    public InvisibleFriendGame sendGift(String guildId, String userId, String gift) {
        return null;
    }

    public Map<String, InvisibleFriendGift> getGifts(String guildId) {
        return null;
    }

}
