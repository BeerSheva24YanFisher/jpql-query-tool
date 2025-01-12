package telran.queries.repo;

import java.util.List;

import telran.queries.entities.Game;
import telran.queries.entities.Gamer;

public interface BullsCowsRepository {
    // Создание новой игры
    Game createGame(String gameName);

    // Присоединение игрока к игре
    void joinGame(long gameId, String username);

    // Завершение игры
    void setGameIsFinished(long gameId);

    // Получение игры по ID
    Game getGameById(long gameId);

    // Получение всех игр
    List<Game> getAllGames();

    // Получение всех игроков в игре
    List<Gamer> getGamersByGameId(long gameId);

    // Проверка, завершена ли игра
    boolean isGameFinished(long gameId);

    // Удаление игры
    void deleteGame(long gameId);

    // Добавление хода игрока в игре
    void addMove(long gameId, String username, String move);

    // Получение ходов в игре
    List<String> getMovesByGameId(long gameId);
}