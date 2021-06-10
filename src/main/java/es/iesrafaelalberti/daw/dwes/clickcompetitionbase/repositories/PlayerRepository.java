package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query("Select p from Player p Where p.clicks >= ?1")
    Collection<Player> findPlayerByClicks(Integer clicks);

    @Query("Select p from Player p inner join City c on c.id = p.city.id where c.name = :name")
    Collection<Player> findPlayerByCity(String name);

    @Query("Select p from Player p group by p.city, p.id")
    Collection<Player> groupPlayerByCity();

}

