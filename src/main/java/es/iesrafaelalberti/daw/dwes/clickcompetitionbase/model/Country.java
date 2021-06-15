package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    Set<State> states = new HashSet<>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Long clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public Country(Long id, String name, Long clicks) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
    }

    public Country(Long id, String name, Long clicks, Set<State> states
    ) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
        this.states = states;
    }
}
