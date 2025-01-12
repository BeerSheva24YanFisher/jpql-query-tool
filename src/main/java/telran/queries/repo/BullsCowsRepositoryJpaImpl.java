package telran.queries.repo;

import java.util.List;

import jakarta.persistence.EntityManager;
import telran.queries.entities.Game;
import telran.queries.entities.GameGamer;
import telran.queries.entities.Gamer;
import telran.queries.entities.Move;

public class BullsCowsRepositoryJpaImpl implements BullsCowsRepository {
    private final EntityManager em;

    public BullsCowsRepositoryJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Game createGame(String gameName) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            Game game = new Game();
            game.setSequence(gameName);
            game.setFinished(false);
            em.persist(game);
            transaction.commit();
            return game;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void joinGame(long gameId, String username) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            Game game = getGameById(gameId);
            Gamer gamer = getGamer(username);
            GameGamer gameGamer = new GameGamer(game, gamer);
            em.persist(gameGamer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void setGameIsFinished(long gameId) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            Game game = getGameById(gameId);
            game.setFinished(true);
            em.merge(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Game getGameById(long gameId) {
        Game game = em.find(Game.class, gameId);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }

    @Override
    public List<Gamer> getGamersByGameId(long gameId) {
        return em.createQuery(
                "SELECT gg.gamer FROM GameGamer gg WHERE gg.game.id = :gameId", 
                Gamer.class)
            .setParameter("gameId", gameId)
            .getResultList();
    }

    @Override
    public boolean isGameFinished(long gameId) {
        Game game = getGameById(gameId);
        return game.isFinished();
    }

    @Override
    public void deleteGame(long gameId) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            Game game = getGameById(gameId);
            em.remove(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void addMove(long gameId, String username, String move) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            GameGamer gameGamer = getGameGamer(gameId, username);
            Move newMove = new Move();
            newMove.setGameGamer(gameGamer);
            newMove.setSequence(move);
            em.persist(newMove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<String> getMovesByGameId(long gameId) {
        return em.createQuery(
                "SELECT m.sequence FROM Move m WHERE m.gameGamer.game.id = :gameId", 
                String.class)
            .setParameter("gameId", gameId)
            .getResultList();
    }

    private Gamer getGamer(String username) {
        Gamer gamer = em.find(Gamer.class, username);
        return gamer;
    }

    private GameGamer getGameGamer(long gameId, String username) {
        return em.createQuery(
                "SELECT gg FROM GameGamer gg WHERE gg.game.id = :gameId AND gg.gamer.username = :username", 
                GameGamer.class)
            .setParameter("gameId", gameId)
            .setParameter("username", username)
            .getSingleResult();
    }
}
