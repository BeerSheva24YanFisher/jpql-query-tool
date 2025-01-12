package telran.queries.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Table(name="move")
@Entity
public class Move {
@Id
@GeneratedValue
long id;
int bulls;
int cows;
String sequence;
@ManyToOne
@JoinColumn (name = "game_gamer_id")
GameGamer gameGamer;
@Override
public String toString() {
    return "Move [id=" + id + ", bulls=" + bulls + ", cows=" + cows + ", sequence=" + sequence + ", gameGamer="
            + gameGamer.id + "]";
}

public void setGameGamer(GameGamer gameGamer) {
    this.gameGamer = gameGamer;
}

public void setSequence(String sequence) {
    this.sequence = sequence;
}



}