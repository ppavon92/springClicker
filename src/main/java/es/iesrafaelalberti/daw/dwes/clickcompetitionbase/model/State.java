package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    Set<City> cities = new HashSet<>();

    //Relation between cities - states. A city belongs to 1 state.
    @JsonBackReference
    @ManyToOne
    @JoinColumn()
    private Country country;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public State(String name, Long clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public State(Long id, String name, Long clicks) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
    }

    public State(Long id, String name, Long clicks, Set<City> cities
    ) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
        this.cities = cities;
    }

    public State(Set<City> cities) {
        this.cities = cities;
    }

    public State(String name, Country country){
        this.name = name;
        this.country = country;
    }
}
