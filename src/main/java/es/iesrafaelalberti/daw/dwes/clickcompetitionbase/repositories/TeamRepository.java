package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TeamRepository extends CrudRepository<Team, Long> {
    @Query("Select t from Team t Where t.name = :name")
    Team findTeamByName(String name);

//    @Query("Select t from Team t inner join t.players where t.name = :name")
//    Collection<Team> listWithPlayers();
}

