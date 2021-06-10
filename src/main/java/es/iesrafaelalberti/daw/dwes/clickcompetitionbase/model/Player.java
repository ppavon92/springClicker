package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;

    @JsonBackReference
    @ManyToOne
    @JoinColumn()
    private City city;

    public Player() {
    }

    public Player(String name, int clicks, City city) {
        this.name = name;
        this.clicks = clicks;
        this.city = city;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
