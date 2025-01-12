package telran.queries.service;

import java.time.LocalDate;
import java.util.List;

public interface BullsCowsService {
    long createGame();
    List<String> startGame(long gameId);
    void registerGamer(String username, LocalDate birthDate);
    void gamerJoinGame(long gameId, String username);
    List<Long> getNotStartedGames();
    boolean gameOver(long gameId);
    List<String> getGameGamers(long gameId);
    String loginGamer(String username);

}
