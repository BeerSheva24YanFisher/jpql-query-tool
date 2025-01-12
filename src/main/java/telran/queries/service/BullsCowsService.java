package telran.queries.service;

import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.Gamer;

public interface BullsCowsService {
    Game createGame(String gameName);
    void joinGame(long gameId, String username);
    void finishGame(long gameId);
    List<Game> getAllGames();
    List<Gamer> getGamersByGameId(long gameId);
    void makeMove(long gameId, String username, String move);
    List<String> getMovesByGameId(long gameId);
}
