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
    public void saveGame(Game game) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Game getGameById(long gameId) {
        return em.find(Game.class, gameId);
    }

    @Override
    public void updateGame(Game game) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            em.merge(game);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void saveGamer(Gamer gamer) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(gamer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Gamer getGamerByUsername(String username) {
        return em.find(Gamer.class, username);
    }

    @Override
    public void saveGameGamer(GameGamer gameGamer) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(gameGamer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public GameGamer getGameGamer(long gameId, String username) {
        try {
            return em.createQuery("SELECT gg FROM GameGamer gg WHERE gg.game.id = :gameId AND gg.gamer.username = :username", GameGamer.class)
                    .setParameter("gameId", gameId)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void saveMove(Move move) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(move);
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
    public List<Gamer> getAllGamers() {
        return em.createQuery("SELECT g FROM Gamer g", Gamer.class).getResultList();
    }
}