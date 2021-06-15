package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "players_teams",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    Set<Team> teams = new HashSet<>();


//    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
//    Set<PlayerTeam> playerTeamSet = new HashSet<>();


    public Player() {
    }

    public Player(String name, int clicks, City city) {
        this.name = name;
        this.clicks = clicks;
        this.city = city;
    }

    public Player(String name, int clicks, City city, Set<Team> teams) {
        this.name = name;
        this.clicks = clicks;
        this.city = city;
        this.teams = teams;
    }

    //    public Player(Long id, String name, int clicks, City city, Set<PlayerTeam> playerTeamSet) {
//        this.id = id;
//        this.name = name;
//        this.clicks = clicks;
//        this.city = city;
//        this.playerTeamSet = playerTeamSet;
//    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
