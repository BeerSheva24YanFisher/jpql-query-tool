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
        game.setDateTime(null);
        repository.saveGame(game);
        return game.getId();
    }


    @Override
    public boolean isGameEmpty(long gameId, String username){
        List<GameGamer> gameGamers = repository.getGameGamersByGameId(gameId);
        boolean flag = true;
        for (GameGamer gameGamer: gameGamers) {
            if (gameGamer.getGamer().getUsername() == username){
                flag = false;
            }
            
        }
        return flag;
    }



    @Override
    public void startGame(long gameId, String username) {
        Game game = repository.getGameById(gameId);
        List<GameGamer> gameGamers = repository.getGameGamersByGameId(gameId);

        if (game != null && !game.isFinished() && gameGamers != null) {
            game.setDateTime(LocalDateTime.now());
            repository.updateGame(game);
        }
    }

    @Override
    public void joinGame(long gameId, String username) {
        Game game = repository.getGameById(gameId);
        if (game != null && game.getDateTime()==null) {
            Gamer gamer = repository.getGamerByUsername(username);
            GameGamer gameGamer = repository.getGameGamer(gameId, username);
            if (gameGamer == null) {
                repository.saveGameGamer(new GameGamer(game, gamer));
            }
        }
    }

    @Override
    public boolean isGameStarted(long gameId){
        Game game = repository.getGameById(gameId);
        return game.getDateTime()!=null;

    }

    @Override
    public MoveResult makeMove(long gameId, String username, String move) {
        Game game = repository.getGameById(gameId);
        Gamer gamer = repository.getGamerByUsername(username);
        GameGamer gameGamer = repository.getGameGamer(gameId, username);
        
        if (game == null || gamer == null || game.isFinished()) {
            throw new IllegalArgumentException("Game or gamer not found");
        }

        String sequence = game.getSequence();
        int bulls = countBulls(sequence, move);
        int cows = countCows(sequence, move);
        
        Move moveEntity = new Move(bulls, cows, move, gameGamer);
        repository.saveMove(moveEntity);

        if (bulls == 4) {
            game.setFinished(true);
            repository.updateGame(game);
        }

        return new MoveResult(bulls, cows, move);
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
    public List<Move> getMovesByGameId(long gameId) {
        return repository.getMovesByGameId(gameId);
    }

    @Override
    public boolean isPlayerNotJoined(long gameId, String username) {
        return repository.getGameGamer(gameId, username)==null;
    }

    @Override
    public List<Game> getStartedGamesWithUser(String username) {
        return repository.getAllGames().stream()
            .filter(game -> game.getDateTime() != null)
            .filter(game -> repository.getGamersByGameId(game.getId()).stream()
                .anyMatch(gamer -> gamer.getUsername().equals(username)))
            .collect(Collectors.toList());
    }

    @Override
    public List<Game> getNotStartedGamesWithUser(String username) {
        return repository.getAllGames().stream()
            .filter(game -> game.getDateTime() == null)
            .filter(game -> repository.getGamersByGameId(game.getId()).stream()
                .anyMatch(gamer -> gamer.getUsername().equals(username)))
            .collect(Collectors.toList());
    }

    @Override
    public List<Game> getNotStartedGamesWithoutUser(String username) {
        return repository.getAllGames().stream()
            .filter(game -> game.getDateTime() == null)
            .filter(game -> repository.getGamersByGameId(game.getId()).stream()
                .allMatch(gamer -> !gamer.getUsername().equals(username)))
            .collect(Collectors.toList());
    }

    @Override
    public Gamer getUser(String username) {
        return repository.getGamerByUsername(username);
    }

    @Override
    public void saveGamer(String username, LocalDate birthDate) {
        Gamer newGamer = new Gamer(username,birthDate);
        repository.saveGamer(newGamer);
    }

    public Game getGame(long gameId){
        return repository.getGameById(gameId);
    }

}