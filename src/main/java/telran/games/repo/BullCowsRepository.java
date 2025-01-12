package telran.games.repo;

public interface BullCowsRepository {
   void joinGame(long gameId, String username);
   void setGameIsFinished(long id);
}