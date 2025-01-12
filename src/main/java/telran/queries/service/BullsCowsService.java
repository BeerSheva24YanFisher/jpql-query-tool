package telran.queries.service;

import java.time.LocalDate;
import java.util.List;

record MoveResult (int bulls, int cows, String sequence){};

public interface BullsCowsService {
    long createGame();
    List<String> startGame(long gameId);
    void registerGamer(String username, LocalDate birthDate);
    void gamerJoinGame(long gameId, String username);
    boolean gameOver(long gameId);
    List<String> getGameGamers(long gameId);
    String loginGamer(String username);
    List <MoveResult> move(String username, long gameId, String sequence);
    String getGameStatus(long gameId);

    
    
}
