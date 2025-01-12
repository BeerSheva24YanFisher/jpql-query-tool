package telran.queries.entities;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue
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
    public void setFinished(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFinished'");
    }
    public void setSequence(String gameName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSequence'");
    }
    public boolean isFinished() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinished'");
    }



}