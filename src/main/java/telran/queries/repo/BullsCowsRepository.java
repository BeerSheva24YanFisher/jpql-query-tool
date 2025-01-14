package telran.queries.repo;

import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.GameGamer;
import telran.queries.entities.Gamer;
import telran.queries.entities.Move;

public interface BullsCowsRepository {
    void saveGame(Game game);
    Game getGameById(long gameId);
    void updateGame(Game game);
    void saveGamer(Gamer gamer);
    Gamer getGamerByUsername(String username);
    void saveGameGamer(GameGamer gameGamer);
    GameGamer getGameGamer(long gameId, String username);
    void saveMove(Move move);
    List<Move> getMovesByGameId(long gameId);
    List<Game> getAllGames();
    List<Gamer> getGamersByGameId(long gameId);
    List<Gamer> getAllGamers();
    List<GameGamer> getGameGamersByGameId(long gameId);

}