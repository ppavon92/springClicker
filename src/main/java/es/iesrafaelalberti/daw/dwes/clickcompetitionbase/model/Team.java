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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teams", cascade = CascadeType.REMOVE)
    Set<Player> players = new HashSet<>();

//    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
//    Set<PlayerTeam> playerTeamSet = new HashSet<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
