package com.lihuel.discordbot.entity;

import lombok.Data;

import java.util.List;

@Data
public class InvisibleFriendUser {
    private String userId;
    private String friendId;
    private List<InvisibleFriendGift> giftsSent;
}
