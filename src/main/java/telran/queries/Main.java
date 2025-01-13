package telran.queries;

import java.util.HashMap;
import java.util.List;

import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import telran.queries.config.BullsCowsPersistenceUnitInfo;
import telran.queries.entities.Game;
import telran.queries.entities.Gamer;
import telran.queries.repo.BullsCowsRepositoryJpaImpl;
import telran.queries.service.BullsCowsService;
import telran.queries.service.BullsCowsService.MoveResult;
import telran.queries.service.BullsCowsServiceImpl;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class Main {
    static InputOutput io = new StandardInputOutput();
    static EntityManager em;
    static BullsCowsService service;

    public static void main(String[] args) {
        createEntityManager();
        createService();
        Menu menu = new Menu("Bulls and Cows Game", getMainMenuItems());
        menu.perform(io);
    }

    private static Item[] getMainMenuItems() {
        return new Item[] {
            Item.of("Create Game", Main::createGameWithRandomName),
            Item.of("Start Game", Main::startGame),
            Item.of("Join Game", Main::joinGame),
            Item.of("Play Game", Main::playGame),
            Item.of("View All Games", Main::viewAllGames),
            Item.of("View All Gamers", Main::viewAllGamers),
            Item.ofExit()
        };
    }

    private static void createEntityManager() {
        HashMap<String, Object> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        PersistenceUnitInfo persistenceUnit = new BullsCowsPersistenceUnitInfo();
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnit, hibernateProperties);
        em = emf.createEntityManager();
    }

    private static void createService() {
        BullsCowsRepositoryJpaImpl repository = new BullsCowsRepositoryJpaImpl(em);
        service = new BullsCowsServiceImpl(repository);
    }

    static void createGameWithRandomName(InputOutput io) {
        long gameId = service.createGame();
        io.writeLine("Game created with ID: " + gameId);
    }

    static void startGame(InputOutput io) {
        io.writeLine("Enter the Game ID to start:");
        String gameIdStr = io.readString("");

        long gameId;
        try {
            gameId = Long.parseLong(gameIdStr);
        } catch (NumberFormatException e) {
            io.writeLine("Invalid Game ID. Please enter a valid number.");
            return;
        }

        try {
            service.startGame(gameId);
            io.writeLine("Game with ID " + gameId + " has started.");
        } catch (Exception e) {
            io.writeLine("Error: " + e.getMessage());
        }
    }

    static void joinGame(InputOutput io) {
        io.writeLine("Enter the Game ID to join:");
        String gameIdStr = io.readString("");

        long gameId;
        try {
            gameId = Long.parseLong(gameIdStr);
        } catch (NumberFormatException e) {
            io.writeLine("Invalid Game ID. Please enter a valid number.");
            return;
        }

        io.writeLine("Enter your username:");
        String username = io.readString("");

        try {
            service.joinGame(gameId, username);
            io.writeLine("Successfully joined the game with ID: " + gameId);
        } catch (Exception e) {
            io.writeLine("Error: " + e.getMessage());
        }
    }

    static void playGame(InputOutput io) {
        io.writeLine("Enter the Game ID to play:");
        String gameIdStr = io.readString("");
        long gameId;
        try {
            gameId = Long.parseLong(gameIdStr);
        } catch (NumberFormatException e) {
            io.writeLine("Invalid Game ID. Please enter a valid number.");
            return;
        }
        
        io.writeLine("Enter your username:");
        String username = io.readString("");

        if (!service.isPlayerJoined(gameId, username)) {
            io.writeLine("You need to join the game first.");
            return;
        }

        Menu menu = new Menu("Game Menu", getGameMenuItems(gameId, username));
        menu.perform(io);
    }

    private static Item[] getGameMenuItems(long gameId, String username) {
        return new Item[] {
            Item.of("Make a Move", io -> makeMove(io, gameId, username)),
            Item.of("View Moves", io -> viewMoves(io, gameId)),
            Item.ofExit()
        };
    }
    
    private static void makeMove(InputOutput io, long gameId, String username) {
        io.writeLine("Enter your move:");
        String move = io.readString("");
    
        try {
            MoveResult result = service.makeMove(gameId, username, move);
            io.writeLine("Move result: " + result.toString());
        } catch (Exception e) {
            io.writeLine("Error: " + e.getMessage());
        }
    }
    
    private static void viewMoves(InputOutput io, long gameId) {
        List<String> moves = service.getMoves(gameId);
        if (moves.isEmpty()) {
            io.writeLine("No moves made in this game.");
        } else {
            io.writeLine("Moves made in the game:");
            for (String move : moves) {
                io.writeLine(move);
            }
        }
    }

    static void viewAllGames(InputOutput io) {
        List<Game> games = service.getAllGames();
        if (games.isEmpty()) {
            io.writeLine("No games available.");
        } else {
            for (Game game : games) {
                io.writeLine("Game ID: " + game.getId() + " | Is finished: " + game.isFinished());
            }
        }
    }

    static void viewAllGamers(InputOutput io) {
        List<Gamer> gamers = service.getAllGamers();
        if (gamers.isEmpty()) {
            io.writeLine("No gamers available.");
        } else {
            for (Gamer gamer : gamers) {
                io.writeLine("Gamer name: " + gamer.getUsername() + " | Birthdate: " + gamer.getBirthdate());
            }
        }
    }
}
