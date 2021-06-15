package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
public class PlayerTeam{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("id")
    @JoinColumn(name = "id_player")
    Player player;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("id")
    @JoinColumn(name = "id_team")
    Team team;

    public PlayerTeam() {
    }

    public PlayerTeam(Player player, Team team) {
        this.player = player;
        this.team = team;
    }
}
