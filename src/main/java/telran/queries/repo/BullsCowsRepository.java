package telran.queries.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.Gamer;

public interface BullsCowsRepository {
    Game getGame(long id);
	Gamer getGamer(String username);
	long createNewGame(String sequence);
	void createNewGamer(String username, LocalDate birthdate);
	boolean isGameStarted(long id);
	void setStartDate(long gameId, LocalDateTime dateTime);
	boolean isGameFinished(long id);
	void setIsFinished(long gameId);
	List<Long> getGameIdsNotStarted();
	List<String> getGameGamers(long id);
	void createGameGamer(long gameId, String username);
	void setWinner(long gameId, String username);
	boolean isWinner(long gameId, String username);

}
