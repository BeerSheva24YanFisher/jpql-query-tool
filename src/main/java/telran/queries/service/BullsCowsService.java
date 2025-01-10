package telran.queries.service;

import java.time.LocalDate;
import java.util.List;

import telran.queries.model.MoveData;

public interface BullsCowsService {
    int N_DIGITS = 4;
    long createGame();//returns ID of the created game
    List<String> startGame(long gameId); //returns list of user (gamer) names
    void registerGamer(String username, LocalDate birthDate);
    void gamerJoinGame(long gameId, String username);
    List<Long> getNotStartedGames();
    List<MoveData> moveProcessing(String sequence, long gameId, String username);
    boolean gameOver(long gameId);
    List<String> getGameGamers(long gameId);
    List<Long> getNotStartedGamesWithGamer(String username); //returns id’s of not started games with a given username
    List<Long> getNotStartedGamesWithNoGamer(String username); //returns id’s of not started games with no a given username
    List<Long> getStartedGamesWithGamer(String username);//returns id’s of the started but not finished games with a given username
    String loginGamer(String username);

}
