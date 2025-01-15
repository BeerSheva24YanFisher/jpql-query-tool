package telran.queries.service;

import java.time.LocalDate;
import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.Gamer;
import telran.queries.entities.Move;
import telran.queries.service.BullsCowsService.MoveResult;



public interface BullsCowsService {
    long createGame();
    boolean startGame(long gameId, String username);
    boolean joinGame(long gameId, String username);
    MoveResult makeMove(long gameId, String username, String move);
    Game getGame(long gameId);
    List<Move> getMovesByGameId(long gameId);
    boolean isPlayerNotJoined(long gameId, String username);
    List<Game> getStartedGamesWithUser(String username);
    List<Game> getNotStartedGamesWithUser(String username);
    List<Game> getNotStartedGamesWithoutUser(String username);
    Gamer getUser(String username);
    void saveGamer(String username, LocalDate birthDate);
    
    record MoveResult(int bulls, int cows, String sequence) {}
    
}