package com.lihuel.discordbot.repository;

import com.lihuel.discordbot.entity.GameStatus;
import com.lihuel.discordbot.entity.InvisibleFriendGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvisibleFriendGameRepository extends MongoRepository<InvisibleFriendGame, String> {
    Optional<InvisibleFriendGame> findByGuildIdAndStatus(String guildId, GameStatus gameStatus);

    @Query(value = "{ 'guildId' : ?0, 'status' : { $in: [ 'CREATED', 'STARTED' ] }, 'groups' : { $elemMatch: { 'users' : { $elemMatch: { 'userId' : ?1 } } } } }", exists = true)
    boolean playerIsInGame(String guildId, String userId);
}
