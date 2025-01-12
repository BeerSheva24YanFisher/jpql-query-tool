package telran.queries.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="game_gamer")
public class GameGamer {
    @Id
    @GeneratedValue
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

}