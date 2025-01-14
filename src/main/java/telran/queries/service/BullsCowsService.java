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
    List<Game> getAvailableGames();
    List<Game> getStartedGames();
    List<Game> StartedGamesWithUser(String username); //Просмотр всех начатых игр, в которых пользователь является участником
    List<Game> NotStartedGamesWithUser(String username);
    List<Game> NotStartedGamesWithoutUser(String username);



    Gamer getUser(String username);
    void saveGamer(String username, LocalDate birthDate);

    record MoveResult(int bulls, int cows, String sequence) {}
    
}