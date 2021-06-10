package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter @Getter
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    private int clicks;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    Set<Player> players = new HashSet<>();

    public City() {}
    public City (String name) {
        this.name = name;
    }
//    public City (String name, int clicks) {
//        this.name = name;
//        this.clicks = clicks;
//    }

//    public void sumClicks(int clicks) {
//        this.clicks = clicks;
//    }
}
