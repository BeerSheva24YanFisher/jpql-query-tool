package telran.games.repo;

import jakarta.persistence.*;
import telran.games.exceptions.GameNotFoundException;
import telran.games.exceptions.GamerNotFoundException;
import telran.queries.entities.*;

public class BullsCowsRepositoryJpaImpl implements BullCowsRepository {
    EntityManager em;

    @Override
    public void joinGame(long gameId, String username) {
        var transaction = em.getTransaction();
        transaction.begin();
        try {
            Game game = getGame(gameId);
            Gamer gamer = getGamer(username);
            GameGamer gameGamer = new GameGamer(game, gamer);
            em.persist(gameGamer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    private Gamer getGamer(String username) {
        Gamer gamer = em.find(Gamer.class, username);
        if (gamer == null) {
            throw new GamerNotFoundException(username);
        }
        return gamer;
    }

    private Game getGame(long gameId) {
        Game game = em.find(Game.class, gameId);
        if (game == null) {
            throw new GameNotFoundException(gameId);
        }
        return game;
    }

    @Override
    public void setGameIsFinished(long gameId) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            Game game = getGame(gameId);
            game.setGameIsFinished();
            transaction.commit();
        } catch (Exception e) {
          transaction.rollback();
          throw e;
        }
    
    }

}