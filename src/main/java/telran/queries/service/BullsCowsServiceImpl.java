package telran.queries.service;

import telran.queries.repo.BullsCowsRepository;
import telran.queries.entities.Game;
import telran.queries.entities.Gamer;

import java.util.List;

public class BullsCowsServiceImpl implements BullsCowsService {
    private final BullsCowsRepository repository;

    public BullsCowsServiceImpl(BullsCowsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game createGame(String gameName) {
        return repository.createGame(gameName);
    }

    @Override
    public void joinGame(long gameId, String username) {
        repository.joinGame(gameId, username);
    }

    @Override
    public void finishGame(long gameId) {
        repository.setGameIsFinished(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return repository.getAllGames();
    }

    @Override
    public List<Gamer> getGamersByGameId(long gameId) {
        return repository.getGamersByGameId(gameId);
    }

    @Override
    public void makeMove(long gameId, String username, String move) {
        repository.addMove(gameId, username, move);
    }

    @Override
    public List<String> getMovesByGameId(long gameId) {
        return repository.getMovesByGameId(gameId);
    }
}