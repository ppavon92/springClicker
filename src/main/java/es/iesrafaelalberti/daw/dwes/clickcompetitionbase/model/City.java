package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter @Getter
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;

    //Relation between cities - players. A city can have many players. 1:M
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    Set<Player> players = new HashSet<>();

    //Relation between cities - states. A city belongs to 1 state.
    @JsonBackReference
    @ManyToOne
    @JoinColumn()
    private State state;

    public City() {}
    public City (String name) {
        this.name = name;
    }

    public City (String name, Long clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public City (String name, State state) {
        this.name = name;
        this.state = state;
    }

    public City (Long id, String name, Long clicks) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
    }
}
