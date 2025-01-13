package telran.queries.entities;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="gamer")
public class Gamer {
    @Id
    String username;
    LocalDate birthdate;
    @Override
    public String toString() {
        return "Gamer [username=" + username + ", birthdate=" + birthdate + "]";
    }

    public Gamer() {
    }

    public Gamer(String username, LocalDate birthdate) {
        this.username = username;
        this.birthdate = birthdate;
    }



    public String getUsername() {
        return username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    



    



}