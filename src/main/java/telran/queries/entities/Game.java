package telran.queries.entities;
import java.time.LocalDateTime;

import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    @Column(name="date_time")
    LocalDateTime dateTime;
    @Column(name="is_finished")
    boolean isFinished;
    String sequence;
    @Override
    public String toString() {
        return "Game [id=" + id + ", dateTime=" + dateTime + ", isFinished=" + isFinished + ", sequence=" + sequence
                + "]";
    }

    public long getId() {
        return id;
    }
    
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    public boolean isFinished() {
        return this.isFinished;
    }

    public String getSequence() {
        return sequence;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Game() {
    }

    public Game(long id, LocalDateTime dateTime, boolean isFinished, String sequence) {
        this.id = id;
        this.dateTime = dateTime;
        this.isFinished = isFinished;
        this.sequence = sequence;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game(JSONObject jsonObject) {
        this.id = jsonObject.getLong("id");
        this.dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"));
        this.isFinished = jsonObject.getBoolean("isFinished");
        this.sequence = jsonObject.getString("sequence");
    }

    







    







}