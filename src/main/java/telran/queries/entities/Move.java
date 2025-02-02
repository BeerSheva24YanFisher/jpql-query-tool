package telran.queries.entities;
import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Table(name="move")
@Entity
public class Move {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
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


    public void setCows(int cows) {
        this.cows = cows;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public Move(int bulls, int cows, String sequence, GameGamer gameGamer) {
        this.bulls = bulls;
        this.cows = cows;
        this.sequence = sequence;
        this.gameGamer = gameGamer;
    }

    public Move(long id, int bulls, int cows, String sequence, GameGamer gameGamer) {
        this.id = id;
        this.bulls = bulls;
        this.cows = cows;
        this.sequence = sequence;
        this.gameGamer = gameGamer;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    public GameGamer getGameGamer() {
        return gameGamer;
    }

    public String getSequence() {
        return sequence;
    }

    public Move() {
    }

    public Move(JSONObject jsonObject) {
        this.id = jsonObject.getLong("id");
        this.bulls = jsonObject.getInt("bulls");
        this.cows = jsonObject.getInt("cows");
        this.sequence = jsonObject.getString("sequence");
        JSONObject gameGamerJson = jsonObject.getJSONObject("gameGamer");
        this.gameGamer = new GameGamer(gameGamerJson);
    }


}