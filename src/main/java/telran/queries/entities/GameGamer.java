package telran.queries.entities;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="game_gamer")
public class GameGamer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    Game game;
    @ManyToOne
    @JoinColumn(name = "gamer_id")
    Gamer gamer;
    @Column(name = "is_winner")
    boolean isWinner;

    @Override
    public String toString() {
        return "GameGamer [id=" + id + ", game=" + game.id + ", gamer=" + gamer.username + ", isWinner=" + isWinner + "]";
    }

    public GameGamer(Game game, Gamer gamer) {
        this.game = game;
        this.gamer = gamer;
    }

    public GameGamer() {
    }

    public Gamer getGamer() {
        return gamer;
    }

    public GameGamer(long id, Game game, Gamer gamer, boolean isWinner) {
        this.id = id;
        this.game = game;
        this.gamer = gamer;
        this.isWinner = isWinner;
    }

    public GameGamer(JSONObject jsonObject) {
        this.id = jsonObject.getLong("id");
        JSONObject gameJson = jsonObject.getJSONObject("game");
        this.game = new Game(gameJson);
        JSONObject gamerJson = jsonObject.getJSONObject("gamer");
        this.gamer = new Gamer(gamerJson);
        
        this.isWinner = jsonObject.getBoolean("isWinner");
    }



    

}