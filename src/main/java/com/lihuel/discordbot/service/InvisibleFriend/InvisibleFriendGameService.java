package com.lihuel.discordbot.service.InvisibleFriend;

import com.lihuel.discordbot.entity.GameStatus;
import com.lihuel.discordbot.entity.InvisibleFriendGame;
import com.lihuel.discordbot.entity.InvisibleFriendGift;
import com.lihuel.discordbot.entity.InvisibleFriendUser;
import com.lihuel.discordbot.exception.InvisibleFriendGameAlreadyExistsException;
import com.lihuel.discordbot.exception.InvisibleFriendGameNotFoundException;
import com.lihuel.discordbot.repository.InvisibleFriendGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class InvisibleFriendGameService {
    private final InvisibleFriendGameRepository invisibleFriendGameRepository;

    @Autowired
    public InvisibleFriendGameService(InvisibleFriendGameRepository invisibleFriendGameRepository) {
        this.invisibleFriendGameRepository = invisibleFriendGameRepository;
    }

    public InvisibleFriendGame createGame(String guildId) throws InvisibleFriendGameAlreadyExistsException {
        if (
                invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.CREATED).isPresent() ||
                invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.STARTED).isPresent()) {
            throw new InvisibleFriendGameAlreadyExistsException("Ya existe un juego creado para este servidor");
        }

        InvisibleFriendGame invisibleFriendGame = new InvisibleFriendGame();
        invisibleFriendGame.setGuildId(guildId);
        invisibleFriendGame.setStatus(GameStatus.CREATED);
        return invisibleFriendGameRepository.save(invisibleFriendGame);
    }

    public InvisibleFriendGame getGame(String guildId) throws InvisibleFriendGameNotFoundException {
        InvisibleFriendGame invisibleFriendGame = invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.CREATED).orElse(invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.STARTED).orElse(null));
        if (invisibleFriendGame == null) {
            throw new InvisibleFriendGameNotFoundException("No hay ninguna partida creada o en curso para este servidor");
        }
        return invisibleFriendGame;
    }

    public InvisibleFriendGame createGroup(String guildId) {
        return null;
    }

    public List<List<InvisibleFriendUser>> getGroups(String guildId) {
        return null;
    }

    public InvisibleFriendGame addGroup(String guildId, List<String> userIds) throws InvisibleFriendGameNotFoundException, InvisibleFriendGameAlreadyExistsException {
        InvisibleFriendGame invisibleFriendGame = invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.CREATED).orElseThrow(() -> new InvisibleFriendGameNotFoundException("No hay ninguna partida creada para este servidor o la partida ya ha comenzado"));
        List<InvisibleFriendUser> users = new ArrayList<>();
        for (String userId : userIds) {
            if (invisibleFriendGameRepository.playerIsInGame(guildId, userId)) {
                throw new InvisibleFriendGameAlreadyExistsException("El usuario <@" + userId + "> ya está en el juego");
            }
            InvisibleFriendUser invisibleFriendUser = new InvisibleFriendUser();
            invisibleFriendUser.setUserId(userId);
            users.add(invisibleFriendUser);
        }
        invisibleFriendGame.getGroups().add(users);
        return invisibleFriendGameRepository.save(invisibleFriendGame);
    }

    public InvisibleFriendGame removeGroup(String guildId, int groupId) throws InvisibleFriendGameNotFoundException {
        InvisibleFriendGame game = invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.CREATED).orElseThrow(() -> new InvisibleFriendGameNotFoundException("No hay ninguna partida creada para este servidor o la partida ya ha comenzado"));
        game.getGroups().remove(groupId);
        return invisibleFriendGameRepository.save(game);
    }
    public InvisibleFriendGame startGame(String guildId) {
        return null;
    }

    public InvisibleFriendGame endGame(String guildId) throws InvisibleFriendGameNotFoundException {
        InvisibleFriendGame game = invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.STARTED).orElse(
                invisibleFriendGameRepository.findByGuildIdAndStatus(guildId, GameStatus.CREATED).orElse(null)
        );
        if (game == null) {
            throw new InvisibleFriendGameNotFoundException("No hay ninguna partida creada o en curso para este servidor");
        }
        game.setStatus(GameStatus.FINISHED);
        return invisibleFriendGameRepository.save(game);
    }

    public InvisibleFriendGame sendGift(String guildId, String userId, String gift) {
        return null;
    }

    public Map<String, InvisibleFriendGift> getGifts(String guildId) {
        return null;
    }

}
