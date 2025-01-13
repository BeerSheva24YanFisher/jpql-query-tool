package telran.queries.service;

import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.Gamer;



public interface BullsCowsService {
    long createGame();
    void startGame(long gameId);
    void joinGame(long gameId, String username);
    MoveResult makeMove(long gameId, String username, String move);
    void viewGame(long gameId);
    List<Game> getAllGames();
    List<String> getMovesByGameId(long gameId);
    List<Gamer> getAllGamers();
    boolean isPlayerJoined(long gameId, String username);
    List<String> getMoves(long gameId);

    record MoveResult(int bulls, int cows, String sequence) {}
    
}