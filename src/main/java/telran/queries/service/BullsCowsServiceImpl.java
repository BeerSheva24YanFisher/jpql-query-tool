package telran.queries.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import telran.queries.entities.Game;
import telran.queries.entities.GameGamer;
import telran.queries.entities.Gamer;
import telran.queries.entities.Move;
import telran.queries.repo.BullsCowsRepository;

public class BullsCowsServiceImpl implements BullsCowsService {
    private final BullsCowsRepository repository;

    public BullsCowsServiceImpl(BullsCowsRepository repository) {
        this.repository = repository;
    }

    @Override
    public long createGame() {
        Game game = new Game();
        game.setFinished(false);
        game.setSequence(generateSequence());
        repository.saveGame(game);
        return game.getId();
    }

    @Override
    public void startGame(long gameId) {
        Game game = repository.getGameById(gameId);
        if (game != null && !game.isFinished()) {
            game.setDateTime(LocalDateTime.now());
            repository.updateGame(game);
        }
    }

    @Override
    public void joinGame(long gameId, String username) {
        Game game = repository.getGameById(gameId);
        if (game != null && !game.isFinished()) {
            Gamer gamer = repository.getGamerByUsername(username);
            if (gamer == null) {
                gamer.setUsername(username);
                gamer.setBirthdate(LocalDate.of(2004, 5, 22));
                repository.saveGamer(gamer);
            }

            GameGamer gameGamer = new GameGamer(game, gamer);
            repository.saveGameGamer(gameGamer);
        }
    }

    @Override
    public MoveResult makeMove(long gameId, String username, String move) {
        Game game = repository.getGameById(gameId);
        Gamer gamer = repository.getGamerByUsername(username);
        
        if (game == null || gamer == null || game.isFinished()) {
            throw new IllegalArgumentException("Game or gamer not found, or game already finished.");
        }

        String sequence = game.getSequence();
        int bulls = countBulls(sequence, move);
        int cows = countCows(sequence, move);
        
        GameGamer gameGamer = repository.getGameGamer(gameId, username);
        Move moveEntity = new Move(bulls, cows, move, gameGamer);
        repository.saveMove(moveEntity);

        if (bulls == 4) {
            game.setFinished(true);
            repository.updateGame(game);
        }

        return new MoveResult(bulls, cows, move);
    }

    @Override
    public void viewGame(long gameId) {
        Game game = repository.getGameById(gameId);
        if (game != null) {
            System.out.println(game);
        }
    }

    public static String generateSequence() {
        List<String> digits = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            digits.add(String.valueOf(i));
        }
        Collections.shuffle(digits);
        return digits.subList(0, 4).stream().collect(Collectors.joining());
    }

    private int countBulls(String sequence, String move) {
        int bulls = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == move.charAt(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    private int countCows(String sequence, String move) {
        int cows = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) != move.charAt(i) && sequence.contains(String.valueOf(move.charAt(i)))) {
                cows++;
            }
        }
        return cows;
    }

    @Override
    public List<Game> getAllGames() {
        return repository.getAllGames();
    }


    @Override
    public List<String> getMovesByGameId(long gameId) {
        return repository.getMovesByGameId(gameId);
    }

    @Override
    public List<Gamer> getAllGamers() {
        return repository.getAllGamers();
    }

    @Override
    public boolean isPlayerJoined(long gameId, String username) {
        return repository.getGameGamer(gameId, username)!=null;
    }

    @Override
    public List<String> getMoves(long gameId) {
        return repository.getMovesByGameId(gameId);
    }

    




    

}