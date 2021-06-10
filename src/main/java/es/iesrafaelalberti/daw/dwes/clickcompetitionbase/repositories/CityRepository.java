package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.City;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CityRepository extends CrudRepository<City, Long> {
    @Query("Select c from City c where c.name = :name")
    City findCityByName(String name);

    @Query("Select c from City c inner join Player p on c.id = p.city.id group by c.id order by sum(p.clicks) desc")
    Collection<City> cityclassification();

}
